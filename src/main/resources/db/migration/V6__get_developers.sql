CREATE OR REPLACE FUNCTION public.fn_developers_by_team_id(id UUID)
    RETURNS SETOF developers AS
$$
DECLARE
    result developers;
BEGIN
    EXECUTE FORMAT(
            'SELECT * from developers where team_id = %L',
            id) INTO STRICT result;
    RETURN NEXT result;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN;
END;
$$ LANGUAGE plpgsql;