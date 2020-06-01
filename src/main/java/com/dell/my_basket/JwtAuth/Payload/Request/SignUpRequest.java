package com.dell.my_basket.JwtAuth.Payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String name;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String number;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public SignUpRequest(@NotBlank @Size(max = 50) @Email String email, String name, @NotBlank @Size(min = 6, max = 40) String password, String number) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.number = number;
    }

}
