insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('AC', '3-5 kW', 43.33, 25.11,
        (select u.id from users u where u.email = 'tim@gmail.com'));

insert into charging_points
    (type_of_current, power, latitude, longitude, user_id)
values ('DC', '6-16 kW', 51.13, 33.66,
        (select u.id from users u where u.email = 'tim@gmail.com'));