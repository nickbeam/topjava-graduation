package ru.javawebinar.topjava.graduation.service;

import ru.javawebinar.topjava.graduation.model.Vote;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {
    Vote create(Vote vote, int restaurantId, int userId);

    List<Vote> findByUserId(int userId);

    Vote findByUserIdAndDate(int userId, LocalDate date) throws NotFoundException;

    List<Vote> findByRestaurantId(int restaurantId);

    Vote findByRestaurantIdAndDate(int restaurantId, LocalDate date) throws NotFoundException;
}
