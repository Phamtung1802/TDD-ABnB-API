package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.services.app_invoice_service.AppInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/invoices"})
public class AppInvoiceController {

    @Autowired
    private AppInvoiceService appInvoiceService;

    @GetMapping()
    public Iterable<AppInvoice> showListInvoice() {
        return appInvoiceService.findAll();
    }

    @GetMapping("/{id}")
    public AppInvoice showInvoice(@PathVariable("id") Long id) {
        return appInvoiceService.findById(id);
    }

    @PostMapping()
    public AppInvoice createInvoice(@RequestBody AppInvoice appInvoice) {
        return appInvoiceService.save(appInvoice);
    }

    @PutMapping("/{id}")
    public AppInvoice updateInvoice(@PathVariable("id") Long id, @RequestBody AppInvoice appInvoice) {
        appInvoice.setId(id);
        return appInvoiceService.save(appInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppInvoice> deleteInvoice(@PathVariable("id") Long id) {
        AppInvoice appInvoice = appInvoiceService.delete(id);
        return new ResponseEntity<AppInvoice>(appInvoice, HttpStatus.ACCEPTED);

    }


}
