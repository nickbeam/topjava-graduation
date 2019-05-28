package ru.javawebinar.topjava.graduation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javawebinar.topjava.graduation.model.User;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.javawebinar.topjava.graduation.UserTestData.*;

@SpringJUnitConfig(locations = "classpath:spring/spring-app-test.xml")
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() throws Exception {
        Objects.requireNonNull(cacheManager.getCache("users")).clear();
    }

    @Test
    void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user, ADMIN);
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), USER, ADMIN);
    }

    @Test
    void getByEmail() throws Exception {
        assertMatch(service.getByEmail(USER.getEmail()), USER);
    }

    @Test
    void create() {
        service.create(NEWUSER);
        assertMatch(service.getAll(), USER, ADMIN, NEWUSER);
    }

    @Test
    void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    void update() throws Exception {
        User user = service.get(USER_ID);
        user.setEmail("changed@yandex.ru");
        service.update(user);
        assertMatch(service.get(USER_ID), user);
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void getByEmailNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getByEmail("org@org.org"));
    }

    @Test
    void updateWrongUser() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.update(new User()));
    }
}