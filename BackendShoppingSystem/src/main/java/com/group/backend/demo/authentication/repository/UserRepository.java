package com.group.backend.demo.authentication.repository;

import com.group.backend.demo.authentication.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
 * Author .OIGO EDWIN
 *  */


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByRole(String role);
    List<User> findAll();

    @Query(value="Select u from User u where lower(u.role)='vendor'")
    List<User> findVendor();
}
