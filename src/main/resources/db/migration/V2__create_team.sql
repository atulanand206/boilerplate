CREATE OR REPLACE FUNCTION fn_team_create(entity_id UUID, body JSONB)
  RETURNS VOID AS
$$
BEGIN
  INSERT INTO teams (id, name)
  VALUES (entity_id, body ->> 'name');
END;
$$
LANGUAGE plpgsql;