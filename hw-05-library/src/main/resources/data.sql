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

insert into BOOKS (name, author_id, genre_id) values ('book', 1, 1);
insert into BOOKS (name, author_id, genre_id) values ('Евгений Онегин', 2, 2);
insert into BOOKS (name, author_id, genre_id) values ('Капитанская дочка', 2, 4);
insert into BOOKS (name, author_id, genre_id) values ('Облако в штанах', 3, 5);
insert into BOOKS (name, author_id, genre_id) values ('Человек', 3, 5);
insert into BOOKS (name, author_id, genre_id) values ('Хорошо!', 3, 3);
insert into BOOKS (name, author_id, genre_id) values ('Черный человек', 4, 6);
insert into BOOKS (name, author_id, genre_id) values ('Страна негодяев', 4, 5);
insert into BOOKS (name, author_id, genre_id) values ('Преступление и наказание', 5, 2);
insert into BOOKS (name, author_id, genre_id) values ('Бесы', 5, 2);
insert into BOOKS (name, author_id, genre_id) values ('Записки из подполья', 5, 3);
insert into BOOKS (name, author_id, genre_id) values ('Двойник', 5, 3);