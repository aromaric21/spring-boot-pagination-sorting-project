DROP TABLE IF EXISTS products;
CREATE TABLE products (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       category VARCHAR(255) NOT NULL,
       price DECIMAL(10, 2) NOT NULL,
       stock_quantity INT NOT NULL
);