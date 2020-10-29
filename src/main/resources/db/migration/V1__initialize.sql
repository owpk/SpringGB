create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('SOMETHING'), ('ROLE_ANONYMOUS');

insert into users (username, password, email)
values
('user', '$2y$11$H7RMGWLES6N5MJJYChfu2uXt.agiR5klltgqxw8jvpY6PY/CGTX8e', 'user@gmail.com');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2);

create table categories (
    id                      bigserial primary key,
    title                   varchar(255) not null
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int,
    category_id             bigint references categories(id)
);



create table orders (
    id                      bigserial primary key,
    user_id                 bigint references users(id),
    price                   int,
    address                 varchar(1000)
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into categories (title)
values
('Food'),
('Clothes');

insert into products (title, price, category_id)
values
('Bread1', 1, 1),
('Bread2', 2, 1),
('Bread3', 3, 1),
('Bread4', 32, 1),
('Bread5', 42, 1),
('Bread6', 52, 1),
('Bread7', 32, 1),
('Bread8', 42, 1),
('Bread9', 552, 1),
('Bread10', 32, 1),
('Shirt1', 122, 2),
('Shirt12', 312, 2),
('Shirt13', 22, 2),
('Shirt14', 432, 2),
('Shirt15', 532, 2),
('Shirt16', 542, 2),
('Shirt17', 232, 2),
('Shirt18', 22, 2),
('Shirt19', 22, 2),
('Shirt20', 22, 2),
('Shirt21', 22, 2),
('Shirt22', 22, 2),
('Shirt23', 22, 2),
('Shirt24', 22, 2),
('Shirt25', 22, 2);
