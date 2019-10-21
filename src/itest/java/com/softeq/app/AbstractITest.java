package com.softeq.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public abstract class AbstractITest {
}
