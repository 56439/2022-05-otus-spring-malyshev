package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw06library.model.Book;
import ru.otus.hw06library.repo.BookRepo;

import javax.persistence.*;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
public class BookRepoJpa implements BookRepo {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book saveOrUpdate(Book book) {
        if (book.getAuthor().getId() == null) {
            em.persist(book.getAuthor());
        }
        if (book.getGenre().getId() == null) {
            em.persist(book.getGenre());
        }
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

    @Override
    public boolean deleteById(Long id) {
        String sqlDeleteComments = "delete from Comment c where c.book.id = :bookId";
        Query deleteComments = em.createQuery(sqlDeleteComments).setParameter("bookId", id);
        deleteComments.executeUpdate();

        String sqlDeleteBook = "delete from Book b where b.id =:id";
        Query deleteBook = em.createQuery(sqlDeleteBook).setParameter("id", id);

        return deleteBook.executeUpdate() == 1;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b from Book b";
        return em.createQuery(sql, Book.class).getResultList();
    }

    @Override
    public Book getById(Long id) {
        String sql = "select b from Book b where b.id = :id";
        TypedQuery<Book> query = em.createQuery(sql, Book.class)
                .setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Book getByTitle(String title) {
        String sql = "select b from Book b where b.title = :title";
        TypedQuery<Book> query = em.createQuery(sql, Book.class)
                .setParameter("title", title);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Book> getByAuthor(String authorName) {
        String sql = "select b from Book b where b.author.name = :authorName";
        TypedQuery<Book> query = em.createQuery(sql, Book.class)
                .setParameter("authorName", authorName);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Book> getByGenre(String genreName) {
        String sql = "select b from Book b where b.genre.name = :genreName";
        TypedQuery<Book> query = em.createQuery(sql, Book.class)
                .setParameter("genreName", genreName);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long count() {
        String sql = "select count(b) from Book b";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}