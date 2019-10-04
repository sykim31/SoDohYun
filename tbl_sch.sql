create sequence sch_id_seq
	INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table tbl_sch
(
	sch_id integer not null default nextval ('sch_id_seq'::regclass),
	sch_code text not null,
	sch_name text not null,
	fy bigint not null,	
	last_update timestamp without time zone not null default now(),
	constraint sch_pkey primary key(sch_id)
)

CREATE INDEX sch_name_idx
    ON public.tbl_sch USING btree
    (sch_name)
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