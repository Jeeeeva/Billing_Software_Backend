package com.example.Billingsoftware.Service;

import com.example.Billingsoftware.io.OrderRequest;
import com.example.Billingsoftware.io.OrderResponse;
import com.example.Billingsoftware.io.PaymentVerificationRequest;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {

   OrderResponse createOrder(OrderRequest orderRequest);
   void deleteOrder(String orderId);

   List<OrderResponse> getLatestOrder();

   OrderResponse verifyPayment(PaymentVerificationRequest request);

   Double sumSalesByDate(LocalDate date);

   Long countByOrderDate(LocalDate date);

   List<OrderResponse> findRecentOrder();
}

