package com.softeq.app.adapters.jpa;

import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRepositoryImpl {

    @Autowired
    protected EntityManager entityManager;

    protected <T> JPAQuery<T> query() {
        return new JPAQuery<>(entityManager);
    }

}
