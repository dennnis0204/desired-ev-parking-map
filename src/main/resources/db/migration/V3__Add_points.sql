-- insert into charging_points
--     (id, type_of_current, power, latitude, longitude, user_id)
-- values (1, 'AC', '3-5 kW', 43.33, 25.11, 1);
--
-- insert into charging_points
--     (id, type_of_current, power, latitude, longitude, user_id)
-- values (2, 'DC', '6-16 kW', 51.13, 33.66, 1);
--
-- insert into charging_points
--     (id, type_of_current, power, latitude, longitude, user_id)
-- values (3, 'AC', '3-5 kW', 43.33, 25.11, 2);
--
-- insert into charging_points
--     (id, type_of_current, power, latitude, longitude, user_id)
-- values (4, 'DC', '6-16 kW', 51.13, 33.66, 2);

insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('AC', '3-5 kW', 43.33, 25.11,
        (select u.id from users u where u.email = 'john@gmail.com'));

insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('DC', '6-16 kW', 51.13, 33.66,
        (select u.id from users u where u.email = 'john@gmail.com'));

insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('AC', '3-5 kW', 43.33, 25.11,
        (select u.id from users u where u.email = 'dennnis0204@gmail.com'));

insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('DC', '6-16 kW', 51.13, 33.66,
        (select u.id from users u where u.email = 'dennnis0204@gmail.com'));