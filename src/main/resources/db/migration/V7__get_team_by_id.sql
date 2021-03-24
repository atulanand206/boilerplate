CREATE OR REPLACE FUNCTION public.fn_team_by_id(id VARCHAR)
    RETURNS SETOF teams AS
$$
DECLARE
    result teams;
BEGIN
    EXECUTE FORMAT(
            'SELECT * from teams where id = %L',
            name) INTO STRICT result;
    RETURN NEXT result;
EXCEPTION
    WHEN TOO_MANY_ROWS THEN
        RAISE EXCEPTION 'id must be unique in the table.';
    WHEN NO_DATA_FOUND THEN
        RETURN;
END;
$$ LANGUAGE plpgsql;