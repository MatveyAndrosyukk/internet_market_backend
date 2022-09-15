SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));

CREATE TABLE IF NOT EXISTS users
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dishes
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    count BIGINT NOT NULL,
    description VARCHAR(3000) NOT NULL,
    image longblob,
    price BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    total_price BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS dishes_model
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    count BIGINT NOT NULL,
    description VARCHAR(3000) NOT NULL,
    image text,
    price BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    total_price BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id BIGINT NOT NULL,
    roles VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_dish
(
    user_id BIGINT NOT NULL,
    dish_id BIGINT NOT NULL
);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_role_fk
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_dish
    ADD CONSTRAINT user_role_subscriptions_channel_fk
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_dish
    ADD CONSTRAINT user_role_subscriptions_subscriber_fk
        FOREIGN KEY (dish_id) REFERENCES dishes (id);

INSERT INTO users (id, email, name, password, phone)
VALUES (1, 'matvey@gmail.com', 'Matvey', '$2a$10$YFUqflS0AtOMshvZvol5jOGXnnIh59wu9bK8Y2wgblzubmx84SQkK', '375255198474'),
       (2, 'ivan@gmail.com', 'Ivan', '$2a$10$pviuUtzkIx4FiNvFwFTSTusjh8yw0ejlfRqb50g7Yv.uP971HOCbS', '375255198474');

INSERT INTO user_role (user_id, roles)
VALUES (1, 'ADMIN'), (1, 'USER'), (2, 'USER');

INSERT  INTO dishes (id, category, count, description, image, price, title, total_price)
VALUES (1, 'Еда', 1, 'Рваная говядина, трюфельный майонез, маринованные огурчики, салат Коул Слоу, бекон (275 гр)',
        null, 7, 'Биф-бургер', 7),
       (2, 'Еда', 1, 'Котлета из цыпленка, жаренный сыр, азиатский соус, маринованный огурец, бекон (310 гр)',
        null, 6, 'Чикенбургер', 6),
       (3, 'Еда', 1, 'Говяжья котлета, томат, Чеддер, вишневый BBQ, Халапеньо, маринованный огурец, бекон (360 гр)',
        null, 9, 'Бургер RIB RAW', 9),
       (4, 'Еда', 1, 'Квашеная капуста, Чеддер, пастрами соус, серый хлеб и огурцы на бурбоне (350 гр)',
        null, 6, 'Пастрами сэндвич', 6),
       (5, 'Еда', 1, 'Вегетарианская котлета со вкусом говядины, луковый конфитюр, томат, Чеддер по вашему желанию, вишневый BBQ, Халапеньо, маринованный огурец (360 гр)',
        null, 5, 'Вегетарианский бургер', 5),
       (6, 'Еда', 1, 'Самый вкусный отруб бычка. Локальная говядина, травяной откорм (215/50 гр)',
        null, 20, 'Рибай стейк', 20),
       (7, 'Еда', 1, 'Филе лосося на гриле со сливочным соусом и припущенным шпинатом (210 гр)',
        null, 23, 'Лосось-гриль', 23),
       (8, 'Еда', 1, 'Приготовленный на гриле сибас с легким салатом и соусом Алабама (420 гр)',
        null, 25, 'Сибас на гриле', 25),
       (9, 'Еда', 1, '40 минут в коптильне. Классический сыр в белой плесени, подается с картофельным паем (160 гр)',
        null, 15, 'Копченый камамбер', 15),
       (10, 'Еда', 1, 'Цветная капуста Су-вид с трюфельным майонезом, ароматным маслом и огурцами на бурбоне (245 гр)',
        null, 10, 'Стейк из цветной капусты', 10),
       (11, 'Еда', 1, 'Куриный бульон с лапшой Удон, цыпленком Су-вид и яйцом (360 гр)',
        null, 6, 'Азиатский куриный бульон', 6),
       (12, 'Закуски', 1, 'Узбекские сладкие томаты, страчателла, тартар из персика и домашнее Песто (260 гр)',
        null, 6, 'Томаты со страчетеллой и персиком', 6),
       (13, 'Закуски', 1, 'Картофель фри с сырным и чесночным соусами и острым перцем Халапеньо (250 гр)',
        null, 4, 'Картофель по-техасски', 4),
       (14, 'Закуски', 1, 'Гравлакс из лосося, цитрусовый соус, тартар из персиков и сладкого лука (140 гр)',
        null, 10, 'Лосось и персиковый тартар', 10),
       (15, 'Закуски', 1, 'Копченый говяжий язык и оригинальный соус с тунцом и каперсами (165 гр)',
        null, 15, 'Вителло Тоннато', 15),
       (16, 'Закуски', 1, 'Тигровые креветки в темпуре с кисло-сладким соусом (230 гр)',
        null, 10, 'Креветки-фри', 10),
       (17, 'Закуски', 1, 'Сладкий картофель подается с белым барбекю соусом (200 гр)',
        null, 15, 'Батат-фри с соусом Алабама', 15),
       (18, 'Закуски', 1, 'Салат со свежими овощами и руколой (220 гр)',
        null, 7, 'Легкий салат', 7),
       (19, 'Закуски', 1, 'Салат, сыр, яйцо, сладкие томаты, гренки с традиционным соусом Цезарь (160 гр)',
        null, 8, 'Цезарь', 8),
       (20, 'Закуски', 1, 'Салат Ромен, яйцо, сладкие томаты, гренки, малосольным лососем с традиционным соусом Цезарь (200гр)',
        null, 10, 'Цезарь с лососем', 10),
       (21, 'Закуски', 1, 'Салат Ромен, яйцо, сладкие томаты, гренки, цыпленок Су-вид, традиционный соус Цезарь (220 гр)',
        null, 8, 'Цезарь с цыпленком', 8),
       (22, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 4, 'Лимонад Мохито', 4),
       (23, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 5, 'Айс-кофе Карамель', 5),
       (24, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 5, 'Кофе Двойной Эспрессо', 5),
       (25, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 5, 'Кофе Глясе', 5),
       (26, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 5, 'Кофе Американо', 5),
       (27, 'Напитки', 1, 'Маленький 300 мл, Средний 470 мл',
        null, 5, 'Кофе Капучино', 5),
       (28, 'Напитки', 1, 'Маленькая порция, Средняя порция, Большая порция',
        null, 5, 'Coca-Cola', 5),
       (29, 'Напитки', 1, 'Маленькая порция, Средняя порция, Большая порция',
        null, 6, 'Милкшейк Клубника', 6),
       (30, 'Напитки', 1, 'Маленькая порция, Средняя порция, Большая порция',
        null, 6, 'Милкшейк Попкорн', 6);
