package com.theduo.storefront.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @Size(min = 2, max = 255, message = "First name must be between 2 and 255 character long")
    private String firstName;

    @Size(min = 2, max = 255, message = "Last name must be between 2 and 255 character long")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;
}
