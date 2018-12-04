create database db_demo;
create user 'demo'@'%' identified by 'welcome1';
grant all on db_demo.* to 'demo'@'%';

INSERT INTO `db_demo`.`customer` (`id`, `age`, `email`, `first_name`, `last_name`) VALUES ('0', '29', 'demo@email.org', 'Demo', 'Demo');