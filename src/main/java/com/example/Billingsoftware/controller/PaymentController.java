package com.example.Billingsoftware.controller;

import com.example.Billingsoftware.Service.OrderService;
import com.example.Billingsoftware.Service.RazorpayService;
import com.example.Billingsoftware.io.OrderResponse;
import com.example.Billingsoftware.io.PaymentRequest;
import com.example.Billingsoftware.io.PaymentVerificationRequest;
import com.example.Billingsoftware.io.RazorpayOrderResponse;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final RazorpayService razorpayService;
    private final OrderService orderService;


    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public RazorpayOrderResponse createRazorpyOrder(@RequestBody PaymentRequest request) throws RazorpayException {
        return razorpayService.createOrder(request.getAmount(),request.getCurrency());
    }

    @PostMapping("/verify")
    public OrderResponse verifyPayment(@RequestBody PaymentVerificationRequest request){
        return orderService.verifyPayment(request);

    }
}
