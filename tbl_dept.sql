create sequence dept_id_seq
	INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table tbl_dept
(
	dept_id integer not null default nextval ('dept_id_seq'::regclass),
	dept_code text not null,
	dept_name text not null,
	fy bigint not null,	
	last_update timestamp without time zone not null default now(),
	constraint dept_pkey primary key(dept_id)
)

CREATE INDEX dept_name_idx
    ON public.tbl_dept USING btree
    (dept_name)
    TABLESPACE pg_default;

CREATE TRIGGER last_updated
    BEFORE UPDATE 
    ON public.tbl_sch
    FOR EACH ROW
    EXECUTE PROCEDURE public.last_updated();

--already created
CREATE FUNCTION public.last_updated()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
    NEW.last_update = CURRENT_TIMESTAMP;
    RETURN NEW;
END $BODY$;