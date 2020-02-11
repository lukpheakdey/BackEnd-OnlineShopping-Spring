package com.group.backend.demo.authentication.service;



import com.group.backend.demo.authentication.AuthenticationConstants;
import com.group.backend.demo.authentication.model.Success;
import com.group.backend.demo.authentication.model.VendorForm;
import com.group.backend.demo.authentication.repository.Vendor;
import com.group.backend.demo.authentication.repository.VendorRepository;
import com.group.backend.demo.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendorService {

    @Value("${payment.amount}")
    private double PAYMENT_AMOUNT; //payment amount changes, we don't have to recompile the application.


    @Autowired
    private VendorRepository vendorRepository;


    @Autowired
    private IPaymentService paymentService;


    public ResponseEntity<Success> saveAndPay(VendorForm form) {
        if(!paymentService.makePayment(form.getCardNo(), form.getCardName(),form.getExpirationDate(), form.getCcv(), form.getCardType(),PAYMENT_AMOUNT))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Success(AuthenticationConstants.PAYMENT_ERROR));
        Vendor vendor = form.getVendor();
        vendor.addAddress(form.getAddress());
        vendorRepository.save(vendor);
        return ResponseEntity.ok(new Success("User registered successfully!"));
    }



}
