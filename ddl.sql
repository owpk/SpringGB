BEGIN;
DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers(name) VALUES ('User1'), ('User2');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), cost INT);
INSERT INTO products (name, cost) VALUES ('Prod1', 200), ('Prod2', 300);

DROP TABLE IF EXISTS cart CASCADE;
CREATE TABLE cart (id bigserial PRIMARY KEY, customer_id bigint, product_id bigint,
FOREIGN KEY(customer_id) REFERENCES customers, FOREIGN KEY(product_id) REFERENCES products);
INSERT INTO cart (customer_id, product_id) VALUES (1,1), (1,2), (2,1), (2,2);

COMMIT;
---------------------------------
-- корзина отдельного пользователя
SELECT customers.name AS customer_name, products.name AS product_name FROM cart
LEFT JOIN customers ON customer_id = customers.id
LEFT JOIN products ON product_id = products.id
WHERE customer_id = 1;

-- общая сумма покупок для каждого пользователя
SELECT customers.name AS customer_name, sum(products.cost) AS total_price FROM cart
LEFT JOIN customers ON customer_id = customers.id
LEFT JOIN products ON product_id = products.id
GROUP BY customers.name;
