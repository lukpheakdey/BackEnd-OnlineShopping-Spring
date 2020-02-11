package com.group.backend.demo.admin;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.repository.UserRepository;
import com.group.backend.demo.authentication.repository.Vendor;
import com.group.backend.demo.authentication.repository.VendorRepository;
import com.group.backend.demo.email.EmailConstants;
import com.group.backend.demo.email.EmailService;
import com.group.backend.demo.vendor.domain.Product;
import com.group.backend.demo.vendor.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RolesAllowed(Constants.ADMIN)
@Service
public class Adminservice {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private VendorRepository repository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private IProductRepository productRepository;


    public List<VendorResponse> fetchVendor() {
        List<Vendor> vendors = repository.findAll();
        List<VendorResponse> response = new ArrayList<>();
        vendors.forEach(e -> {
            VendorResponse response1 = new VendorResponse();
            Optional<User> user = userRepository.findById(e.getUserID());
            if (user.isPresent())
                response1.addUser(user.get());
            response1.addVendor(e);
            response.add(response1);
        });
        return response;

    }

    public void changeProduct(Status status){
        System.out.println(status);
        Optional<Product> prod = productRepository.findById(status.getId());
        if (prod.isPresent()) {
            Product p = prod.get();
            p.setStatus(status.getStatus());
            productRepository.save(p);
        }
        System.out.println(prod);

    }

    public List<User> fetchUsers() {

        List<User> users = userRepository.findAllByRole(Constants.USER_ROLE);

        return users;
    }

    public void changeStatus(Status status) {
        Optional<User> optuser = userRepository.findById(status.getId());
        if (optuser.isPresent()) {
            User user = optuser.get();
            user.setStatus(status.getStatus());
           //send the email when the status changes.
            sendEmail(user, status);
            userRepository.save(user);
        }
    }



    public void sendEmail(User user, Status status){

        SimpleMailMessage message =  new SimpleMailMessage();

        message.setSubject(EmailConstants.SUBJECT);

        message.setTo(user.getEmail());

        switch (user.getRole()){

            case Constants.VENDOR:

                switch (status.getStatus()){

                    case Constants.VENDOR_APPROVED:

                        message.setText(EmailConstants.POSITIVE_BODY + EmailConstants.LINK+ "/n" + " /n" + EmailConstants.COPYRIGHT);

                        break;

                    case Constants.VENDOR_DISAPPROVED:

                        message.setText(EmailConstants.NEGATIVE + "/n" + " /n" + EmailConstants.COPYRIGHT);

                        break;

                        default:

                }

            case Constants.USER_ROLE:

                switch (status.getStatus()){

                    case Constants.USER_ACTIVE:

                        message.setText(EmailConstants.NEGATIVE + "/n" + " /n" + EmailConstants.COPYRIGHT);

                        System.out.println("active!");
                        break;

                    case Constants.USER_BLOCKED:

                        try {
                            emailService.UserBlockedEmail(EmailService.SENDER_ACCOUNTS, user.getFullName() , user.getEmail());
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }

                        break;

                    default:

                }

        }
            //   emailService.sendEmail(message);

    }

}
