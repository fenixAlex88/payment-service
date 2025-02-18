//package org.example.payment.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.payment.dto.PaymentRequest;
//import org.example.payment.service.PaymentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/payments")
//@RequiredArgsConstructor
//@Slf4j
//public class RestPaymentController implements PaymentController {
//    private final PaymentService paymentService;
//
//    @PostMapping("/handlePayment")
//    @Override
//    public ResponseEntity<Void> handlePayment(@RequestBody PaymentRequest request) {
//        try {
//            log.info("Processing payment request: {}", request);
//            paymentService.processPayment(request.getUserId(), request.getAmount());
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            log.error("Failed to process payment request: {}", request, e);
//            return ResponseEntity.status(500).build();
//        }
//    }
//
//    @PostMapping("/handleCancellation")
//    @Override
//    public ResponseEntity<Void> handleCancellation(@RequestBody PaymentRequest request) {
//        try {
//            log.info("Processing payment cancellation: {}", request.getPaymentId());
//            paymentService.cancelPayment(request.getPaymentId(), request.getUserId());
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            log.error("Failed to process payment cancellation: {}", request, e);
//            return ResponseEntity.status(500).build();
//        }
//    }
//}
