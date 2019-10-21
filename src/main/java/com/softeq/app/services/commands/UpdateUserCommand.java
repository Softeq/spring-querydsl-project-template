package com.softeq.app.services.commands;

import lombok.Data;

@Data
public class UpdateUserCommand {
    private String firstName;
    private String lastName;
}
