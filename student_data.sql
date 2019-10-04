--sequence
create sequence student_id_seq
	INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

--table
create table student_data
(
	student_id integer not null default nextval ('student_id_seq'::regclass),
	first_name text not null,
	last_name text not null,
	enroll_fy bigint not null,
	sch_code text not null,
	sch_name text not null,
	last_update timestamp without time zone not null default now(),
	constraint student_pkey primary key(student_id)
)

--index
CREATE INDEX stu_last_name_idx
    ON public.student_data USING btree
    (last_name)
    TABLESPACE pg_default;

--trigger
CREATE TRIGGER last_updated
    BEFORE UPDATE 
    ON public.student_data
    FOR EACH ROW
    EXECUTE PROCEDURE public.last_updated();

--trigger function
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