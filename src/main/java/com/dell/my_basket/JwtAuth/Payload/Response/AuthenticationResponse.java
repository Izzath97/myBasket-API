package com.dell.my_basket.JwtAuth.Payload.Response;

public class AuthenticationResponse {

    private final String jwt;
    private String type = "Bearer";
    private String name;
    private String email;

    public AuthenticationResponse(String jwt, String type, String name, String email) {
        this.jwt = jwt;
        this.type = type;
        this.name = name;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
