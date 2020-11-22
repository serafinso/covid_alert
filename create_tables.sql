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

CREATE TABLE contacts
(
    contact_id serial primary key,
    user_id_positif_1 varchar(36) NOT NULL REFERENCES user_entity (id),
    user_id_contact_2 varchar(36) NOT NULL REFERENCES user_entity (id),
    date_contact time without time zone NOT NULL
);

CREATE TABLE positif
(
    positif_id serial primary key,
    user_id_positif varchar(36) not null references user_entity (id),
    date_positif time without time zone NOT NULL,
    date_contamination time without time zone
);

ALTER TABLE IF EXISTS user_entity ADD userState varchar(36) NOT NULL DEFAULT 'Ok';


INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());
INSERT INTO contacts(user_id_contact_1,user_id_contact_2, date_contact) VALUES('49fa3739-0587-4e58-8f2a-be3ea23929d8','49fa3739-0587-4e58-8f2a-be3ea23929d8',now());