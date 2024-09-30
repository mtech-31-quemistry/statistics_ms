
CREATE OR REPLACE PROCEDURE qms_report.generatestatistics(
	)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
BEGIN

	DROP TABLE IF EXISTS mcq_attempts;

	CREATE TEMP TABLE mcq_attempts AS
		SELECT mcq_id, joptions->>'no' as correct_answer, attempt.option_no
			, EXTRACT(DAY from attempt.attempt_time) as attemp_day, EXTRACT(MONTH from attempt.attempt_time) as attemp_month
			, EXTRACT(YEAR from attempt.attempt_time) as attemp_year
		FROM qms_quiz.attempt attempt
		INNER JOIN qms_question.mcq mcq on mcq.id = attempt.mcq_id
		CROSS JOIN lateral json_array_elements(mcq.options::json) as joptions
		WHERE joptions->>'isAnswer' ='true' and attempt_time is not null
			and NOW()::date - attempt_time::date <= 1; -- today and yesterdays records

	WITH topics_skills_stats AS (
		SELECT attemp_year, attemp_month, attemp_day
			, t.id as topic_id, t.name as topic_name, s.id as skill_id, s.name as skill_name
			, COUNT(*) as cnt_attempt
			, SUM(CASE WHEN correct_answer::int8 = option_no THEN 1 ELSE 0 END) as cnt_correct_answer
		FROM mcq_attempts
		INNER JOIN qms_question.mcq_skills ms on ms.mcq_id = mcq_attempts.mcq_id
		INNER JOIN qms_question.skill s on s.id = ms.skill_id
		INNER JOIN qms_question.topic t on t.id = s.topic_id
		GROUP BY t.id, t.name, s.id, s.name, attemp_day, attemp_month, attemp_year
		ORDER BY t.id, t.name, s.id, s.name, attemp_day, attemp_month, attemp_year
	)
	MERGE INTO qms_report.topics_skills_stats as dest
	USING topics_skills_stats as src on src.topic_id = dest.topic_id and src.topic_name = dest.topic_name
										and src.skill_id = dest.skill_id and src.skill_name = dest.skill_name
										and src.attemp_day = dest.attemp_day
										and src.attemp_month = dest.attemp_month
										and src.attemp_year = dest.attemp_year
	WHEN MATCHED THEN
		UPDATE SET cnt_attempt = src.cnt_attempt, cnt_correct_answer = src.cnt_correct_answer
	WHEN NOT MATCHED THEN
		INSERT (attemp_year, attemp_month, attemp_day, topic_id, topic_name, skill_id, skill_name, cnt_attempt, cnt_correct_answer)
		VALUES(src.attemp_year, src.attemp_month, src.attemp_day, src.topic_id, src.topic_name, src.skill_id, src.skill_name, src.cnt_attempt, src.cnt_correct_answer);

	WITH mcq_stats AS (
		SELECT attemp_year, attemp_month, attemp_day,  mcq_id
			, COUNT(*) as cnt_attempt
			, SUM(CASE WHEN correct_answer::int8 = option_no THEN 1 ELSE 0 END) as cnt_correct_answer
		FROM mcq_attempts
		GROUP BY mcq_id, attemp_day, attemp_month, attemp_year
		ORDER BY mcq_id, attemp_day, attemp_month, attemp_year
	)
	MERGE INTO qms_report.mcq_stats as dest
	USING mcq_stats as src on src.mcq_id = dest.mcq_id
							and src.attemp_day = dest.attemp_day
							and src.attemp_month = dest.attemp_month
							and src.attemp_year = dest.attemp_year
	WHEN MATCHED THEN
		UPDATE SET cnt_attempt = src.cnt_attempt, cnt_correct_answer = src.cnt_correct_answer
	WHEN NOT MATCHED THEN
		INSERT (attemp_year, attemp_month, attemp_day, mcq_id, cnt_attempt, cnt_correct_answer)
		VALUES(src.attemp_year, src.attemp_month, src.attemp_day, src.mcq_id, src.cnt_attempt, src.cnt_correct_answer);

END;
$BODY$;
