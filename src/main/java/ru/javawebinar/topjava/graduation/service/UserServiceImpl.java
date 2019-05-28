package ru.javawebinar.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.graduation.repository.UserRepository;
import ru.javawebinar.topjava.graduation.to.UserTo;
import ru.javawebinar.topjava.graduation.util.exception.NotFoundException;
import ru.javawebinar.topjava.graduation.web.AuthorizedUser;
import ru.javawebinar.topjava.graduation.model.User;
import java.util.List;

import static ru.javawebinar.topjava.graduation.util.UserUtil.prepareToSave;
import static ru.javawebinar.topjava.graduation.util.UserUtil.updateFromTo;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        if (user.getId() == null) {
            throw new NotFoundException("user must be present in database");
        }
        checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}