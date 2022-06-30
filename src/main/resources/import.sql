INSERT INTO tb_category (category_name) VALUES ('Smartphones');
INSERT INTO tb_category (category_name) VALUES ('TV');

INSERT INTO tb_product (product_name, price) VALUES ('Iphone', 5455.00);
INSERT INTO tb_product (product_name, price) VALUES ('Tv Full HD 5K LG', 4999.99);
INSERT INTO tb_product (product_name, price) VALUES ('Tv Full HD 5K Samsung', 5999.99);
INSERT INTO tb_product (product_name, price) VALUES ('Tv Full HD 5K Sony', 6999.99);
INSERT INTO tb_product (product_name, price) VALUES ('Motorola Smart Plus', 2450.00);
INSERT INTO tb_product (product_name, price) VALUES ('Samsung Galaxy Ultra', 3380.00);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (4, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (5, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (6, 1);

