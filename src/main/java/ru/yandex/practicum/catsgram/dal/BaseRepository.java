package ru.yandex.practicum.catsgram.dal;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import ru.yandex.practicum.catsgram.exception.CatsgramInternalServerException;

import java.sql.PreparedStatement;
import java.sql.Statement;
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

    protected Long insert(String query, Object... params) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbc.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps;
        }, generatedKeyHolder);
        Long id = generatedKeyHolder.getKeyAs(Long.class);
        if (id != null) {
            return id;
        } else {
            throw new CatsgramInternalServerException("Fail save data.");
        }
    }
}
