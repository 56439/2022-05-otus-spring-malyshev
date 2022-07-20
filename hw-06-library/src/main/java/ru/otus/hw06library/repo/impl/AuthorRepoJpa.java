package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06library.model.Author;
import ru.otus.hw06library.repo.AuthorRepo;

import javax.persistence.*;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
@Transactional
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
    public List<Author> getAll() {
        String sql = "select a from Author a";
        return em.createQuery(sql, Author.class).getResultList();
    }

    @Override
    public Author getByName(String name) {
        String sql = "select a from Author a where a.name = :name";
        TypedQuery<Author> query = em.createQuery(sql, Author.class)
                .setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long count() {
        String sql = "select count(a) from Author a";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}