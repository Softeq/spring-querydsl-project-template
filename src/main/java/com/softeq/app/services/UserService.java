package com.softeq.app.services;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepository;
import com.softeq.app.services.commands.UpdateUserCommand;
import com.softeq.app.services.commands.UserCommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCommandMapper commandMapper;

    @Transactional
    public User update(Long id, UpdateUserCommand command) {
        User curUser = userRepository
            .findById(id)
            .orElseThrow(NoSuchElementException::new);

        commandMapper.merge(curUser, command);

        return userRepository.save(curUser);
    }

}
