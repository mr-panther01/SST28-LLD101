package com.example.payments;

import java.util.Map;
import java.util.Objects;

public class OrderService {
    private final Map<String, PaymentGateway> gateways;

    public OrderService(Map<String, PaymentGateway> gateways) {
        this.gateways = Objects.requireNonNull(gateways, "gateways map cannot be null");
    }

    public String charge(String provider, String customerId, int amountCents) {
        PaymentGateway gw = gateways.get(provider);
        if (gw == null) {
            throw new IllegalArgumentException("Unsupported payment provider: " + provider);
        }
        
        return gw.charge(customerId, amountCents);
    }
}