//package org.example.payment.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.payment.dto.PaymentRequest;
//import org.example.payment.service.PaymentService;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class KafkaPaymentController implements PaymentController {
//    private final PaymentService paymentService;
//
//    @Override
//    @KafkaListener(topics = "payment-requests", groupId = "payment-group")
//    public void handlePayment(PaymentRequest request) {
//        try {
//            log.info("Processing payment request: {}", request);
//            paymentService.processPayment(request.getUserId(), request.getAmount());
//        } catch (Exception e) {
//            log.error("Failed to process payment request: {}", request, e);
//        }
//    }
//
//    @Override
//    @KafkaListener(topics = "payment-cancellations", groupId = "payment-group")
//    public void handleCancellation(PaymentRequest request) {
//        try {
//            log.info("Processing payment cancellation: {}", request);
//            paymentService.cancelPayment(request.getPaymentId(), request.getUserId());
//        } catch (Exception e) {
//            log.error("Failed to process payment cancellation: {}", request, e);
//        }
//    }
//}
