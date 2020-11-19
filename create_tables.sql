CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date time without time zone NOT NULL
);

CREATE TABLE user_locations
(
    user_id integer NOT NULL REFERENCES users (user_id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);

INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());