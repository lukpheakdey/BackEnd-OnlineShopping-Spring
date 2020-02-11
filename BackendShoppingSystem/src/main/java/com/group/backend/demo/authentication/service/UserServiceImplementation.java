package com.group.backend.demo.authentication.service;

import com.group.backend.demo.authentication.model.User;
import com.group.backend.demo.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Author .OIGO EDWIN
 *  */


@Primary
@Service
public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user != null) return user;
         throw new UsernameNotFoundException("user with username "+ username + "  not found");
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
