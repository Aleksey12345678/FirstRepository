insert into users (id, username, password, email, active,bucket_id)
values (1, 'f', 'f','f@mail.ru', true,null);

insert into user_role (user_id, roles)
values (1,'USER'),(1,'ADMIN');

insert into users (id, username, password, email, active,bucket_id)
values (2, 'd', 'd','d@mail.ru', true,null);

insert into user_role (user_id, roles)
values (2,'USER');

-- insert into users (id, username, password, email, active,bucket_id)
-- values (1, 'f', '$2a$08$QhsScL1xCTWyM6pwEdG6Au0xjn/vx8TAGf1RGr8RZhiLGUrs7bP76','f@mail.ru', true,null);
--
-- insert into user_role (user_id, roles)
-- values (1,'USER'),(1,'ADMIN');
--
-- insert into users (id, username, password, email, active,bucket_id)
-- values (2, 'd', '$2a$08$QhsScL1xCTWyM6pwEdG6Au0xjn/vx8TAGf1RGr8RZhiLGUrs7bP76','d@mail.ru', true,null);
--
-- insert into user_role (user_id, roles)
-- values (2,'USER');