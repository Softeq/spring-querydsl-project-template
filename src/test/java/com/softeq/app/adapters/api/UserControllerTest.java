package com.softeq.app.adapters.api;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepository;
import com.softeq.app.services.UserService;
import com.softeq.app.services.commands.UpdateUserCommand;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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

    private UserService prepareService(UserService service) {
        lenient()
            .doReturn(new User())
            .when(service)
            .update(anyLong(), any());

        return service;
    }

    private UserRepository prepareRepo(UserRepository repo) {
        List<User> result = singletonList(new User());

        lenient()
            .doReturn(result)
            .when(repo).findAll();

        lenient()
            .doReturn(result)
            .when(repo).findByLastName(any());

        lenient()
            .doReturn(new User())
            .when(repo).save(any());

        lenient()
            .doNothing()
            .when(repo).deleteById(anyLong());

        lenient()
            .doReturn(Optional.of(new User()))
            .when(repo).findById(anyLong());

        return repo;
    }

}
