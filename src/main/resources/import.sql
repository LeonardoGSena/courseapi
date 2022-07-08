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

INSERT INTO tb_state (state_name) VALUES ('São Paulo');
INSERT INTO tb_state (state_name) VALUES ('Minas Gerais');

INSERT INTO tb_city (city_name, state_id) VALUES ('Campinas', 1);
INSERT INTO tb_city (city_name, state_id) VALUES ('Uberlândia', 2);
INSERT INTO tb_city (city_name, state_id) VALUES ('Campos do Jordão', 1);

INSERT INTO tb_client (client_name, email, EIN_or_IDno, client_type) VALUES ('Xuxa da Silva', 'xuxa@gmail.com', '11122244455', 1);

INSERT INTO tb_address (street, number, complement, province, ziP_code, client_id, city_id) VALUES ('Rua das Palmeiras', '100', 'Casa', 'Setúbal', '55444330', 1, 3);
INSERT INTO tb_address (street, number, complement, province, zip_code, client_id, city_id) VALUES ('Rua das flores', '300', 'Ap 888', 'Graças', '88554556', 1, 1);




