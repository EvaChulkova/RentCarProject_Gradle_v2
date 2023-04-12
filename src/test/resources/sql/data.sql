INSERT INTO users (id, first_name, last_name, login, password, role)
VALUES (1, 'Ivan', 'Ivanov', 'ivanAdmin@gmail.com', '123', 'ADMIN'),
       (2, 'Anna', 'Sidorova', 'annaAdmin@gmail.com', '123', 'ADMIN'),
       (3, 'Svetlana', 'Petrova', 'sveta@gmail.com', '456', 'CLIENT'),
       (4, 'Inna', 'Nikitina', 'inna@gmail.com', '456', 'CLIENT'),
       (5, 'Nina', 'Monahova', 'nina@gmail.com', '456', 'CLIENT'),
       (6, 'Aleksey', 'Antoch', 'aleks@gmail.com', '456', 'CLIENT'),
       (7, 'Viktor', 'Viktorov', 'viktor@gmail.com', '456', 'CLIENT'),
       (8, 'Petr', 'Sidorov', 'petr@gmail.com', '456', 'CLIENT');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));


INSERT INTO clients (id, birth_date, driving_licence_no, validity, user_id)
VALUES (1, '2002-02-15', 136015, '2038-10-10', (SELECT id FROM users WHERE login = 'sveta@gmail.com')),
       (2, '1984-07-24', 116753, '2027-01-10', (SELECT id FROM users WHERE login = 'inna@gmail.com')),
       (3, '1998-05-18', 144096, '2031-07-19', (SELECT id FROM users WHERE login = 'nina@gmail.com')),
       (4, '2001-11-06', 201410, '2036-04-21', (SELECT id FROM users WHERE login = 'aleks@gmail.com')),
       (5, '1978-04-17', 714002, '2025-09-04', (SELECT id FROM users WHERE login = 'viktor@gmail.com')),
       (6, '1989-08-22', 315698, '2029-09-15', (SELECT id FROM users WHERE login = 'petr@gmail.com'));
SELECT SETVAL('clients_id_seq', (SELECT MAX(id) FROM clients));


INSERT INTO cars (id, brand, color, image, model, price_per_day, seat_amount, status)
VALUES (1, 'Audi', 'BLACK', 'AudiA3Image', 'A3', 5450, 4, 'BOOKED'),
       (2, 'Audi', 'BLACK', 'AudiRS6Image', 'RS6', 9450, 5, 'AVAILABLE'),
       (3, 'Kia', 'WHITE', 'KiaRioImage', 'Rio', 3170, 4, 'AVAILABLE'),
       (4, 'BMW', 'BLACK', 'BMWX5Image', 'X5', 8915, 5, 'IN_CAR_SERVICE'),
       (5, 'Renault', 'BLACK', 'YellowRenaultLoganImage', 'Logan', 2100, 4, 'AVAILABLE'),
       (6, 'Renault', 'YELLOW', 'WhiteRenaultLoganImage', 'Logan', 2150, 4, 'AVAILABLE'),
       (7, 'Mercedes', 'GREY', 'MercedesGLA200Image', 'GLA 200', 8900, 5, 'AVAILABLE'),
       (8, 'Mini', 'RED', 'MiniCooperImage', 'Cooper', 6920, 2, 'BOOKED'),
       (9, 'VW', 'BLACK', 'BlackVWPoloImage', 'Polo', 3450, 4, 'BOOKED'),
       (10, 'VW', 'GREY', 'GreyVWPoloImage', 'Polo', 3460, 4, 'AVAILABLE'),
       (11, 'VW', 'WHITE', 'VWTouaregImage', 'Touareg', 7050, 4, 'BOOKED'),
       (12, 'Toyota', 'BLACK', 'ToyotaCamryImage', 'Camry', 4740, 4, 'AVAILABLE');
SELECT SETVAL('cars_id_seq', (SELECT MAX(id) FROM cars));


INSERT INTO bookings (id, comment, payment_state, rental_finish, rental_start, status, car_id, client_id)
VALUES (1, 'Booking is completed. Nice to meet you again!', 'PAID', '2023-02-17', '2023-02-14', 'COMPLETED', (SELECT id FROM cars WHERE brand = 'Kia' and model = 'Rio' and color = 'WHITE'), (SELECT id FROM clients WHERE driving_licence_no = 116753)),
       (2, 'Booking is approved. To pay...', 'NOT_PAID', '2023-03-19', '2023-03-16', 'APPROVED', (SELECT id FROM cars WHERE brand = 'VW' and model = 'Polo' and color = 'BLACK'), (SELECT id FROM clients WHERE driving_licence_no = 116753)),
       (3, 'Booking is completed. Nice to meet you again!', 'PAID', '2023-02-27', '2023-02-21', 'COMPLETED', (SELECT id FROM cars WHERE brand = 'Mini' and model = 'Cooper' and color = 'RED'), (SELECT id FROM clients WHERE driving_licence_no = 144096)),
       (4, 'Payment was successful! Have a good trip!', 'PAID', '2023-03-18', '2023-03-11', 'APPROVED', (SELECT id FROM cars WHERE brand = 'Mini' and model = 'Cooper' and color = 'RED'), (SELECT id FROM clients WHERE driving_licence_no = 144096)),
       (5, 'Payment was successful! Have a good trip!', 'PAID', '2023-03-21', '2023-03-11', 'APPROVED', (SELECT id FROM cars WHERE brand = 'VW' and model = 'Touareg' and color = 'WHITE'), (SELECT id FROM clients WHERE driving_licence_no = 714002)),
       (6, 'Booking is completed. Nice to meet you again!', 'PAID', '2023-03-10', '2023-03-06', 'COMPLETED', (SELECT id FROM cars WHERE brand = 'Toyota' and model = 'Camry' and color = 'BLACK'), (SELECT id FROM clients WHERE driving_licence_no = 201410)),
       (7, 'Booking in progress. Please, wait for approving', 'NOT_PAID', '2023-04-14', '2023-04-01', 'IN_PROGRESS', (SELECT id FROM cars WHERE brand = 'Mercedes' and model = 'GLA 200' and color = 'GREY'), (SELECT id FROM clients WHERE driving_licence_no = 201410)),
       (8, 'Booking in progress. Please, wait for approving', 'NOT_PAID', '2023-03-20', '2023-03-13', 'IN_PROGRESS', (SELECT id FROM cars WHERE brand = 'Audi' and model = 'A3' and color = 'BLACK'), (SELECT id FROM clients WHERE driving_licence_no = 136015)),
       (9, 'Booking in progress. Please, wait for approving', 'NOT_PAID', '2023-03-27', '2023-03-20', 'IN_PROGRESS', (SELECT id FROM cars WHERE brand = 'VW' and model = 'Polo' and color = 'GREY'), (SELECT id FROM clients WHERE driving_licence_no = 315698));
SELECT SETVAL('bookings_id_seq', (SELECT MAX(id) FROM bookings));