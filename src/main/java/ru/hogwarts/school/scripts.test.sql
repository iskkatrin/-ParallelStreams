SELECT * FROM student
WHERE age BETWEEN 25 AND 30;

SELECT name
FROM student;

SELECT * FROM student WHERE name LIKE '%О%';

SELECT * FROM student WHERE age < id;

SELECT * FROM student ORDER BY age;

create table expenses
(
    id       smallserial primary key not null,
    name     text                                                   not null,
    date     date                                                   not null,
    category text                                                           ,
    amount   integer                                                not null
);

INSERT INTO public.expenses (id, name, date, category, amount) VALUES (1, 'Проезд в автобусе', '2021-01-30', 'Транспорт', 50);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (2, 'Проезд в метро', '2021-01-30', 'Транспорт', 50);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (3, 'Покупка книги', '2021-01-31', 'Прочие покупки', 300);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (4, 'Покупка продуктов', '2021-01-31', 'Покупка продуктов', 450);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (5, 'Поход в кино', '2021-02-01', 'Развлечения', 400);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (6, 'Кофе', '2021-02-01', 'Еда вне дома', 150);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (7, 'Покупка продуктов', '2021-02-02', 'Покупка продуктов', 600);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (8, 'Поход в театр', '2021-02-14', 'Развлечения', 1000);
INSERT INTO public.expenses (id, name, date, category, amount) VALUES (9, 'Цветы', '2021-02-14', null, 500);


SELECT * FROM expenses ORDER BY amount DESC ;
SELECT COUNT(*) FROM expenses ;
SELECT COUNT(category) FROM expenses;
SELECT COUNT(DISTINCT(category)) FROM expenses;
SELECT MIN(amount), MAX(amount), AVG(amount) from expenses;
SELECT SUM(amount) FROM expenses;
SELECT category, COUNT(*) FROM expenses GROUP BY category;
SELECT category, min(amount), max(amount), avg(amount) FROM expenses GROUP BY category;
SELECT category, SUM(amount) FROM expenses GROUP BY category;
SELECT category, COUNT(*) FROM expenses GROUP BY category HAVING COUNT(*) > 1;
SELECT category, SUM(amount) FROM expenses GROUP BY category HAVING SUM(amount) > 1000;
SELECT * FROM expenses LIMIT 4;
SELECT * FROM expenses OFFSET 4;