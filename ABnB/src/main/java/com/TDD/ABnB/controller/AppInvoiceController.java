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
    public ResponseEntity <Iterable<AppInvoice>> showListInvoice() {
        Iterable<AppInvoice> appInvoices = appInvoiceService.findAll();
        return new ResponseEntity<>(appInvoices, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppInvoice> showInvoice(@PathVariable("id") Long id) {
        AppInvoice appInvoice = appInvoiceService.findById(id);
        return new ResponseEntity<>(appInvoice, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity <AppInvoice> createInvoice(@RequestBody AppInvoice appInvoice) {
         appInvoiceService.save(appInvoice);
         return new ResponseEntity<>(appInvoice, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AppInvoice> updateInvoice(@PathVariable("id") Long id, @RequestBody AppInvoice appInvoice) {
        appInvoice.setId(id);
        appInvoiceService.save(appInvoice);
        return new ResponseEntity<>(appInvoice, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppInvoice> deleteInvoice(@PathVariable("id") Long id) {
        AppInvoice appInvoice = appInvoiceService.delete(id);
        return new ResponseEntity<AppInvoice>(appInvoice, HttpStatus.ACCEPTED);

    }


}
