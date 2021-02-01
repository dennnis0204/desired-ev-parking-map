
insert into users
    (id, email, name, provider)
    values (1, 'john@gmail.com', 'john', 'google');

insert into charging_points
    (id, type_of_current, power, latitude, longitude, user_id)
    values (1, 'AC', '3-5 kW', 43.33, 25.11, 1);

insert into charging_points
    (id, type_of_current, power, latitude, longitude, user_id)
    values (2, 'DC', '6-16 kW', 51.13, 33.66, 1);