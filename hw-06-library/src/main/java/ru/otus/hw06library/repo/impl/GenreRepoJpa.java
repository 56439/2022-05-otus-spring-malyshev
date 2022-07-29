package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw06library.model.Genre;
import ru.otus.hw06library.repo.GenreRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
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
    public Long count() {
        String sql = "select count(g) from Genre g";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }
}