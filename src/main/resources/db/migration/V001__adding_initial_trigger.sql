CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE OR REPLACE FUNCTION creation_timestamp_handler()
  returns trigger
AS
$body$
BEGIN
  new.created_at := NOW();
  new.updated_at := NOW();
  return new;
END;
$body$
language plpgsql;

CREATE OR REPLACE FUNCTION updation_timestamp_handler()
  returns trigger
AS
$body$
BEGIN
  new.updated_at := NOW();
  return new;
END;
$body$
language plpgsql;
