--
-- PostgreSQL database dump
--

-- Dumped from database version 14.13 (Ubuntu 14.13-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.13 (Ubuntu 14.13-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: images; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.images (
    id_image integer NOT NULL,
    table_name text NOT NULL,
    id_table integer NOT NULL,
    "order" smallint DEFAULT 0 NOT NULL,
    image bytea NOT NULL
);


ALTER TABLE public.images OWNER TO felipe;

--
-- Name: images_id_image_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.images_id_image_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.images_id_image_seq OWNER TO felipe;

--
-- Name: images_id_image_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.images_id_image_seq OWNED BY public.images.id_image;


--
-- Name: instructor; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.instructor (
    id_instructor integer NOT NULL,
    id_user integer NOT NULL,
    degree_name text NOT NULL,
    educational_institution text NOT NULL,
    start_date date NOT NULL,
    end_date date,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.instructor OWNER TO felipe;

--
-- Name: instructor_id_instructor_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.instructor_id_instructor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instructor_id_instructor_seq OWNER TO felipe;

--
-- Name: instructor_id_instructor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.instructor_id_instructor_seq OWNED BY public.instructor.id_instructor;


--
-- Name: modality; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.modality (
    id_modality integer NOT NULL,
    id_instructor integer NOT NULL,
    description character varying(50) NOT NULL,
    max_participants smallint,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.modality OWNER TO felipe;

--
-- Name: modality_id_modality_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.modality_id_modality_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modality_id_modality_seq OWNER TO felipe;

--
-- Name: modality_id_modality_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.modality_id_modality_seq OWNED BY public.modality.id_modality;


--
-- Name: place; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.place (
    id_place integer NOT NULL,
    name character varying(50) NOT NULL,
    max_capacity smallint,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.place OWNER TO felipe;

--
-- Name: place_id_place_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.place_id_place_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.place_id_place_seq OWNER TO felipe;

--
-- Name: place_id_place_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.place_id_place_seq OWNED BY public.place.id_place;


--
-- Name: poll; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.poll (
    id_poll integer NOT NULL,
    name character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.poll OWNER TO felipe;

--
-- Name: poll_id_poll_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.poll_id_poll_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poll_id_poll_seq OWNER TO felipe;

--
-- Name: poll_id_poll_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.poll_id_poll_seq OWNED BY public.poll.id_poll;


--
-- Name: poll_response; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.poll_response (
    id_poll_response integer NOT NULL,
    id_user integer NOT NULL,
    id_poll integer NOT NULL,
    response_date_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.poll_response OWNER TO felipe;

--
-- Name: poll_response_id_poll_response_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.poll_response_id_poll_response_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poll_response_id_poll_response_seq OWNER TO felipe;

--
-- Name: poll_response_id_poll_response_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.poll_response_id_poll_response_seq OWNED BY public.poll_response.id_poll_response;


--
-- Name: presence; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.presence (
    id_presence integer NOT NULL,
    id_training integer NOT NULL,
    id_user integer NOT NULL,
    datetime_training timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    present boolean DEFAULT true NOT NULL
);


ALTER TABLE public.presence OWNER TO felipe;

--
-- Name: presence_id_presence_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.presence_id_presence_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.presence_id_presence_seq OWNER TO felipe;

--
-- Name: presence_id_presence_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.presence_id_presence_seq OWNED BY public.presence.id_presence;


--
-- Name: question; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.question (
    id_question integer NOT NULL,
    id_poll integer NOT NULL,
    id_question_type integer NOT NULL,
    question text NOT NULL,
    required boolean DEFAULT false,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.question OWNER TO felipe;

--
-- Name: question_id_question_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.question_id_question_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_id_question_seq OWNER TO felipe;

--
-- Name: question_id_question_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.question_id_question_seq OWNED BY public.question.id_question;


--
-- Name: question_response; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.question_response (
    id_question_response integer NOT NULL,
    id_poll_response integer NOT NULL,
    id_question integer NOT NULL,
    response text NOT NULL
);


ALTER TABLE public.question_response OWNER TO felipe;

--
-- Name: question_response_id_question_response_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.question_response_id_question_response_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_response_id_question_response_seq OWNER TO felipe;

--
-- Name: question_response_id_question_response_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.question_response_id_question_response_seq OWNED BY public.question_response.id_question_response;


--
-- Name: question_type; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.question_type (
    id_question_type integer NOT NULL,
    type_name character varying(50) NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.question_type OWNER TO felipe;

--
-- Name: question_type_id_question_type_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.question_type_id_question_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_type_id_question_type_seq OWNER TO felipe;

--
-- Name: question_type_id_question_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.question_type_id_question_type_seq OWNED BY public.question_type.id_question_type;


--
-- Name: role; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.role (
    id_role integer NOT NULL,
    description character varying(50) NOT NULL,
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.role OWNER TO felipe;

--
-- Name: role_id_role_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.role_id_role_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_role_seq OWNER TO felipe;

--
-- Name: role_id_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.role_id_role_seq OWNED BY public.role.id_role;


--
-- Name: training; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.training (
    id_training integer NOT NULL,
    id_modality integer NOT NULL,
    id_place integer NOT NULL,
    description character varying(50),
    week_day smallint NOT NULL,
    start_hour smallint NOT NULL,
    end_hour smallint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    CONSTRAINT training_week_day_check CHECK (((week_day >= 1) AND (week_day <= 7)))
);


ALTER TABLE public.training OWNER TO felipe;

--
-- Name: training_id_training_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.training_id_training_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.training_id_training_seq OWNER TO felipe;

--
-- Name: training_id_training_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.training_id_training_seq OWNED BY public.training.id_training;


--
-- Name: training_user; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.training_user (
    id_training_user integer NOT NULL,
    id_training integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.training_user OWNER TO felipe;

--
-- Name: training_user_id_training_user_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.training_user_id_training_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.training_user_id_training_user_seq OWNER TO felipe;

--
-- Name: training_user_id_training_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.training_user_id_training_user_seq OWNED BY public.training_user.id_training_user;


--
-- Name: users; Type: TABLE; Schema: public; Owner: felipe
--

CREATE TABLE public.users (
    id_user integer NOT NULL,
    id_role integer DEFAULT 0 NOT NULL,
    cpf character(11) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_date date,
    email text NOT NULL,
    phone character(13),
    password character(72),
    active boolean DEFAULT true NOT NULL
);


ALTER TABLE public.users OWNER TO felipe;

--
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: felipe
--

CREATE SEQUENCE public.users_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_user_seq OWNER TO felipe;

--
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: felipe
--

ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;


--
-- Name: images id_image; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.images ALTER COLUMN id_image SET DEFAULT nextval('public.images_id_image_seq'::regclass);


--
-- Name: instructor id_instructor; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.instructor ALTER COLUMN id_instructor SET DEFAULT nextval('public.instructor_id_instructor_seq'::regclass);


--
-- Name: modality id_modality; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.modality ALTER COLUMN id_modality SET DEFAULT nextval('public.modality_id_modality_seq'::regclass);


--
-- Name: place id_place; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.place ALTER COLUMN id_place SET DEFAULT nextval('public.place_id_place_seq'::regclass);


--
-- Name: poll id_poll; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.poll ALTER COLUMN id_poll SET DEFAULT nextval('public.poll_id_poll_seq'::regclass);


--
-- Name: poll_response id_poll_response; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.poll_response ALTER COLUMN id_poll_response SET DEFAULT nextval('public.poll_response_id_poll_response_seq'::regclass);


--
-- Name: presence id_presence; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.presence ALTER COLUMN id_presence SET DEFAULT nextval('public.presence_id_presence_seq'::regclass);


--
-- Name: question id_question; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question ALTER COLUMN id_question SET DEFAULT nextval('public.question_id_question_seq'::regclass);


--
-- Name: question_response id_question_response; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question_response ALTER COLUMN id_question_response SET DEFAULT nextval('public.question_response_id_question_response_seq'::regclass);


--
-- Name: question_type id_question_type; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question_type ALTER COLUMN id_question_type SET DEFAULT nextval('public.question_type_id_question_type_seq'::regclass);


--
-- Name: role id_role; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.role ALTER COLUMN id_role SET DEFAULT nextval('public.role_id_role_seq'::regclass);


--
-- Name: training id_training; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.training ALTER COLUMN id_training SET DEFAULT nextval('public.training_id_training_seq'::regclass);


--
-- Name: training_user id_training_user; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.training_user ALTER COLUMN id_training_user SET DEFAULT nextval('public.training_user_id_training_user_seq'::regclass);


--
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.users ALTER COLUMN id_user SET DEFAULT nextval('public.users_id_user_seq'::regclass);


--
-- Data for Name: images; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.images (id_image, table_name, id_table, "order", image) FROM stdin;
\.


--
-- Data for Name: instructor; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.instructor (id_instructor, id_user, degree_name, educational_institution, start_date, end_date, active) FROM stdin;
\.


--
-- Data for Name: modality; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.modality (id_modality, id_instructor, description, max_participants, active) FROM stdin;
\.


--
-- Data for Name: place; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.place (id_place, name, max_capacity, active) FROM stdin;
1	Lugar de Teste	23	t
\.


--
-- Data for Name: poll; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.poll (id_poll, name, created_at, updated_at, active) FROM stdin;
\.


--
-- Data for Name: poll_response; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.poll_response (id_poll_response, id_user, id_poll, response_date_time) FROM stdin;
\.


--
-- Data for Name: presence; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.presence (id_presence, id_training, id_user, datetime_training, present) FROM stdin;
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.question (id_question, id_poll, id_question_type, question, required, active) FROM stdin;
\.


--
-- Data for Name: question_response; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.question_response (id_question_response, id_poll_response, id_question, response) FROM stdin;
\.


--
-- Data for Name: question_type; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.question_type (id_question_type, type_name, active) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.role (id_role, description, active) FROM stdin;
\.


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.training (id_training, id_modality, id_place, description, week_day, start_hour, end_hour, active) FROM stdin;
\.


--
-- Data for Name: training_user; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.training_user (id_training_user, id_training, id_user) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: felipe
--

COPY public.users (id_user, id_role, cpf, first_name, last_name, birth_date, email, phone, password, active) FROM stdin;
\.


--
-- Name: images_id_image_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.images_id_image_seq', 1, false);


--
-- Name: instructor_id_instructor_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.instructor_id_instructor_seq', 1, false);


--
-- Name: modality_id_modality_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.modality_id_modality_seq', 1, false);


--
-- Name: place_id_place_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.place_id_place_seq', 1, true);


--
-- Name: poll_id_poll_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.poll_id_poll_seq', 1, false);


--
-- Name: poll_response_id_poll_response_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.poll_response_id_poll_response_seq', 1, false);


--
-- Name: presence_id_presence_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.presence_id_presence_seq', 1, false);


--
-- Name: question_id_question_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.question_id_question_seq', 1, false);


--
-- Name: question_response_id_question_response_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.question_response_id_question_response_seq', 1, false);


--
-- Name: question_type_id_question_type_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.question_type_id_question_type_seq', 1, false);


--
-- Name: role_id_role_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.role_id_role_seq', 1, false);


--
-- Name: training_id_training_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.training_id_training_seq', 1, false);


--
-- Name: training_user_id_training_user_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.training_user_id_training_user_seq', 1, false);


--
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: felipe
--

SELECT pg_catalog.setval('public.users_id_user_seq', 1, false);


--
-- Name: images images_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id_image);


--
-- Name: instructor instructor_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT instructor_pkey PRIMARY KEY (id_instructor);


--
-- Name: modality modality_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.modality
    ADD CONSTRAINT modality_pkey PRIMARY KEY (id_modality);


--
-- Name: place place_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id_place);


--
-- Name: poll poll_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.poll
    ADD CONSTRAINT poll_pkey PRIMARY KEY (id_poll);


--
-- Name: poll_response poll_response_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.poll_response
    ADD CONSTRAINT poll_response_pkey PRIMARY KEY (id_poll_response);


--
-- Name: presence presence_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.presence
    ADD CONSTRAINT presence_pkey PRIMARY KEY (id_presence);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id_question);


--
-- Name: question_response question_response_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question_response
    ADD CONSTRAINT question_response_pkey PRIMARY KEY (id_question_response);


--
-- Name: question_type question_type_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.question_type
    ADD CONSTRAINT question_type_pkey PRIMARY KEY (id_question_type);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id_role);


--
-- Name: training training_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT training_pkey PRIMARY KEY (id_training);


--
-- Name: training_user training_user_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.training_user
    ADD CONSTRAINT training_user_pkey PRIMARY KEY (id_training_user);


--
-- Name: training unq_training_time; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT unq_training_time UNIQUE (id_place, week_day, start_hour);


--
-- Name: users users_cpf_key; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_cpf_key UNIQUE (cpf);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: felipe
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- Name: idx_images_id_table; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_images_id_table ON public.images USING btree (id_table);


--
-- Name: idx_images_table_name; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_images_table_name ON public.images USING btree (table_name);


--
-- Name: idx_poll_created_at; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_poll_created_at ON public.poll USING btree (created_at);


--
-- Name: idx_poll_name; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_poll_name ON public.poll USING btree (name);


--
-- Name: idx_poll_updated_at; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_poll_updated_at ON public.poll USING btree (updated_at);


--
-- Name: idx_presence_datetime_training; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_presence_datetime_training ON public.presence USING btree (datetime_training);


--
-- Name: idx_question_type_type_name; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_question_type_type_name ON public.question_type USING btree (type_name);


--
-- Name: idx_users_birth_date; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_birth_date ON public.users USING btree (birth_date);


--
-- Name: idx_users_cpf; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_cpf ON public.users USING btree (cpf);


--
-- Name: idx_users_email; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_email ON public.users USING btree (email);


--
-- Name: idx_users_first_name; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_first_name ON public.users USING btree (first_name);


--
-- Name: idx_users_id_role; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_id_role ON public.users USING btree (id_role);


--
-- Name: idx_users_last_name; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_last_name ON public.users USING btree (last_name);


--
-- Name: idx_users_phone; Type: INDEX; Schema: public; Owner: felipe
--

CREATE INDEX idx_users_phone ON public.users USING btree (phone);


--
-- Name: unq_images_order; Type: INDEX; Schema: public; Owner: felipe
--

CREATE UNIQUE INDEX unq_images_order ON public.images USING btree (table_name, id_table, "order");


--
-- PostgreSQL database dump complete
--

