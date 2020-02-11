package com.group.backend.demo.admin;

import com.group.backend.demo.authentication.model.Success;
import com.group.backend.demo.authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AdminController {


    @Autowired
        private Adminservice service;



        @GetMapping(Constants.VENDORS)
        public List<VendorResponse> fetchVendor() {
            return service.fetchVendor();
        }



        @GetMapping(Constants.USERS)
        public List<User> users() {
            return service.fetchUsers();
        }



        @PostMapping(Constants.VENDORS_STATUS)
        public ResponseEntity<Success> changeVendorStatus(@RequestBody Status status) {
            service.changeStatus(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Success(Constants.SUCCESS_SMS));

        }

        @PostMapping(Constants.USERS_STATUS)
        public ResponseEntity<Success> changeUserStatus(@RequestBody Status status){
            service.changeStatus(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Success(Constants.SUCCESS_SMS));

        }

        @PostMapping(Constants.PRODUCT_STATUS)
        public ResponseEntity<Success> updateProductStatus(@RequestBody Status status){
            service.changeProduct(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Success(Constants.SUCCESS_SMS));

        }

}
