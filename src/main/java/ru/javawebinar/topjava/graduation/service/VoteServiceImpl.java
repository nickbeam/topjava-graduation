package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.graduation.model.Vote;
import ru.javawebinar.topjava.graduation.repository.RestaurantRepository;
import ru.javawebinar.topjava.graduation.repository.UserRepository;
import ru.javawebinar.topjava.graduation.repository.VoteRepository;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class VoteServiceImpl implements VoteService {
    private final VoteRepository repository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Vote create(Vote vote, int restaurantId, int userId) {
        return null;
    }

    @Override
    public List<Vote> findByUserId(int userId) {
        return null;
    }

    @Override
    public Vote findByUserIdAndDate(int userId, LocalDate date) throws NotFoundException {
        return null;
    }

    @Override
    public List<Vote> findByRestaurantId(int restaurantId) {
        return null;
    }

    @Override
    public Vote findByRestaurantIdAndDate(int restaurantId, LocalDate date) throws NotFoundException {
        return null;
    }
}
