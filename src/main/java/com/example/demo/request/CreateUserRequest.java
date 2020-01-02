package com.example.demo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username can only have maximum 50 characters")
    @ApiModelProperty(
            example="anderadored",
            notes="Username cannot be empty",
            required=true
    )
    private String username;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Email can only have maximum 100 characters")
    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example="ander@gmail.com",
            notes="Email cannot be empty",
            required=true
    )
    private String email;
    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address can only have maximum 100 characters")
    @ApiModelProperty(
            example="Hanoi",
            notes="Address cannot be empty",
            required=true
    )
    private String address;
}
