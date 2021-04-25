package com.example.pizza.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

}
