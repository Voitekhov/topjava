package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private static final Map<Integer, User> REPOSITORY = new ConcurrentHashMap<>();
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return REPOSITORY.remove(id) != null;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(COUNTER.incrementAndGet());
            REPOSITORY.put(user.getId(), user);
            return user;
        } else {
            return REPOSITORY.merge(user.getId(), user, (u1, u2) -> u2);
        }

    }

    @Override
    public User get(int id) {
        if (REPOSITORY.containsKey(id)) {
            return REPOSITORY.get(id);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return new ArrayList<>(REPOSITORY.values());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return REPOSITORY.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
