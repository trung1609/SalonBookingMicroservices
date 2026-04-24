package com.trung.controller;

import com.trung.domain.PaymentMethod;
import com.trung.model.PaymentOrder;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.payload.response.PaymentLinkResponse;
import com.trung.service.PaymentService;
import com.trung.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final UserFeignClient userFeignClient;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO bookingDTO,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

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
             @RequestParam Long orderId,
             @RequestParam(required = false) String paymentId) throws Exception {
         PaymentOrder paymentOrder = paymentService.getPaymentOrderById(orderId);
         if (paymentOrder == null) {
             throw new Exception("Payment order not found with order id: " + orderId);
         }
         Boolean response = paymentService.proceedPayment(paymentOrder, paymentId, paymentOrder.getPaymentLinkId());
         return ResponseEntity.ok(response);
     }
}
