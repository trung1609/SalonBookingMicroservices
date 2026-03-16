package com.trung.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.trung.domain.PaymentMethod;
import com.trung.model.PaymentOrder;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.payload.response.PaymentLinkResponse;
import com.trung.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO bookingDTO,
            @RequestParam PaymentMethod paymentMethod
    ) throws StripeException, RazorpayException {
        UserDTO userDTO = new UserDTO();
        userDTO.setFullName("Trung");
        userDTO.setEmail("trung@gmail.com");
        userDTO.setId(1L);

        PaymentLinkResponse response = paymentService.createOrder(userDTO, bookingDTO, paymentMethod);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{paymentOrderId}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId) throws Exception {
        PaymentOrder response = paymentService.getPaymentOrderById(paymentOrderId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        Boolean response = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
        return ResponseEntity.ok(response);
    }
}
