package com.healthcare.service;

import com.healthcare.entity.Payment;

public interface PaymentService {
    Payment process(Payment p);
}
