CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date time without time zone NOT NULL
);

CREATE TABLE user_locations
(
    user_id varchar(36) NOT NULL REFERENCES user_entity (id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);

ALTER TABLE IF EXISTS user_entity ADD userState varchar(36) NOT NULL DEFAULT 'OK';


INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());
