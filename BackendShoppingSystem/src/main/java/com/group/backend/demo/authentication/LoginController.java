package com.group.backend.demo.authentication;



import com.group.backend.demo.authentication.JWT.JwtRequest;
import com.group.backend.demo.authentication.JWT.JwtResponse;
import com.group.backend.demo.authentication.JWT.JwtTokenUtil;
import com.group.backend.demo.authentication.model.Success;
import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.model.VendorForm;
import com.group.backend.demo.authentication.repository.UserRepository;
import com.group.backend.demo.authentication.service.VendorService;
import com.group.backend.demo.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


/*
 * Author .OIGO EDWIN
 *  */

@RestController

public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private VendorService service;

    @Autowired
    private EmailService  emailService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        User user = repository.findByUsername(authenticationRequest.getUsername());

        //
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getRole(),user.getId()));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> registerUser(@RequestBody User user) throws Exception {
        String pass = user.getPassword();
        if(user.getRole().equals("user"))
            user.setStatus("active");
        else
            user.setStatus("unapproved");
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);

        authenticate(user.getUsername(), pass);

        final UserDetails userDetails = userDetailsService

                .loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        //send email
        emailService.sentRegistrationVerificationEmail(user.getFullName(),user.getEmail());

        System.out.println(user.getEmail());

        return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getRole(), user.getId()));

    }


    @RequestMapping(value = "/regvendor", method = RequestMethod.POST)
    public ResponseEntity<Success> registerVendor(@RequestBody VendorForm form){

        return service.saveAndPay(form);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {

            System.out.println("bad credentials...");
            throw new Exception("INVALID_CREDENTIALS", e);//
        }
    }

}
