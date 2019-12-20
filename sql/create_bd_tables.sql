-- DROP DATABASE rules;

CREATE DATABASE rules
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;	
	
CREATE TABLE public.rulelist (
    id bigint NOT NULL,
    action character varying(255),
    code_type_of_balance character varying(255),
    direction character varying(255),
    source character varying(255),
    summ character varying(255),
    type character varying(255),
    end_date timestamp without time zone,
    start_date timestamp without time zone,
    summ_compare character varying(1)
	CONSTRAINT rulelist_pkey PRIMARY KEY (id)
);

	
CREATE TABLE public.sourceoft (
    id bigint NOT NULL,
    source character varying NOT NULL
	CONSTRAINT sourceoft_pkey PRIMARY KEY (id)
); 

CREATE TABLE public.typeoft (
    id bigint NOT NULL,
    type character varying NOT NULL
);	

ALTER TABLE ONLY public.typeoft ADD CONSTRAINT typeoft_pkey PRIMARY KEY (id);