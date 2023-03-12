insert into level (name) values ('NOOB');
insert into level (name) values ('BEGINNER');
insert into level (name) values ('NOVICE');
insert into level (name) values ('INTERMEDIATE');
insert into level (name) values ('ADVANCED');
insert into level (name) values ('EXPERT');
insert into level (name) values ('MASTER');
insert into level (name) values ('LEGEND');
insert into level (name) values ('PRO');
insert into level (name) values ('UNSTOPPABLE');
insert into level (name) values ('IMMORTAL');
insert into level (name) values ('INVINCIBLE');
insert into level (name) values ('GODLIKE');

insert into game (name) values ('Fortnite');
insert into game (name) values ('PUBG');
insert into game (name) values ('Call of Duty');
insert into game (name) values ('FIFA');
insert into game (name) values ('Rocket League');
insert into game (name) values ('Counter-Strike');
insert into game (name) values ('Minecraft');
insert into game (name) values ('GTA');
insert into game (name) values ('Apex Legends');
insert into game (name) values ('League of Legends');

insert into location (continent) values ('Asia');
insert into location (continent) values ('Europe');
insert into location (continent) values ('South America');
insert into location (continent) values ('North America');
insert into location (continent) values ('Oceania');
insert into location (continent) values ('Africa');

insert into account (name, nickname, email, password, location_id) values ('John', 'Johny', 'johnjohn@mail.com', '12345678', 1);

insert into account_game (account_id, game_id, level_id) values (1, 1, 3);
insert into account_game (account_id, game_id, level_id) values (1, 2, 5);