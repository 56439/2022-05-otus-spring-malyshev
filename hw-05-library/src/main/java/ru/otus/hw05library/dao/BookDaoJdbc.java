package ru.otus.hw05library.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.otus.hw05library.model.Author;
import ru.otus.hw05library.model.Book;
import ru.otus.hw05library.model.Genre;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao{

    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert jdbcInsert;

    public BookDaoJdbc(DataSource dataSource, NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("books")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Book insert(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("authorId", book.getAuthor().getId())
                .addValue("genreId", book.getGenre().getId());

        Number newKey = jdbcInsert.executeAndReturnKey(parameterSource);
        book.setId(newKey.longValue());
        return book;
    }

    @Override
    public boolean deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "delete from books where id=:id";

        return jdbcOperations.update(sql, params) != 0;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b.id, b.name, b.author_id, b.genre_id, a.name as author_name, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id";

        return jdbcOperations.query(sql, new BookMapper());
    }

    @Override
    public Book getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = "select b.id, b.name, b.author_id, b.genre_id, a.name as author_name, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id " +
                "where b.id = :id";

        Book book;
        try {
            book = jdbcOperations.queryForObject(sql, params, new BookMapper());
            return book;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        String sql = "select b.id, b.name, b.author_id, b.genre_id, a.name as author_name, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id " +
                "where b.name = :name";

        Book book;
        try {
            book = jdbcOperations.queryForObject(sql, params, new BookMapper());
            return book;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getByAuthor(String author) {
        Map<String, Object> params = Collections.singletonMap("author_name", author);
        String sql = "select b.id, b.name, b.author_id, b.genre_id, a.name as author_name, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id " +
                "where a.name = :author_name";

        return jdbcOperations.query(sql, params, new BookMapper());
    }

    @Override
    public List<Book> getByGenre(String genre) {
        Map<String, Object> params = Collections.singletonMap("genre_name", genre);
        String sql = "select b.id, b.name, b.author_id, b.genre_id, a.name as author_name, g.name as genre_name " +
                "from books b join authors a on b.author_id = a.id " +
                "join genres g on b.genre_id = g.id " +
                "where g.name = :genre_name";

        return jdbcOperations.query(sql, params, new BookMapper());
    }

    @Override
    public Integer count() {
        Map<String, Object> params = Collections.emptyMap();
        String sql = "select count(*) from books";
        return jdbcOperations.queryForObject(sql, params, Integer.class);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");

            long authorId = rs.getInt("author_id");
            String authorName = rs.getString("author_name");

            long genreId = rs.getInt("genre_id");
            String genreName = rs.getString("genre_name");

            return new Book(id, name, new Author(authorId, authorName), new Genre(genreId, genreName));
        }
    }
}