package com.example.apollofy.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data

public class UserDTO implements Serializable {
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
