package com.jtsp.springaopdemo.entity;


import jakarta.validation.constraints.*;
import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    @NotEmpty // make sure string or ds has content
    private String name;
    @NotBlank // applies to String, make sure it's more than white space
    @Size(min = 8, max = 200, message = "username length doesn't meet requirement")
    private String username;
    @Email
    private String email;
    @NotNull
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
