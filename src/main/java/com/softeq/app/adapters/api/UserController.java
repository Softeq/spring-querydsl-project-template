package com.softeq.app.adapters.api;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepository;
import com.softeq.app.services.UserService;
import com.softeq.app.services.commands.UpdateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    List<User> findAll() {
        return userRepository.findAll();
    }

    @PostMapping
    User create(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Long id) {
        return userRepository
            .findById(id)
            .orElseThrow(NoSuchElementException::new);
    }

    @GetMapping(params = "lastName")
    List<User> findByLastName(@RequestParam String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @PutMapping("/{id}")
    User update(@PathVariable Long id, @RequestBody UpdateUserCommand user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<String> onException() {
        return new ResponseEntity<>(NOT_FOUND);
    }

}
