package com.softeq.app;

import javax.transaction.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
public abstract class AbstractITest {

}