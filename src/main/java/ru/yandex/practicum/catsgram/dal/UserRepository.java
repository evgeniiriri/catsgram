package ru.yandex.practicum.catsgram.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.catsgram.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository<User> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO USERS (username, email, password, registration_date)" +
            "VALUES (?, ?, ?, ?) returning id";
    private static final String UPDATE_QUERY = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

    public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
        super(jdbc, mapper);
    }

    public List<User> findAll() {
        return findAll(FIND_ALL_QUERY);
    }

    public Optional<User> findUserByEmail(String email) {
        return findOne(FIND_BY_EMAIL_QUERY, email);
    }

    public Optional<User> findUserById(Long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }

    public User save(User user) {
        Long id = insert(INSERT_QUERY,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRegistrationDate());
        user.setId(id);
        return user;
    }

    public User update(User user) {
        update(UPDATE_QUERY,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getId());
        return user;
    }
}
