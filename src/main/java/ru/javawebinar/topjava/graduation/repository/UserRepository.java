package ru.javawebinar.topjava.graduation.repository;

import ru.javawebinar.topjava.graduation.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    List<User> getAll();

    // null if not found
    User getByEmail(String email);

    default User getWithDishes(int id) {
        throw new UnsupportedOperationException();
    }

    default User getWithVotes(int id) {
        throw new UnsupportedOperationException();
    }
}