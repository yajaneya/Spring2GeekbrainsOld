create table if not exists products
(
    id bigserial primary key,
    title varchar(255),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
    );

insert into products (title, price)
values
('Milk', 50),
('Bred', 30),
('Solt', 15),
('Soda', 12),
('Tea', 75),
('Apple', 55),
('Banana', 75),
('Orange', 100),
('Potato', 60),
('Cake', 95),
('Cream', 81),
('Salmon', 950),
('Beef', 450),
('Chiken', 180),
('Hamburger', 115),
('Pizza', 355),
('Grape', 105),
('Cheese', 515),
('Butter', 650),
('Steak', 555),
('Plum', 74);
