package ru.otus.ivan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.ivan.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}