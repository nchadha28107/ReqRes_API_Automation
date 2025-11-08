package com.reqres.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String username;
    private String email;
    private String password;
    private String id;
    private String createdAt;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}