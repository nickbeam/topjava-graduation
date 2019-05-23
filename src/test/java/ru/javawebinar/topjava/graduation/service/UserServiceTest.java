package ru.javawebinar.topjava.graduation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.graduation.model.User;

import static ru.javawebinar.topjava.graduation.UserTestData.*;

class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void get() {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

}