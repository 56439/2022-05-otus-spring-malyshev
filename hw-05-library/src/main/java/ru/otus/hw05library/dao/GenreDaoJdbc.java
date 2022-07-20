package ru.otus.hw05library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.otus.hw05library.model.Genre;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;

    public GenreDaoJdbc(DataSource dataSource, NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("genres")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Genre insert(Genre genre) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(genre);
        Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
        genre.setId(newKey.longValue());

        return genre;
    }

    @Override
    public List<Genre> getAll() {
        String sql = "select id, name from genres";

        return jdbcOperations.query(sql, new GenreMapper());
    }

    @Override
    public Genre getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        String sql = "select g.id, g.name from genres g where g.name = :name";

        Genre genre;
        try {
            genre = jdbcOperations.queryForObject(sql, params, new GenreMapper());
            return genre;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer count() {
        Map<String, Object> params = Collections.emptyMap();
        String sql = "select count(*) from genres";
        return jdbcOperations.queryForObject(sql, params, Integer.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Genre(id, name);
        }
    }
}