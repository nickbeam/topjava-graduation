package ru.javawebinar.topjava.graduation.service;

import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javawebinar.topjava.graduation.model.User;

import static ru.javawebinar.topjava.graduation.UserTestData.*;

@SpringJUnitConfig(locations = "classpath:spring/spring-app-test.xml")
@SqlGroup({
        @Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8")),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/populateDB.sql")
})

class UserServiceTest {

    @Autowired
    private UserService service;

    @BeforeEach
    private void setUp() {

    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), USER, ADMIN);
    }

    @Test
    public void getByEmail() throws Exception {
        assertMatch(service.getByEmail(USER.getEmail()), USER);
    }

    @Test
    public void create() {
        service.create(NEWUSER);
        assertMatch(service.getAll(), USER, ADMIN, NEWUSER);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    public void update() throws Exception{
        User user = service.get(USER_ID);
        user.setEmail("changed@yandex.ru");
        service.update(user);
        assertMatch(service.get(USER_ID), user);
    }
}