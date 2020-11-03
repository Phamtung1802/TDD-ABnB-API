package com.TDD.ABnB.services.app_invoice_service;

import com.TDD.ABnB.models.AppInvoice;
import org.springframework.stereotype.Service;

@Service
public interface AppInvoiceService {
    Iterable<AppInvoice> findAll();

    AppInvoice findById(Long id);

    AppInvoice save(AppInvoice appInvoice);

    void remove(AppInvoice appInvoice);
}
