package com.softeq.app.services;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepository;
import com.softeq.app.services.commands.UpdateUserCommand;
import com.softeq.app.services.commands.UserCommandMapperImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Autowired
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        service = new UserService();
        service.userRepository = prepareRepo(repository);
        service.commandMapper = new UserCommandMapperImpl();
    }

    @Test
    void testMerge() {
        UpdateUserCommand command = new UpdateUserCommand();
        command.setFirstName("John");
        command.setLastName("Doe");

        service.update(1L, command);

        verify(repository).save(any());
    }

    private UserRepository prepareRepo(UserRepository repository) {
        Optional<User> result = Optional.of(new User());
        doReturn(result).when(repository).findById(eq(1L));

        return repository;
    }

}