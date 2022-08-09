package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.repo.CommentRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
@SuppressWarnings("JpaQlInspection")
@RequiredArgsConstructor
public class CommentRepoJpa implements CommentRepo {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment saveOrUpdate(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
        return comment;
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }

    @Override
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Long count() {
        String sql = "select count(c) from Comment c";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}