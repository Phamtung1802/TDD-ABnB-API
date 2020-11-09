package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppImage;
import com.TDD.ABnB.models.AppInvoice;
import com.TDD.ABnB.services.app_invoice_service.AppInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@CrossOrigin("*")
public class AppInvoiceController {

    @Autowired
    private AppInvoiceService appInvoiceService;

    @GetMapping()
    public ResponseEntity<Iterable<AppInvoice>> showListInvoice() {
        Iterable<AppInvoice> appInvoices=appInvoiceService.findAll();
        ResponseEntity<Iterable<AppInvoice>> res=new ResponseEntity<Iterable<AppInvoice>>(appInvoices, HttpStatus.ACCEPTED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppInvoice> showInvoice(@PathVariable("id") Long id) {
        AppInvoice appInvoice= appInvoiceService.findById(id);
        ResponseEntity<AppInvoice> res=new ResponseEntity<AppInvoice>(appInvoice, HttpStatus.ACCEPTED);
        return res;
    }

    @PostMapping()
    public ResponseEntity<AppInvoice> createInvoice(@RequestBody AppInvoice appInvoice) {
        appInvoiceService.save(appInvoice);
        ResponseEntity<AppInvoice> res=new ResponseEntity<AppInvoice>(appInvoice, HttpStatus.ACCEPTED);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppInvoice> updateInvoice(@PathVariable("id") Long id, @RequestBody AppInvoice appInvoice) {
        appInvoice.setId(id);
        appInvoiceService.save(appInvoice);
        ResponseEntity<AppInvoice> res=new ResponseEntity<AppInvoice>(appInvoice, HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/{id}")
    public  void deleteInvoice(@PathVariable("id") Long id) {
        AppInvoice appInvoice = appInvoiceService.findById(id);
        appInvoiceService.delete(appInvoice);
    }


}
