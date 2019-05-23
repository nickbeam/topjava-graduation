package ru.javawebinar.topjava.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.graduation.model.User;
import ru.javawebinar.topjava.graduation.service.UserService;
import ru.javawebinar.topjava.graduation.to.UserTo;
import ru.javawebinar.topjava.graduation.util.UserUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.javawebinar.topjava.graduation.util.UserUtil.createNewFromTo;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(RootRestController.REST_URL)
public class RootRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    final static String REST_URL = "/rest/register";

    private final UserService service;

    @Autowired
    public RootRestController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("Register {}", userTo);
        checkNew(userTo);
        User created = service.create(createNewFromTo(userTo));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
