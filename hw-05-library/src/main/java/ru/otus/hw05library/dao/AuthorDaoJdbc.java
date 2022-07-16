package ru.otus.hw05library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.otus.hw05library.model.Author;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;

    public AuthorDaoJdbc(DataSource dataSource, NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("authors")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Author insert(Author author) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(author);
        Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
        author.setId(newKey.longValue());

        return author;
    }

    @Override
    public List<Author> getAll() {
        String sql = "select id, name from authors";

        return jdbcOperations.query(sql, new AuthorMapper());
    }

    @Override
    public Author getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        String sql = "select a.id, a.name from authors a where a.name = :name";

        Author author;
        try {
            author = jdbcOperations.queryForObject(sql, params, new AuthorMapper());
            return author;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer count() {
        Map<String, Object> params = Collections.emptyMap();
        String sql = "select count(*) from authors";
        return jdbcOperations.queryForObject(sql, params, Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            return new Author(id, name);
        }
    }
}