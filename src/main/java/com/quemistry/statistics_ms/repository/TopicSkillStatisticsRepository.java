package com.quemistry.statistics_ms.repository;

import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;
import com.quemistry.statistics_ms.repository.entity.TopicSkillStatistics;
import com.quemistry.statistics_ms.repository.entity.TopicSkillStatisticsPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicSkillStatisticsRepository extends ReadOnlyRepository<TopicSkillStatistics, TopicSkillStatisticsPK> {

    @Query(value = """
            SELECT new com.quemistry.statistics_ms.model.TopicSkillStatisticsDto(s.topicId, s.topicName, s.skillId, s.skillName, SUM(s.cntAttempt), SUM(s.cntCorrectAnswer))
            FROM TopicSkillStatistics s
            GROUP BY s.topicId, s.topicName, s.skillId, s.skillName
            ORDER BY (SUM(s.cntCorrectAnswer)/SUM(s.cntAttempt))
            """ ,
            countQuery = """
                SELECT COUNT(s.skillId ) FROM TopicSkillStatistics s
                GROUP BY s.topicId, s.topicName, s.skillId, s.skillName
            """)
    Page<TopicSkillStatisticsDto> findTotalAttempt(Pageable pageable);
}

