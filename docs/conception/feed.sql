
insert into `USERS` values
(1, "demo@demo.fr", "demo", "{bcrypt}$2a$10$1HD4Rf.AlnHxya5Qeb3Tsuy.2NjuQUkD37bPm3LFY0g/tZUWGgcTi", now(), now()), -- password demo
(2, "greg@greg.fr", "greg", "{bcrypt}$2a$10$AuhyRVV4xknRrqCjWYkVR.TEGBCOcyr4/zH66cZHGHTHFnQnMLRRO", now(), now()) -- password greg
;

insert into `RENTALS` values
(1, "villa à la plage", 99.5, 100000, "https://picsum.photos/seed/1/200/300", "villa à la plage", 1, now(), now()),
(2, "villa à la montagne", 99.5, 100000, "https://picsum.photos/seed/2/200/300", "villa à la montagne", 1, now(), now()),
(3, "cabane à la plage", 99.5, 100000, "https://picsum.photos/seed/3/200/300", "cabane à la plage", 2, now(), now()),
(4, "cabane à la montagne", 99.5, 100000, "https://picsum.photos/seed/4/200/300", "cabane à la montagne", 2, now(), now());

insert into `MESSAGES` values
(1, 1, 2, "super villa à la plage !", now(), now());