package ru.otus.hw06library.repo.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw06library.model.Comment;
import ru.otus.hw06library.repo.CommentRepo;

import javax.persistence.*;
import java.util.List;

@Repository
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
    public boolean deleteById(Long id) {
        String sql = "delete from Comment c where c.id = :id";
        Query query = em.createQuery(sql).setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public List<Comment> getAllByBookId(Long bookId) {
        String sql = "select c from Comment c where c.book.id = :bookId";
        TypedQuery<Comment> query = em.createQuery(sql, Comment.class)
                .setParameter("bookId", bookId);
        List<Comment> comments = query.getResultList();

        if (comments.isEmpty()) {
            return null;
        }

        return comments;
    }

    @Override
    public Comment getById(Long id) {
        String sql = "select c from Comment c where c.id = :id";
        TypedQuery<Comment> query = em.createQuery(sql, Comment.class)
                .setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long count() {
        String sql = "select count(c) from Comment c";
        Query query = em.createQuery(sql);
        return (Long) query.getSingleResult();
    }
}