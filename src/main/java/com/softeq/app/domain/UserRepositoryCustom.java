package com.softeq.app.domain;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findByLastName(String lastName);

}
