package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw06library.model.Author;
import ru.otus.hw06library.repo.AuthorRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
public class AuthorRepoJpa implements AuthorRepo {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author saveOrUpdate(Author author) {
        if (author.getId() == null) {
            em.persist(author);
        } else {
            em.merge(author);
        }
        return author;
    }

    @Override
    public Long count() {
        String sql = "select count(a) from Author a";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }
}