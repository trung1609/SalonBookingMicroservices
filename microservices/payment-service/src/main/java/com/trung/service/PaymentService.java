package com.trung.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.trung.domain.PaymentMethod;
import com.trung.model.PaymentOrder;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.payload.response.PaymentLinkResponse;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDTO userDTO,
                                    BookingDTO bookingDTO,
                                    PaymentMethod paymentMethod) throws RazorpayException, StripeException;

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO userDTO,
                                          Long amount,
                                          Long orderId) throws RazorpayException;

    String[] createStripePaymentLink(UserDTO userDTO, Long Amount, Long orderId) throws StripeException;

    Boolean proceedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException;
}
