package ru.yandex.practicum.catsgram.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.catsgram.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resutSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resutSet.getLong("id"));
        user.setUsername(resutSet.getString("username"));
        user.setEmail(resutSet.getString("email"));
        user.setPassword(resutSet.getString("password"));

        Timestamp registrationDate = resutSet.getTimestamp("registration_date");
        user.setRegistrationDate(registrationDate.toInstant());

        return user;
    }
}
