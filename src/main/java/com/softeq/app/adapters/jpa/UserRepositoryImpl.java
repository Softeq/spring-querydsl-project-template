package com.softeq.app.adapters.jpa;

import com.softeq.app.domain.User;
import com.softeq.app.domain.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.softeq.app.domain.QUser.user;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl implements UserRepositoryCustom {

    @Override
    public List<User> findByLastName(String lastName) {
        return query()
            .select(user)
            .from(user)
            .where(user.lastName.eq(lastName))
            .fetch();
    }

}
