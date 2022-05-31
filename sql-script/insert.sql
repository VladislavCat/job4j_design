insert into rules(rule_text) values ('Данному типу пользователей нельзя добавлять файлы к заявкам');
insert into role(name_role) values('Рядовой пользователь');
insert into rules_roles(id_role, id_rules) values(1, 1);
insert into category(name_category) values('Важные ошибки проекта');
insert into state(state_name) values('400');
insert into users(name_user, role_user) values('Junior-dev', 1);
insert into item(item_name, user_item, item_category, item_state) values('SQL-запросы', 1, 1, 1);
insert into comments(text_comment, comment_item) values('SQL-запросы не работают', 1);
insert into attachs(path_file, attach_item) values('-', 1);