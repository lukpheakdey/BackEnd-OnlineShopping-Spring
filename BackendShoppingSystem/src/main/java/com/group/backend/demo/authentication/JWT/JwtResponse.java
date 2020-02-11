package com.group.backend.demo.authentication.JWT;



import lombok.Data;

import java.io.Serializable;

/*
 * Author .OIGO EDWIN
 *  */

@Data
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String username;
    private String role;
    private long id;

    public JwtResponse(String jwttoken, String username, String role, long id) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.role = role;
        this.id = id;
    }
}
