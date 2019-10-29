package com.softeq.app.services.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.softeq.app.domain.User;
import org.junit.jupiter.api.Test;

class UserCommandMapperTest {

  private UserCommandMapper mapper = new UserCommandMapperImpl();

  @Test
  void testMerge() {
    User newUser = new User();
    UpdateUserCommand command = new UpdateUserCommand();
    command.setFirstName("John");
    command.setLastName("Doe");

    mapper.merge(newUser, command);

    assertEquals("John", newUser.getFirstName());
    assertEquals("Doe", newUser.getLastName());
  }

}