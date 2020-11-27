CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date time without time zone NOT NULL,
    user_id varchar(36) NOT NULL REFERENCES user_entity (id)
);

ALTER TABLE IF EXISTS user_entity ADD state varchar(36) NOT NULL DEFAULT 'OK';

INSERT INTO locations(latitude,longitude,location_date, user_id) VALUES(43.63746472422702,3.8409670228559136,now(),'d4b30e68-db1d-4d0d-8423-7800e017df47');
INSERT INTO locations(latitude,longitude,location_date, user_id) VALUES(43.63746472422702,5.8409670228559136,now(),'d4b30e68-db1d-4d0d-8423-7800e017df47');
