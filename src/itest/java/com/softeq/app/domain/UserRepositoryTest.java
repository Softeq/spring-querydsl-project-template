package com.softeq.app.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.softeq.app.AbstractITest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends AbstractITest {

    @Autowired
    UserRepository userRepository;

    @Test
    void testCreateUser() {
        User newUser = createNewUser();

        long userId = newUser.getId();
        User user = userRepository.findById(userId).orElse(null);

        assertEquals(user.getFirstName(), "John");
        assertEquals(user.getLastName(), "Doe");
    }

    @Test
    void testFindAll() {
        createNewUser();

        List<User> results = userRepository.findAll();

        assert results.size() == 1;
    }

    @Test
    void testFindByLastName() {
        createNewUser();

        List<User> results = userRepository.findByLastName("Doe");

        assertEquals(1, results.size());
    }

    private User createNewUser() {
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");

        userRepository.save(newUser);
        return newUser;
    }

}
