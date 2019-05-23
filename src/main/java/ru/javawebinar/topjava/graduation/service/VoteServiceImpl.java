package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.model.Vote;
import ru.javawebinar.topjava.graduation.repository.RestaurantRepository;
import ru.javawebinar.topjava.graduation.repository.UserRepository;
import ru.javawebinar.topjava.graduation.repository.VoteRepository;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;
import ru.javawebinar.topjava.graduation.util.exception.TooLateChangeVoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service("voteService")
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
        Assert.notNull(vote, "vote must not be null");
        vote.setDate(LocalDate.now());
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(userId));
        return repository.save(vote);
    }

    public Vote update(Vote vote, int restaurantId) {
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(vote);
    }

    @Override
    public void vote(int userId, int restaurantId) {
        Vote vote = repository.findByUserIdAndDate(userId, LocalDate.now());
        if (vote == null) {
            create(new Vote(), restaurantId, userId);
        } else {
            if (LocalTime.now().isAfter(LocalTime.of(11, 0, 0))) {
                throw new TooLateChangeVoteException("too late change vote");
            }
            update(vote, restaurantId);
        }
    }

    @Override
    public List<Vote> findByUserId(int userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Vote findByUserIdAndDate(int userId, LocalDate date) throws NotFoundException {
        Assert.notNull(date, "date must not be null");
        return repository.findByUserIdAndDate(userId, date);
    }

    @Override
    public List<Vote> findByRestaurantId(int restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

    @Override
    public Vote findByRestaurantIdAndDate(int restaurantId, LocalDate date) throws NotFoundException {
        Assert.notNull(date, "date must not be null");
        return repository.findByRestaurantIdAndDate(restaurantId, date);
    }
}
