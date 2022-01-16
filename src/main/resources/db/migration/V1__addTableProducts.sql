create table categories (
    id              bigserial primary key,
    category_name   varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (category_name)
values
('Milk'),
('Bakery'),
('Fruits'),
('Vegetables'),
('Grocery'),
('Fish'),
('Meat');

create table if not exists products
(
    id bigserial primary key,
    category_id bigint not null references categories (id),
    title varchar(255),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

insert into products (category_id, title, price)
values
(1, 'Milk', 50),
(2, 'Bred', 30),
(5, 'Solt', 15),
(5, 'Soda', 12),
(5, 'Tea', 75),
(3, 'Apple', 55),
(3, 'Banana', 75),
(3, 'Orange', 100),
(4, 'Potato', 60),
(2, 'Cake', 95),
(1, 'Cream', 81),
(6, 'Salmon', 950),
(7, 'Beef', 450),
(7, 'Chiken', 180),
(2, 'Hamburger', 115),
(2, 'Pizza', 355),
(3, 'Grape', 105),
(1, 'Cheese', 515),
(1, 'Butter', 650),
(7, 'Steak', 555),
(3, 'Plum', 74);
