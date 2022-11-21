insert into GENRES (name) values ('genre');
insert into GENRES (name) values ('Роман');
insert into GENRES (name) values ('Повесть');
insert into GENRES (name) values ('Исторический');
insert into GENRES (name) values ('Поэма');
insert into GENRES (name) values ('Поэзия');

insert into AUTHORS (name) values ('author');
insert into AUTHORS (name) values ('Александр Пушкин');
insert into AUTHORS (name) values ('Владимир Маяковский');
insert into AUTHORS (name) values ('Сергей Есенин');
insert into AUTHORS (name) values ('Фёдор Достоевский');

insert into BOOKS (title, author_id, genre_id) values ('book', 1, 1);
insert into BOOKS (title, author_id, genre_id) values ('Евгений Онегин', 2, 2);
insert into BOOKS (title, author_id, genre_id) values ('Капитанская дочка', 2, 4);
insert into BOOKS (title, author_id, genre_id) values ('Облако в штанах', 3, 5);
insert into BOOKS (title, author_id, genre_id) values ('Человек', 3, 5);
insert into BOOKS (title, author_id, genre_id) values ('Хорошо!', 3, 3);
insert into BOOKS (title, author_id, genre_id) values ('Черный человек', 4, 6);
insert into BOOKS (title, author_id, genre_id) values ('Страна негодяев', 4, 5);
insert into BOOKS (title, author_id, genre_id) values ('Преступление и наказание', 5, 2);
insert into BOOKS (title, author_id, genre_id) values ('Бесы', 5, 2);
insert into BOOKS (title, author_id, genre_id) values ('Записки из подполья', 5, 3);
insert into BOOKS (title, author_id, genre_id) values ('Двойник', 5, 3);

insert into COMMENTS (entry, book_id) values ('comment 1 for book 1', 1);
insert into COMMENTS (entry, book_id) values ('comment 2 for book 1', 1);
insert into COMMENTS (entry, book_id) values ('comment for book 2', 2);
insert into COMMENTS (entry, book_id) values ('comment for book 3', 3);

insert into USERS (username, password) values ('admin', 'admin');
insert into USERS (username, password) values ('user', 'user');

insert into USER_ROLE (user_id, roles) values (1, 'USER');
insert into USER_ROLE (user_id, roles) values (2, 'ADMIN');

/*------------ ACL ------------*/
INSERT INTO acl_sid (principal, sid) VALUES
    (1, 'admin'),
    (1, 'user');

INSERT INTO acl_class (class) VALUES
    ('ru.otus.ivan.model.Book');

INSERT INTO acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
    (1, 1, NULL, 1, 0),
    (1, 2, NULL, 1, 0),
    (1, 3, NULL, 1, 0),
    (1, 4, NULL, 1, 0),
    (1, 5, NULL, 1, 0),
    (1, 6, NULL, 1, 0),
    (1, 7, NULL, 1, 0),
    (1, 8, NULL, 1, 0),
    (1, 9, NULL, 1, 0),
    (1, 10, NULL, 1, 0),
    (1, 11, NULL, 1, 0),
    (1, 12, NULL, 1, 0);

INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask,
                       granting, audit_success, audit_failure) VALUES
    (1, 1, 1, 1, 1, 1, 1),
    (1, 2, 1, 2, 1, 1, 1),
    (1, 3, 1, 4, 1, 1, 1),
    (1, 4, 1, 8, 1, 1, 1),
    (1, 5, 2, 1, 1, 1, 1),

    (2, 1, 1, 1, 1, 1, 1),
    (2, 2, 1, 2, 1, 1, 1),
    (2, 3, 1, 4, 1, 1, 1),
    (2, 4, 1, 8, 1, 1, 1),
    (2, 5, 2, 1, 1, 1, 1),

    (3, 1, 1, 1, 1, 1, 1),
    (3, 2, 1, 2, 1, 1, 1),
    (3, 3, 1, 4, 1, 1, 1),
    (3, 4, 1, 8, 1, 1, 1),
    (3, 5, 2, 1, 1, 1, 1),

    (4, 1, 1, 1, 1, 1, 1),
    (4, 2, 1, 2, 1, 1, 1),
    (4, 3, 1, 4, 1, 1, 1),
    (4, 4, 1, 8, 1, 1, 1),
    (4, 5, 2, 1, 1, 1, 1),

    (5, 1, 1, 1, 1, 1, 1),
    (5, 2, 1, 2, 1, 1, 1),
    (5, 3, 1, 4, 1, 1, 1),
    (5, 4, 1, 8, 1, 1, 1),
    (5, 5, 2, 1, 1, 1, 1),

    (6, 1, 1, 1, 1, 1, 1),
    (6, 2, 1, 2, 1, 1, 1),
    (6, 3, 1, 4, 1, 1, 1),
    (6, 4, 1, 8, 1, 1, 1),
    (6, 5, 2, 1, 1, 1, 1),

    (7, 1, 1, 1, 1, 1, 1),
    (7, 2, 1, 2, 1, 1, 1),
    (7, 3, 1, 4, 1, 1, 1),
    (7, 4, 1, 8, 1, 1, 1),
    (7, 5, 2, 1, 1, 1, 1),

    (8, 1, 1, 1, 1, 1, 1),
    (8, 2, 1, 2, 1, 1, 1),
    (8, 3, 1, 4, 1, 1, 1),
    (8, 4, 1, 8, 1, 1, 1),
    (8, 5, 2, 1, 1, 1, 1),

    (9, 1, 1, 1, 1, 1, 1),
    (9, 2, 1, 2, 1, 1, 1),
    (9, 3, 1, 4, 1, 1, 1),
    (9, 4, 1, 8, 1, 1, 1),
    (9, 5, 2, 1, 1, 1, 1),

    (10, 1, 1, 1, 1, 1, 1),
    (10, 2, 1, 2, 1, 1, 1),
    (10, 3, 1, 4, 1, 1, 1),
    (10, 4, 1, 8, 1, 1, 1),
    (10, 5, 2, 1, 1, 1, 1),

    (11, 1, 1, 1, 1, 1, 1),
    (11, 2, 1, 2, 1, 1, 1),
    (11, 3, 1, 4, 1, 1, 1),
    (11, 4, 1, 8, 1, 1, 1),
    (11, 5, 2, 1, 1, 1, 1),

    (12, 1, 1, 1, 1, 1, 1),
    (12, 2, 1, 2, 1, 1, 1),
    (12, 3, 1, 4, 1, 1, 1),
    (12, 4, 1, 8, 1, 1, 1),
    (12, 5, 2, 1, 1, 1, 1);