package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw06library.model.Genre;
import ru.otus.hw06library.repo.GenreRepo;

import javax.persistence.*;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
@Transactional
public class GenreRepoJpa implements GenreRepo {

    @PersistenceContext
    private final EntityManager em;
    @Override
    public Genre saveOrUpdate(Genre genre) {
        if (genre.getId() == null) {
            em.persist(genre);
        } else {
            em.merge(genre);
        }
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        String sql = "select g from Genre g";
        return em.createQuery(sql, Genre.class).getResultList();
    }

    @Override
    public Genre getByName(String name) {
        String sql = "select g from Genre g where g.name = :name";
        TypedQuery<Genre> query = em.createQuery(sql, Genre.class)
            .setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long count() {
        String sql = "select count(g) from Genre g";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}