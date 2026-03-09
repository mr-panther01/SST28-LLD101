package com.example.payments;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, PaymentGateway> gateways = new HashMap<>();
        
        gateways.put("fastpay", new FastPayAdapter(new FastPayClient()));
        gateways.put("safecash", new SafeCashAdapter(new SafeCashClient()));

        OrderService svc = new OrderService(gateways);

        System.out.println("=== Payment Gateway Demo ===");
        
        String id1 = svc.charge("fastpay", "cust-1", 1299);
        String id2 = svc.charge("safecash", "cust-2", 4500);

        System.out.println("FastPay Response:  " + id1);
        System.out.println("SafeCash Response: " + id2);
    }
}
