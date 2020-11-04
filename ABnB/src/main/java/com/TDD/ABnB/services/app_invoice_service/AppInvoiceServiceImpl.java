package com.TDD.ABnB.services.app_invoice_service;

import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.repositories.AppInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppInvoiceServiceImpl implements AppInvoiceService {

    @Autowired
    private AppInvoiceRepository appInvoiceRepository;

    @Override
    public Iterable<AppInvoice> findAll() {
        return appInvoiceRepository.findAll();
    }

    @Override
    public AppInvoice findById(Long id) {
        return appInvoiceRepository.findById(id).get();
    }

    @Override
    public AppInvoice save(AppInvoice appInvoice) {
        return appInvoiceRepository.save(appInvoice);
    }

    @Override
    public AppInvoice delete(Long id) {
        AppInvoice appInvoice = appInvoiceRepository.findById(id).get();
        appInvoiceRepository.delete(appInvoice);
        return appInvoice;
    }
}
