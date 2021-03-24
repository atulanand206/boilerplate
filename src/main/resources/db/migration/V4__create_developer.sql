CREATE OR REPLACE FUNCTION fn_developer_create(entity_id UUID, body JSONB)
  RETURNS VOID AS
$$
BEGIN
  INSERT INTO developers (id, team_id, name, phone_number)
  VALUES (entity_id, (body ->> 'team_id')::UUID, body ->> 'name', body ->> 'phone_number');
END;
$$
LANGUAGE plpgsql;