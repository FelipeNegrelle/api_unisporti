PGDMP         "        
    
    |            postgres %   14.13 (Ubuntu 14.13-0ubuntu0.22.04.1) %   14.13 (Ubuntu 14.13-0ubuntu0.22.04.1) u    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13797    postgres    DATABASE     ]   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'pt_BR.UTF-8';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3515                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    17227    images    TABLE     �   CREATE TABLE public.images (
    id_image integer NOT NULL,
    table_name text NOT NULL,
    id_table integer NOT NULL,
    ordering smallint DEFAULT 0 NOT NULL,
    image bytea NOT NULL,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.images;
       public         heap    felipe    false    3            �            1259    17226    images_id_image_seq    SEQUENCE     �   CREATE SEQUENCE public.images_id_image_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.images_id_image_seq;
       public          felipe    false    234    3            �           0    0    images_id_image_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.images_id_image_seq OWNED BY public.images.id_image;
          public          felipe    false    233            �            1259    17097 
   instructor    TABLE     
  CREATE TABLE public.instructor (
    id_instructor integer NOT NULL,
    id_user integer NOT NULL,
    degree_name text NOT NULL,
    educational_institution text NOT NULL,
    start_date date NOT NULL,
    end_date date,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.instructor;
       public         heap    felipe    false    3            �            1259    17096    instructor_id_instructor_seq    SEQUENCE     �   CREATE SEQUENCE public.instructor_id_instructor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.instructor_id_instructor_seq;
       public          felipe    false    3    212            �           0    0    instructor_id_instructor_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.instructor_id_instructor_seq OWNED BY public.instructor.id_instructor;
          public          felipe    false    211            �            1259    17115    modality    TABLE     �   CREATE TABLE public.modality (
    id_modality integer NOT NULL,
    id_instructor integer NOT NULL,
    description character varying(50) NOT NULL,
    max_participants smallint,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.modality;
       public         heap    felipe    false    3            �            1259    17114    modality_id_modality_seq    SEQUENCE     �   CREATE SEQUENCE public.modality_id_modality_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.modality_id_modality_seq;
       public          felipe    false    3    216            �           0    0    modality_id_modality_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.modality_id_modality_seq OWNED BY public.modality.id_modality;
          public          felipe    false    215            �            1259    17107    place    TABLE     �   CREATE TABLE public.place (
    id_place integer NOT NULL,
    name character varying(50) NOT NULL,
    max_capacity smallint,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.place;
       public         heap    felipe    false    3            �            1259    17106    place_id_place_seq    SEQUENCE     �   CREATE SEQUENCE public.place_id_place_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.place_id_place_seq;
       public          felipe    false    214    3            �           0    0    place_id_place_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.place_id_place_seq OWNED BY public.place.id_place;
          public          felipe    false    213            �            1259    17215    plan    TABLE     �   CREATE TABLE public.plan (
    id_plan integer NOT NULL,
    name character varying(50) NOT NULL,
    price_cents smallint NOT NULL,
    duration_days smallint NOT NULL,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.plan;
       public         heap    felipe    false    3            �            1259    17214    plan_id_plan_seq    SEQUENCE     �   CREATE SEQUENCE public.plan_id_plan_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.plan_id_plan_seq;
       public          felipe    false    232    3            �           0    0    plan_id_plan_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.plan_id_plan_seq OWNED BY public.plan.id_plan;
          public          felipe    false    231            �            1259    17151    poll    TABLE       CREATE TABLE public.poll (
    id_poll integer NOT NULL,
    name character varying(50) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.poll;
       public         heap    felipe    false    3            �            1259    17150    poll_id_poll_seq    SEQUENCE     �   CREATE SEQUENCE public.poll_id_poll_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.poll_id_poll_seq;
       public          felipe    false    224    3            �           0    0    poll_id_poll_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.poll_id_poll_seq OWNED BY public.poll.id_poll;
          public          felipe    false    223            �            1259    17184    poll_response    TABLE     �   CREATE TABLE public.poll_response (
    id_poll_response integer NOT NULL,
    id_user integer NOT NULL,
    id_poll integer NOT NULL,
    response_date_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
 !   DROP TABLE public.poll_response;
       public         heap    felipe    false    3            �            1259    17183 "   poll_response_id_poll_response_seq    SEQUENCE     �   CREATE SEQUENCE public.poll_response_id_poll_response_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.poll_response_id_poll_response_seq;
       public          felipe    false    3    228            �           0    0 "   poll_response_id_poll_response_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.poll_response_id_poll_response_seq OWNED BY public.poll_response.id_poll_response;
          public          felipe    false    227            �            1259    17141    presence    TABLE       CREATE TABLE public.presence (
    id_presence integer NOT NULL,
    id_training integer NOT NULL,
    id_user integer NOT NULL,
    datetime_training timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    present boolean DEFAULT true NOT NULL
);
    DROP TABLE public.presence;
       public         heap    felipe    false    3            �            1259    17140    presence_id_presence_seq    SEQUENCE     �   CREATE SEQUENCE public.presence_id_presence_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.presence_id_presence_seq;
       public          felipe    false    3    222            �           0    0    presence_id_presence_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.presence_id_presence_seq OWNED BY public.presence.id_presence;
          public          felipe    false    221            �            1259    17173    question    TABLE     �   CREATE TABLE public.question (
    id_question integer NOT NULL,
    id_poll integer NOT NULL,
    question text NOT NULL,
    active boolean DEFAULT true NOT NULL
);
    DROP TABLE public.question;
       public         heap    felipe    false    3            �            1259    17172    question_id_question_seq    SEQUENCE     �   CREATE SEQUENCE public.question_id_question_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.question_id_question_seq;
       public          felipe    false    226    3            �           0    0    question_id_question_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.question_id_question_seq OWNED BY public.question.id_question;
          public          felipe    false    225            �            1259    17192    question_response    TABLE     �   CREATE TABLE public.question_response (
    id_question_response integer NOT NULL,
    id_poll_response integer NOT NULL,
    id_question integer NOT NULL
);
 %   DROP TABLE public.question_response;
       public         heap    felipe    false    3            �            1259    17191 *   question_response_id_question_response_seq    SEQUENCE     �   CREATE SEQUENCE public.question_response_id_question_response_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 A   DROP SEQUENCE public.question_response_id_question_response_seq;
       public          felipe    false    230    3            �           0    0 *   question_response_id_question_response_seq    SEQUENCE OWNED BY     y   ALTER SEQUENCE public.question_response_id_question_response_seq OWNED BY public.question_response.id_question_response;
          public          felipe    false    229            �            1259    17123    training    TABLE     �  CREATE TABLE public.training (
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
    DROP TABLE public.training;
       public         heap    felipe    false    3            �            1259    17122    training_id_training_seq    SEQUENCE     �   CREATE SEQUENCE public.training_id_training_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.training_id_training_seq;
       public          felipe    false    218    3            �           0    0    training_id_training_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.training_id_training_seq OWNED BY public.training.id_training;
          public          felipe    false    217            �            1259    17134    training_user    TABLE     �   CREATE TABLE public.training_user (
    id_training_user integer NOT NULL,
    id_training integer NOT NULL,
    id_user integer NOT NULL
);
 !   DROP TABLE public.training_user;
       public         heap    felipe    false    3            �            1259    17133 "   training_user_id_training_user_seq    SEQUENCE     �   CREATE SEQUENCE public.training_user_id_training_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.training_user_id_training_user_seq;
       public          felipe    false    3    220            �           0    0 "   training_user_id_training_user_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.training_user_id_training_user_seq OWNED BY public.training_user.id_training_user;
          public          felipe    false    219            �            1259    17067    users    TABLE     �  CREATE TABLE public.users (
    id_user integer NOT NULL,
    cpf character varying(11) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_date date,
    email text NOT NULL,
    phone character varying(13),
    password character varying(72),
    active boolean DEFAULT true NOT NULL,
    role character(1) DEFAULT 'U'::bpchar NOT NULL
);
    DROP TABLE public.users;
       public         heap    felipe    false    3            �            1259    17066    users_id_user_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.users_id_user_seq;
       public          felipe    false    210    3            �           0    0    users_id_user_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;
          public          felipe    false    209            �           2604    17230    images id_image    DEFAULT     r   ALTER TABLE ONLY public.images ALTER COLUMN id_image SET DEFAULT nextval('public.images_id_image_seq'::regclass);
 >   ALTER TABLE public.images ALTER COLUMN id_image DROP DEFAULT;
       public          felipe    false    233    234    234            �           2604    17100    instructor id_instructor    DEFAULT     �   ALTER TABLE ONLY public.instructor ALTER COLUMN id_instructor SET DEFAULT nextval('public.instructor_id_instructor_seq'::regclass);
 G   ALTER TABLE public.instructor ALTER COLUMN id_instructor DROP DEFAULT;
       public          felipe    false    212    211    212            �           2604    17118    modality id_modality    DEFAULT     |   ALTER TABLE ONLY public.modality ALTER COLUMN id_modality SET DEFAULT nextval('public.modality_id_modality_seq'::regclass);
 C   ALTER TABLE public.modality ALTER COLUMN id_modality DROP DEFAULT;
       public          felipe    false    216    215    216            �           2604    17110    place id_place    DEFAULT     p   ALTER TABLE ONLY public.place ALTER COLUMN id_place SET DEFAULT nextval('public.place_id_place_seq'::regclass);
 =   ALTER TABLE public.place ALTER COLUMN id_place DROP DEFAULT;
       public          felipe    false    213    214    214            �           2604    17218    plan id_plan    DEFAULT     l   ALTER TABLE ONLY public.plan ALTER COLUMN id_plan SET DEFAULT nextval('public.plan_id_plan_seq'::regclass);
 ;   ALTER TABLE public.plan ALTER COLUMN id_plan DROP DEFAULT;
       public          felipe    false    232    231    232            �           2604    17154    poll id_poll    DEFAULT     l   ALTER TABLE ONLY public.poll ALTER COLUMN id_poll SET DEFAULT nextval('public.poll_id_poll_seq'::regclass);
 ;   ALTER TABLE public.poll ALTER COLUMN id_poll DROP DEFAULT;
       public          felipe    false    224    223    224            �           2604    17187    poll_response id_poll_response    DEFAULT     �   ALTER TABLE ONLY public.poll_response ALTER COLUMN id_poll_response SET DEFAULT nextval('public.poll_response_id_poll_response_seq'::regclass);
 M   ALTER TABLE public.poll_response ALTER COLUMN id_poll_response DROP DEFAULT;
       public          felipe    false    228    227    228            �           2604    17144    presence id_presence    DEFAULT     |   ALTER TABLE ONLY public.presence ALTER COLUMN id_presence SET DEFAULT nextval('public.presence_id_presence_seq'::regclass);
 C   ALTER TABLE public.presence ALTER COLUMN id_presence DROP DEFAULT;
       public          felipe    false    221    222    222            �           2604    17176    question id_question    DEFAULT     |   ALTER TABLE ONLY public.question ALTER COLUMN id_question SET DEFAULT nextval('public.question_id_question_seq'::regclass);
 C   ALTER TABLE public.question ALTER COLUMN id_question DROP DEFAULT;
       public          felipe    false    225    226    226            �           2604    17195 &   question_response id_question_response    DEFAULT     �   ALTER TABLE ONLY public.question_response ALTER COLUMN id_question_response SET DEFAULT nextval('public.question_response_id_question_response_seq'::regclass);
 U   ALTER TABLE public.question_response ALTER COLUMN id_question_response DROP DEFAULT;
       public          felipe    false    230    229    230            �           2604    17126    training id_training    DEFAULT     |   ALTER TABLE ONLY public.training ALTER COLUMN id_training SET DEFAULT nextval('public.training_id_training_seq'::regclass);
 C   ALTER TABLE public.training ALTER COLUMN id_training DROP DEFAULT;
       public          felipe    false    218    217    218            �           2604    17137    training_user id_training_user    DEFAULT     �   ALTER TABLE ONLY public.training_user ALTER COLUMN id_training_user SET DEFAULT nextval('public.training_user_id_training_user_seq'::regclass);
 M   ALTER TABLE public.training_user ALTER COLUMN id_training_user DROP DEFAULT;
       public          felipe    false    219    220    220            �           2604    17070    users id_user    DEFAULT     n   ALTER TABLE ONLY public.users ALTER COLUMN id_user SET DEFAULT nextval('public.users_id_user_seq'::regclass);
 <   ALTER TABLE public.users ALTER COLUMN id_user DROP DEFAULT;
       public          felipe    false    209    210    210            �          0    17227    images 
   TABLE DATA           Y   COPY public.images (id_image, table_name, id_table, ordering, image, active) FROM stdin;
    public          felipe    false    234   �       �          0    17097 
   instructor 
   TABLE DATA           �   COPY public.instructor (id_instructor, id_user, degree_name, educational_institution, start_date, end_date, active) FROM stdin;
    public          felipe    false    212   ��       �          0    17115    modality 
   TABLE DATA           e   COPY public.modality (id_modality, id_instructor, description, max_participants, active) FROM stdin;
    public          felipe    false    216   ��       �          0    17107    place 
   TABLE DATA           E   COPY public.place (id_place, name, max_capacity, active) FROM stdin;
    public          felipe    false    214   ؘ       �          0    17215    plan 
   TABLE DATA           Q   COPY public.plan (id_plan, name, price_cents, duration_days, active) FROM stdin;
    public          felipe    false    232   �       �          0    17151    poll 
   TABLE DATA           M   COPY public.poll (id_poll, name, created_at, updated_at, active) FROM stdin;
    public          felipe    false    224   6�       �          0    17184    poll_response 
   TABLE DATA           _   COPY public.poll_response (id_poll_response, id_user, id_poll, response_date_time) FROM stdin;
    public          felipe    false    228   S�       �          0    17141    presence 
   TABLE DATA           a   COPY public.presence (id_presence, id_training, id_user, datetime_training, present) FROM stdin;
    public          felipe    false    222   p�       �          0    17173    question 
   TABLE DATA           J   COPY public.question (id_question, id_poll, question, active) FROM stdin;
    public          felipe    false    226   ��       �          0    17192    question_response 
   TABLE DATA           `   COPY public.question_response (id_question_response, id_poll_response, id_question) FROM stdin;
    public          felipe    false    230   ��       �          0    17123    training 
   TABLE DATA           {   COPY public.training (id_training, id_modality, id_place, description, week_day, start_hour, end_hour, active) FROM stdin;
    public          felipe    false    218   Ǚ       �          0    17134    training_user 
   TABLE DATA           O   COPY public.training_user (id_training_user, id_training, id_user) FROM stdin;
    public          felipe    false    220   �       �          0    17067    users 
   TABLE DATA           v   COPY public.users (id_user, cpf, first_name, last_name, birth_date, email, phone, password, active, role) FROM stdin;
    public          felipe    false    210   �       �           0    0    images_id_image_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.images_id_image_seq', 1, true);
          public          felipe    false    233            �           0    0    instructor_id_instructor_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.instructor_id_instructor_seq', 1, false);
          public          felipe    false    211            �           0    0    modality_id_modality_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.modality_id_modality_seq', 1, false);
          public          felipe    false    215            �           0    0    place_id_place_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.place_id_place_seq', 4, true);
          public          felipe    false    213            �           0    0    plan_id_plan_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.plan_id_plan_seq', 1, false);
          public          felipe    false    231            �           0    0    poll_id_poll_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.poll_id_poll_seq', 1, false);
          public          felipe    false    223            �           0    0 "   poll_response_id_poll_response_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.poll_response_id_poll_response_seq', 1, false);
          public          felipe    false    227            �           0    0    presence_id_presence_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.presence_id_presence_seq', 1, false);
          public          felipe    false    221            �           0    0    question_id_question_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.question_id_question_seq', 1, false);
          public          felipe    false    225            �           0    0 *   question_response_id_question_response_seq    SEQUENCE SET     Y   SELECT pg_catalog.setval('public.question_response_id_question_response_seq', 1, false);
          public          felipe    false    229            �           0    0    training_id_training_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.training_id_training_seq', 1, false);
          public          felipe    false    217            �           0    0 "   training_user_id_training_user_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.training_user_id_training_user_seq', 1, false);
          public          felipe    false    219            �           0    0    users_id_user_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_id_user_seq', 1, true);
          public          felipe    false    209                       2606    17236    images images_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id_image);
 <   ALTER TABLE ONLY public.images DROP CONSTRAINT images_pkey;
       public            felipe    false    234            �           2606    17105    instructor instructor_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.instructor
    ADD CONSTRAINT instructor_pkey PRIMARY KEY (id_instructor);
 D   ALTER TABLE ONLY public.instructor DROP CONSTRAINT instructor_pkey;
       public            felipe    false    212            �           2606    17121    modality modality_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.modality
    ADD CONSTRAINT modality_pkey PRIMARY KEY (id_modality);
 @   ALTER TABLE ONLY public.modality DROP CONSTRAINT modality_pkey;
       public            felipe    false    216            �           2606    17113    place place_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.place
    ADD CONSTRAINT place_pkey PRIMARY KEY (id_place);
 :   ALTER TABLE ONLY public.place DROP CONSTRAINT place_pkey;
       public            felipe    false    214                       2606    17221    plan plan_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.plan
    ADD CONSTRAINT plan_pkey PRIMARY KEY (id_plan);
 8   ALTER TABLE ONLY public.plan DROP CONSTRAINT plan_pkey;
       public            felipe    false    232                        2606    17159    poll poll_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.poll
    ADD CONSTRAINT poll_pkey PRIMARY KEY (id_poll);
 8   ALTER TABLE ONLY public.poll DROP CONSTRAINT poll_pkey;
       public            felipe    false    224                       2606    17190     poll_response poll_response_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.poll_response
    ADD CONSTRAINT poll_response_pkey PRIMARY KEY (id_poll_response);
 J   ALTER TABLE ONLY public.poll_response DROP CONSTRAINT poll_response_pkey;
       public            felipe    false    228            �           2606    17148    presence presence_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.presence
    ADD CONSTRAINT presence_pkey PRIMARY KEY (id_presence);
 @   ALTER TABLE ONLY public.presence DROP CONSTRAINT presence_pkey;
       public            felipe    false    222                       2606    17182    question question_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id_question);
 @   ALTER TABLE ONLY public.question DROP CONSTRAINT question_pkey;
       public            felipe    false    226                       2606    17199 (   question_response question_response_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.question_response
    ADD CONSTRAINT question_response_pkey PRIMARY KEY (id_question_response);
 R   ALTER TABLE ONLY public.question_response DROP CONSTRAINT question_response_pkey;
       public            felipe    false    230            �           2606    17130    training training_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.training
    ADD CONSTRAINT training_pkey PRIMARY KEY (id_training);
 @   ALTER TABLE ONLY public.training DROP CONSTRAINT training_pkey;
       public            felipe    false    218            �           2606    17139     training_user training_user_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.training_user
    ADD CONSTRAINT training_user_pkey PRIMARY KEY (id_training_user);
 J   ALTER TABLE ONLY public.training_user DROP CONSTRAINT training_user_pkey;
       public            felipe    false    220            �           2606    17132    training unq_training_time 
   CONSTRAINT     o   ALTER TABLE ONLY public.training
    ADD CONSTRAINT unq_training_time UNIQUE (id_place, week_day, start_hour);
 D   ALTER TABLE ONLY public.training DROP CONSTRAINT unq_training_time;
       public            felipe    false    218    218    218            �           2606    25424    users users_cpf_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_cpf_key UNIQUE (cpf);
 =   ALTER TABLE ONLY public.users DROP CONSTRAINT users_cpf_key;
       public            felipe    false    210            �           2606    17080    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            felipe    false    210            �           2606    17076    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            felipe    false    210                       1259    17238    idx_images_id_table    INDEX     J   CREATE INDEX idx_images_id_table ON public.images USING btree (id_table);
 '   DROP INDEX public.idx_images_id_table;
       public            felipe    false    234                       1259    17237    idx_images_table_name    INDEX     N   CREATE INDEX idx_images_table_name ON public.images USING btree (table_name);
 )   DROP INDEX public.idx_images_table_name;
       public            felipe    false    234                       1259    17224    idx_plan_duration_days    INDEX     P   CREATE INDEX idx_plan_duration_days ON public.plan USING btree (duration_days);
 *   DROP INDEX public.idx_plan_duration_days;
       public            felipe    false    232                       1259    17222    idx_plan_name    INDEX     >   CREATE INDEX idx_plan_name ON public.plan USING btree (name);
 !   DROP INDEX public.idx_plan_name;
       public            felipe    false    232            	           1259    17223    idx_plan_price_cents    INDEX     L   CREATE INDEX idx_plan_price_cents ON public.plan USING btree (price_cents);
 (   DROP INDEX public.idx_plan_price_cents;
       public            felipe    false    232            �           1259    17161    idx_poll_created_at    INDEX     J   CREATE INDEX idx_poll_created_at ON public.poll USING btree (created_at);
 '   DROP INDEX public.idx_poll_created_at;
       public            felipe    false    224            �           1259    17160    idx_poll_name    INDEX     >   CREATE INDEX idx_poll_name ON public.poll USING btree (name);
 !   DROP INDEX public.idx_poll_name;
       public            felipe    false    224            �           1259    17162    idx_poll_updated_at    INDEX     J   CREATE INDEX idx_poll_updated_at ON public.poll USING btree (updated_at);
 '   DROP INDEX public.idx_poll_updated_at;
       public            felipe    false    224            �           1259    17149    idx_presence_datetime_training    INDEX     `   CREATE INDEX idx_presence_datetime_training ON public.presence USING btree (datetime_training);
 2   DROP INDEX public.idx_presence_datetime_training;
       public            felipe    false    222            �           1259    17085    idx_users_birth_date    INDEX     L   CREATE INDEX idx_users_birth_date ON public.users USING btree (birth_date);
 (   DROP INDEX public.idx_users_birth_date;
       public            felipe    false    210            �           1259    25425    idx_users_cpf    INDEX     >   CREATE INDEX idx_users_cpf ON public.users USING btree (cpf);
 !   DROP INDEX public.idx_users_cpf;
       public            felipe    false    210            �           1259    17086    idx_users_email    INDEX     B   CREATE INDEX idx_users_email ON public.users USING btree (email);
 #   DROP INDEX public.idx_users_email;
       public            felipe    false    210            �           1259    17083    idx_users_first_name    INDEX     L   CREATE INDEX idx_users_first_name ON public.users USING btree (first_name);
 (   DROP INDEX public.idx_users_first_name;
       public            felipe    false    210            �           1259    17084    idx_users_last_name    INDEX     J   CREATE INDEX idx_users_last_name ON public.users USING btree (last_name);
 '   DROP INDEX public.idx_users_last_name;
       public            felipe    false    210            �           1259    25426    idx_users_phone    INDEX     B   CREATE INDEX idx_users_phone ON public.users USING btree (phone);
 #   DROP INDEX public.idx_users_phone;
       public            felipe    false    210                       1259    17239    unq_images_ordering    INDEX     g   CREATE UNIQUE INDEX unq_images_ordering ON public.images USING btree (table_name, id_table, ordering);
 '   DROP INDEX public.unq_images_ordering;
       public            felipe    false    234    234    234            �      x��\K�%)�g.������ �l�r��o����32���j��n܏;�w�$������?���o�?���{�ٯ���.�|s�}��J�(���_W~{�����o��U�wW8�sL�2翾�z#�}E͎J��k��?�z<��՗�c�y�$S� |���ʯW53����[?^������_��^����X��O�!q�z���f��t'�ma��0�t�����0�%�?~�5���v$�5}���C��VΉ�c����/ݑml5�׻�L2��0�$�)u��&qn����0K�=��m��&{4�KD>Q.f�=2_���ߤ�0ƕK�{����BJ~��#O���#O�̼�������{eFCF���pҌ1w��v��:�ׇu�9$����C���=�ZO�߬&���ZF�t�{�V�B���(K�\�{>A���&�ibO�E�@��mp������$O����W����!��9����H,����}�=�TnV�Iv~��e���J��3��]���e�(o-�n��b�۱HV���Ɓu���СQ�W�q~E���qg�:��(��=OϘAj"���d�f$h$.U����V�w��=��������B��H�2�O;Hs�b�%�L��9�X���֓��GfXF��	-�>��1�ӓ�h��x�!�ы$$�Z�&za�/#�:�7�e}
��M<b���W=���IA���0V7�q��{��)Y)ؙ`�9����OLY(�L}�;j��$���	��������W
�F����W3�4����>!�������	�N�a�f$�?��D3by���<�����^� �U!��qG�~�ް��B��(�j4+��Z���5{&���)��A2Qz�(M@lwt�+��
V#oՂ��v����z����ݹ�VF��z��A5���gyDVq��$$3��7��FL���a��W�h&��k�W+�H�/�h�Ds]\��ǜ��s�t�z�!�-�>��{�s��c}�����e�P�kW�=��̡٧2��z��ٕ�y��{xf�#TH	�8��G�<���U:���aX��SR��3��UL�V�<�D\��Er�I��-� Q�;7�Y���?�4�*gc趐t�N�]���Ɏ�w^�z��{,rw�����0��|���o+��F�����_t�?[l��홓w=��"b���Ә�MX��mm�w�/`.����@>i�p�����<NX���uU�R�#����,s��"F*��N���	�Cp�J������dğ�v�|�g���k�Iڑ�p�=�����-g2!r�m��O��5�#�c�8e���'2��&��Uf��İ�(�<X q<��Lz�d�Vk����H�-�x5�!����Uw��+��~�N��b�;���9�;�>�c��l�f�n��b�؄��t��J���?l$�`)���tY�.��Sn��@�ѻN ��(H�<�j�ĝ�R^���=��q"�0a�
��V��W�\�sw����%��~�l�Q�S���z�M�LY^|��@6��!QX_Il�����1%��14߳r%F�R��:l1��� kgQ���u��8;Kn�hC�������]�(Q�|�#�cl�.;௅�H����7�X*|�����%��·�)���CH����G���~���_:�7.X������C*Ͳ-��lu}�� ����_������>}:���{h�5XO�-�
��Y�)��:�Hy���60ܭ1i끫�(���;pq{?���g��q���O3��GYp�FIp��e
2c`X�������[�v��*v���Y3��#�*pǋ�Ny_��c&�W� 0"�)	��T�]��E"S~�6��������W*�l%�\�J���	4А���0����f�5�����p����fv���*�ˏ<���������F&h~h4��������w�/
1�{�Yq�f�c��a���.F>���H,���=2�A�6f��� �#�^k	�����頡>�tM���Ì��)&�A���_�5�{$��Wэcg��V��Q�>��O"��w�&��xZ���e�/������q�*]�Z�9�os+����"�M�.ৰ�D���*��H �4}1#���C������%���u4�����!j~�M�eͮܭ���QX�EJ��J4`���,V.�*M:��F��1x^��ي쪾�gm��9b��F�W�5�Du��?���$�H}��\rw]���<� �<��\kq�t|����Mx ��=g��>�{��kN�K��N��F.� �q�r����Nd ଝ�'��XhPS�@���-��u{!8��QT\b �#>�k���k��y^��b�Sc��dǡ{�J��l�ڝ���,����f��t����,"4o�k�E�&���z����E���5��o��`��JQ������k6�߾\��;* �v�	��}z`a�:��g\1.U�p �4��:�+<��N;�f�ךC����8~HS"�k�:�U?<hzb��|͞��
AI���/�k�D��YlFb2�'�$�� �够'�p�n�*���Lau������hx�V`��s��+�l���r^9��=mY� d�$�R�:��l����Low�a<ŋs-�� �i
F��`w�ތ\�5f�-j]3����g	�.�1��Sg԰��:�_��[�;�^P�LǺ��0[�xyب�|��wC�Ʃ.�~��ZԔ�9h�b��`�����\�ccv�@��/^��۪�D�H�8Z��.Zk)�=#�*��W��r\%�v� f�D3ͳ��2LU{k�:o�.V���8Oy��\���7'1�>N穆�AQ�D$����A�䀪��1�g/	��Z��5]�����	Ik��=f �dmei|f��������"����=D,�d�2�1ѿ��'�@θ�1��E��h��r$!��⤙����{�Q����fw��D//�u�7%n	r�m��f�S��#�-��hCZuZ��G��,w
�2s<�{��OʉQ�]E�S/F�~/�2��9�Vۋ�$����N@��,mN�
�R��ѯ��!��NZ��sC�Duz߅�ވ�q�${"������/_�{T����ktI� ��q� k[�|�����wͩ$��-�W��ĒFD�vY�!�ь1T�`�fp��jsA냧��WN@�B�<�y��n�[�!sR�-��`���s��5� "�Yl-���&]�jr��������t�������]�;T0TVv͚�?a��&�Ac���HsI�D�ZWC�9�1{ZO.��R��|�{V�t���(XAЄF��F_��%��1�*1NW����������� ��%}��#+�H�+��5�:2r$@�Vo�5�#Q���������G��b]z�E�腱g&���Nh�K �?k�f���w���z*����y���
�^�)�.j�Lm%5�93��B�������B�"��y�$tP�
k��Q��B��io����[BwuY8�5V��J�����&����ađ=���38�lK����w~�ݎ�{���-g�q��VtȠF�n=�n�v�ӣ�$L��3]}~�3<��{|0{d��g��׀��mI�W�~�:�1d<�8Ux�1F�)�I��7��d�`��'�l��c�촼��Ԯ���s!uH�oA*9�JZ�-��Қe��S �J�jc&�-*ѝ�'�f9 <�#���ݫ[�/��V��UEe��=�Q�vZ��Ɏ��O=��0�U�̡��E^�m�7�4\�Qю��%�=�aB�D]Y#e�����3���ἳK��$a�6T��e�~�|���c�6�|�.����8y���8�Z���s�_���;���ܲ@�t�
t��U�XbW�%��_��౽L�d]�d�x�mI���=b3c�u2*kW�o
��6����䀄�gQq�V>�f<i��G��[���*D}����PG��s�I||�6��O�AkΈ�VDRw��u�0������6l�;Z��8�*qM�,8� s  �~���3=j�t������RQkF��������x��OO��qzȘ,o��
��pG�.�_�#�e�g/�!kUV�zߡ�T�`���n�.c;�x{¤�h�K�N��[�W,_��C?�K![�|�����Ӈ�`����O���abŶӤ[T�5�fd5�N��dה\��\J��@8�{#�[M�s~G&���ǹ[�lHB�VN�ZN&M=��C=Y���;��˄�˖��!�h9���dB\��5�"������H�H��ܚ��H�����]�����eZg�|��]�F�]wk�(Vldc���!���V���v=N8�	���%�!�����ȠQ���-ˮ.3[���p�5ױ�	���{��d�[������;`�X٩;�d�� ��4[��Y�t]5�[;�Q�������a�����@O���O|G�딲����T���Z��-�A��f�ֹ?1���n3��0	�W|ԉ�%���gE�H']���IE�Jz\�(P�)���b^ϖ��_�����d�`���S�`|tk���t.��e�(�'9D�X�xh7�.�H����F����}�,�%��b�^�X��o�y�7�U��/&�fK�?��~����D�`@      �      x������ � �      �      x������ � �      �   1   x�3�qqUpqU��wv��46�,�2�D☠)Q0�44 ���qqq �ji      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x�E�A�  ���wx��dD�eЪÐ6]ތ�`	�F�� ����>�#'�B�	��X ��n�rZ���O��b�C��pEoǭ�~el�J���x�����	2K#����mjgf�����d��$coˉ(�     