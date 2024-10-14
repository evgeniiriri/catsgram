package ru.yandex.practicum.catsgram.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.catsgram.exception.CatsgramInternalServerException;

import java.lang.management.OperatingSystemMXBean;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<T> {

    protected final JdbcTemplate jdbc;
    protected final RowMapper<T> mapper;

    protected Optional<T> findOne(String query, Object... params) {
        try {
            T result = jdbc.queryForObject(query, mapper, params);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException ignored) {
            return Optional.empty();
        }
    }

    protected List<T> findAll(String query, Object... params) {
        return jdbc.query(query, mapper, params);
    }

    protected boolean deleteById(String query, Long id) {
        int rowsUpdate = jdbc.update(query, id);
        return rowsUpdate > 0;
    }

    protected void update(String query, Object... params) {
        int rowsUpdate = jdbc.update(query, params);
        if (rowsUpdate == 0) {
            throw new CatsgramInternalServerException("Filed update user with params - [" + params + "]");
        }
    }
}
