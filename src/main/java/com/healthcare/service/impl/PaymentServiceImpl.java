package com.healthcare.service.impl;

import com.healthcare.entity.Payment;
import com.healthcare.repository.PaymentRepository;
import com.healthcare.service.PaymentService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    public PaymentServiceImpl(PaymentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Payment process(Payment p) {
        // simulate success
        p.setPaymentStatus(Payment.Status.SUCCESS);
        p.setTransactionId("TXN-" + UUID.randomUUID());
        return repo.save(p);
    }
}
