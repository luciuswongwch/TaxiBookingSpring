package com.luciuswong.taxicabbookingrestcaller.model;

import lombok.Data;

@Data
public class Contact {
    private int contactId;
    private String name;
    private String subject;
    private String email;
    private String message;
    private String status;
}
