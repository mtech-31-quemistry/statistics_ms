-- Table: qms_report.mcq_stats

-- DROP TABLE IF EXISTS qms_report.mcq_stats;

CREATE TABLE IF NOT EXISTS qms_report.mcq_stats
(
    attemp_year numeric,
    attemp_month numeric,
    attemp_day numeric,
    mcq_id bigint,
    cnt_attempt bigint,
    cnt_correct_answer bigint
)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS qms_report.topics_skills_stats
(
    attemp_year numeric,
    attemp_month numeric,
    attemp_day numeric,
    topic_id integer,
    topic_name character varying(255) COLLATE pg_catalog."default",
    skill_id integer,
    skill_name character varying(255) COLLATE pg_catalog."default",
    cnt_attempt bigint,
    cnt_correct_answer bigint
    )
    TABLESPACE pg_default;
