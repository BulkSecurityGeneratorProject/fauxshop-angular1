package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by derekzuk on 10/10/17.
 */
public class MessageDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("message")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
