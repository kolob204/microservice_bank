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

INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (1, 'provide', '3', 'cancel', '5', '6', '7', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '=');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (4, NULL, '4', NULL, NULL, '11', '5', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '>');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (5, NULL, NULL, NULL, '55', '11', '5', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '<');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (6, NULL, NULL, NULL, '55', '11', '5', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '=');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (7, 'provide', NULL, 'original', '55', '11', '5', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '<');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (2, 'cancel', '33', 'cancel', '55', '11', '1', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '=');
INSERT INTO public.rulelist (id, action, code_type_of_balance, direction, source, summ, type, end_date, start_date, summ_compare) VALUES (3, 'provide', '1', NULL, '555', NULL, '5', '2019-12-25 00:00:00', '2019-12-15 00:00:00', '=');

INSERT INTO public.typeoft (id, type) VALUES (1, '1');
INSERT INTO public.typeoft (id, type) VALUES (4, '7856');
INSERT INTO public.typeoft (id, type) VALUES (5, '79');
INSERT INTO public.typeoft (id, type) VALUES (2, '7');
INSERT INTO public.typeoft (id, type) VALUES (3, '5');

INSERT INTO public.sourceoft (id, source) VALUES (4, '1214551');
INSERT INTO public.sourceoft (id, source) VALUES (5, '23');
INSERT INTO public.sourceoft (id, source) VALUES (1, '5');
INSERT INTO public.sourceoft (id, source) VALUES (2, '55');
INSERT INTO public.sourceoft (id, source) VALUES (3, '555');