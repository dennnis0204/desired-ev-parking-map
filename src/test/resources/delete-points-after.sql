delete
from charging_points
where user_id = (select u.id from users u where u.email = 'tim@gmail.com');