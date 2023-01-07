package com.luciuswong.taxicabbooking.model;

import com.luciuswong.taxicabbooking.annotation.FieldsValueMatch;
import com.luciuswong.taxicabbooking.annotation.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@FieldsValueMatch.List({
    @FieldsValueMatch(field="email", fieldMatch="confirmEmail", message="Email does not match"),
    @FieldsValueMatch(field="password", fieldMatch="confirmPassword", message="Password does not match")
})
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private int userId;
    @NotBlank(message="Username must not be blank")
    @Size(min=3, message="Username must be at least 3 characters long")
    private String username;
    @NotBlank(message="Email must not be blank")
    @Email(message="Email address is invalid")
    private String email;
    @NotBlank(message="Confirm email must not be blank")
    @Email(message="Confirm email address is invalid")
    @Transient
    private String confirmEmail;
    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String password;
    @NotBlank(message="Confirm password must not be blank")
    @Size(min=5, message="Confirm password must be at least 5 characters long")
    @Transient
    private String confirmPassword;
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, targetEntity=Role.class)
    @JoinColumn(name="role_id", referencedColumnName="roleId", nullable=false)
    private Role role;
    @Valid
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=Address.class)
    @JoinColumn(name="address_id", referencedColumnName="addressId", nullable=false)
    private Address address;
}
