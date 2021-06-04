// championships
INSERT INTO championships(name) VALUES("AP-2019");
INSERT INTO championships(name) VALUES("CL-2020");
INSERT INTO championships(name) VALUES("AP-2020");
INSERT INTO championships(name) VALUES("AP-2021");


// Soccer days
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J1", "2019-08-15T00:00:00.000-05:00", "2019-08-18T00:00:00.000-05:00", 1);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J2", "2019-08-25T00:00:00.000-05:00", "2019-08-26T00:00:00.000-05:00", 1);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J3", "2019-09-03T00:00:00.000-05:00", "2019-09-06T00:00:00.000-05:00", 1);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J1", "2020-02-01T00:00:00.000-05:00", "2019-03-04T00:00:00.000-05:00", 2);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J2", "2020-02-08T00:00:00.000-05:00", "2020-02-13T00:00:00.000-05:00", 2);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J1", "2020-10-16T00:00:00.000-05:00", "2020-10-18T00:00:00.000-05:00", 3);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J1", "2021-01-15T00:00:00.000-05:00", "2021-01-19T00:00:00.000-05:00", 4);
INSERT INTO soccer_days(name, start_date, end_date, championship_id) VALUES("J2", "2021-01-25T00:00:00.000-05:00", "2021-01-27T00:00:00.000-05:00", 4);

// clubs
INSERT INTO clubs(name) VALUES("Pumas UNAM");
INSERT INTO clubs(name) VALUES("Atlas");
INSERT INTO clubs(name) VALUES("Tigres UANL");
INSERT INTO clubs(name) VALUES("Monterrey");
INSERT INTO clubs(name) VALUES("Cruz Azul");
INSERT INTO clubs(name) VALUES("Chivas");
INSERT INTO clubs(name) VALUES("América");

// users
INSERT INTO users(fullname, username, email, password, enabled, created_at) VALUES("Jorge Cervantes", "jorgec", "jorgec@email.com", "$2a$10$pvbW4eP7XKAKnULruVmL8OoTqqeXJrdSWdHp4FbdjB54hz6r/eRwO", true, NOW());
INSERT INTO users(fullname, username, email, password, enabled, created_at) VALUES("Alfredo Talaver", "alfredot", "alfredot@email.com", "$2a$10$.YJvTgfU4tEXax0lyc16TuPD9D.SAi3URGPFyKDz/UlduQWlFign6", true, NOW());
INSERT INTO users(fullname, username, email, password, enabled, created_at) VALUES("Johan Vazquez", "johanv", "johanv@email.com", "$2a$10$Ta2nUP3bWOeIq5Wmf1W2UOzoKNU8JAFpcenCuwzOtH3ThjEl2iBfG", true, NOW());
INSERT INTO users(fullname, username, email, password, enabled, created_at) VALUES("Nicolas Freire", "nicolasf", "nicolas@email.com", "$2a$10$6i37VDDt2L/cMKTm9iXU0uUCdbe108MzffqqWEu3QyfL8bqP7cL0K", false, NOW());

// roles
INSERT INTO roles(name) VALUES("ROLE_ADMIN");
INSERT INTO roles(name) VALUES("ROLE_USER");

// users_roles
INSERT INTO users_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(2, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(3, 1);
INSERT INTO users_roles(user_id, role_id) VALUES(3, 2);
INSERT INTO users_roles(user_id, role_id) VALUES(4, 2);
