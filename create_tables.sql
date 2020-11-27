CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date timestamp NOT NULL,
    user_id varchar(36) NOT NULL REFERENCES user_entity (id)
);

ALTER TABLE IF EXISTS user_entity ADD state varchar(36) NOT NULL DEFAULT 'OK';