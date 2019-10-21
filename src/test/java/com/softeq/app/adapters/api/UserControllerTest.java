package com.softeq.app.adapters.api;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepository;
import com.softeq.app.services.UserService;
import com.softeq.app.services.commands.UpdateUserCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private UserController controller;

    @Mock
    private UserRepository repository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        controller = new UserController();
        controller.userRepository = prepareRepo(repository);
        controller.userService = prepareService(userService);
    }

    @Test
    void testFindAll() {
        List<User> result = controller.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void testFindByLastName() {
        List<User> result = controller.findByLastName("test");

        assertEquals(1, result.size());
    }

    @Test
    void testCreate() {
        User curUser = new User();

        controller.create(curUser);

        verify(repository).save(any());
    }

    @Test
    void testDelete() {
        controller.delete(1L);

        verify(repository).deleteById(anyLong());
    }

    @Test
    void testOnException() {
        ResponseEntity<?> result = controller.onException();

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void testFindById() {
        controller.findById(1L);

        verify(repository).findById(anyLong());
    }

    @Test
    void testUpdate() {
        User result = controller.update(1L, new UpdateUserCommand());

        assertNotNull(result);
        verify(userService).update(eq(1L), any());
    }

    private UserService prepareService(UserService userService) {
        lenient()
            .doReturn(new User())
            .when(userService)
            .update(anyLong(), any());

        return userService;
    }

    private UserRepository prepareRepo(UserRepository repository) {
        List<User> result = singletonList(new User());

        lenient()
            .doReturn(result)
            .when(repository).findAll();

        lenient()
            .doReturn(result)
            .when(repository).findByLastName(any());

        lenient()
            .doReturn(new User())
            .when(repository).save(any());

        lenient()
            .doNothing()
            .when(repository).deleteById(anyLong());

        lenient()
            .doReturn(Optional.of(new User()))
            .when(repository).findById(anyLong());

        return repository;
    }

}
