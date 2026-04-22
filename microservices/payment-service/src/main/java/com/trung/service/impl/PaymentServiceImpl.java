package com.trung.service.impl;

import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.trung.domain.PaymentMethod;
import com.trung.domain.PaymentOrderStatus;
import com.trung.model.PaymentOrder;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.payload.response.PaymentLinkResponse;
import com.trung.repository.PaymentRepository;
import com.trung.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpaySecretKey;

    @Value("${stripe.api.secret}")
    private String stripeSecretKey;

    @Override
    public PaymentLinkResponse createOrder(UserDTO userDTO, BookingDTO bookingDTO, PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Long amount = (long) bookingDTO.getTotalPrice();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setBookingId(bookingDTO.getId());
        paymentOrder.setSalonId(bookingDTO.getSalonId());
        paymentOrder.setUserId(userDTO.getId());
        PaymentOrder savedOrder = paymentRepository.save(paymentOrder);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            PaymentLink paymentLink = createRazorpayPaymentLink(userDTO,
                    savedOrder.getAmount(),
                    savedOrder.getId());

            String paymentUrl = paymentLink.get("short_url");
            String paymentUrlId = paymentLink.get("id");

            paymentLinkResponse.setPaymentLinkUrl(paymentUrl);
            paymentLinkResponse.setPaymentLinkId(paymentUrlId);

            savedOrder.setPaymentLinkId(paymentUrlId);
            paymentRepository.save(savedOrder);
        } else {
            String[] stripePaymentData = createStripePaymentLink(
                    userDTO,
                    savedOrder.getAmount(),
                    savedOrder.getId()
            );
            String paymentUrl = stripePaymentData[0];
            String paymentLinkId = stripePaymentData[1];

            paymentLinkResponse.setPaymentLinkUrl(paymentUrl);
            paymentLinkResponse.setPaymentLinkId(paymentLinkId);

            savedOrder.setPaymentLinkId(paymentLinkId);
            paymentRepository.save(savedOrder);
        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        PaymentOrder paymentOrder = paymentRepository.findById(id).orElse(null);
        if (paymentOrder == null) {
            throw new Exception("Payment order not found with id: " + id);
        }

        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentLinkId(paymentId);
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO userDTO, Long Amount, Long orderId) throws RazorpayException {
        Long amount = Amount * 100;
        RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpaySecretKey);
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", amount);
        paymentLinkRequest.put("currency", "INR");
        JSONObject customer = new JSONObject();
        customer.put("name", userDTO.getFullName());
        customer.put("email", userDTO.getEmail());

        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);
        paymentLinkRequest.put("notify", notify);

        paymentLinkRequest.put("reminder_enable", true);

        paymentLinkRequest.put("callback_url", "http://localhost:3000/payment-success/" + orderId);

        paymentLinkRequest.put("callback_method", "get");

        return razorpayClient.paymentLink.create(paymentLinkRequest);
    }

    @Override
    public String[] createStripePaymentLink(UserDTO userDTO, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/" + orderId)
                .setCancelUrl("http://localhost:3000/payment-cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount * 100)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder().setName("Salon Appointment Booking")
                                        .build())
                                .build())
                        .build()
                )
                .build();

        Session session = Session.create(params);
        return new String[]{session.getUrl(), session.getId()};
    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            if (paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)) {
                RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpaySecretKey);

                Payment payment = razorpayClient.payments.fetch(paymentId);
                Integer amount = payment.get("amount");
                String status = payment.get("status");
                if (status.equals("captured")) {
                    // produce kafka event
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentRepository.save(paymentOrder);
                    return true;
                }
                return false;
            } else {
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentRepository.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
