CREATE TABLE companies(
  id SERIAL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL UNIQUE
);

CREATE TABLE super_heroes(
  id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL UNIQUE,
  alter_ego CHARACTER VARYING(255) NOT NULL,
  company_id INTEGER NOT NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT super_heroes_fkey_company_name FOREIGN KEY (company_id)
        REFERENCES companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE trigger super_heroes_creation_timestamp_trigger
   BEFORE INSERT ON super_heroes
   for each row EXECUTE procedure creation_timestamp_handler();
   
CREATE trigger super_heroes_updation_timestamp_trigger
   BEFORE UPDATE ON super_heroes
   for each row EXECUTE procedure updation_timestamp_handler();
