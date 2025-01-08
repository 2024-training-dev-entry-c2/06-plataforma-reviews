CREATE TABLE RESTAURANT (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE MENU (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT(restaurant_id)
);

CREATE TABLE DISH (
    dish_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    menu_id INT,
    FOREIGN KEY (menu_id) REFERENCES MENU(menu_id)
);

CREATE TABLE REVIEW (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT,
    average_rating DECIMAL(3, 2),
    comments TEXT,
    FOREIGN KEY (restaurant_id) REFERENCES RESTAURANT(restaurant_id)
);

CREATE TABLE DISH_REVIEW (
    dish_review_id INT AUTO_INCREMENT PRIMARY KEY,
    dish_id INT,
    average_rating DECIMAL(3, 2),
    comment TEXT,
    taste_rating DECIMAL(3, 2),
    presentation_rating DECIMAL(3, 2),
    FOREIGN KEY (dish_id) REFERENCES DISH(dish_id)
);
