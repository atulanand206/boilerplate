CREATE OR REPLACE FUNCTION public.fn_team_by_name(name VARCHAR)
    RETURNS SETOF teams AS
$$
DECLARE
    result teams;
BEGIN
    EXECUTE FORMAT(
            'SELECT * from teams where name = %L',
            name) INTO STRICT result;
    RETURN NEXT result;
EXCEPTION
    WHEN TOO_MANY_ROWS THEN
        RAISE EXCEPTION 'name must be unique in the table.';
    WHEN NO_DATA_FOUND THEN
        RETURN;
END;
$$ LANGUAGE plpgsql;