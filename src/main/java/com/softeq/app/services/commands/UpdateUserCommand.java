package com.softeq.app.services.commands;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserCommand {

  private String firstName;
  private String lastName;
}
