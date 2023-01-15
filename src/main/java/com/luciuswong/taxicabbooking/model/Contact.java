package com.luciuswong.taxicabbooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    private int contactId;
    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;
    @NotBlank(message="Subject must not be blank")
    @Size(min=5, message="Subject must be at least 5 characters long")
    private String subject;
    @NotBlank(message="Email must not be blank")
    @Email(message="Please provide a valid email address")
    private String email;
    @NotBlank(message="Message must not be blank")
    @Size(min=10, message="Message must be at least 10 characters long")
    private String message;
    private String status;
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST, optional=true)
    @JoinColumn(name="user_id", referencedColumnName="userId", nullable=true)
    private Person person;
}
