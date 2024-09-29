package com.quemistry.statistics_ms.repository;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.repository.entity.MCQStatistics;
import com.quemistry.statistics_ms.repository.entity.MCQStatisticsPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface McqStatisticsRepository extends ReadOnlyRepository<MCQStatistics, MCQStatisticsPK> {

    @Query(value = """
            SELECT new com.quemistry.statistics_ms.model.MCQStatisticsDto(s.mcqId, SUM(s.cntAttempt), SUM(s.cntCorrectAnswer))
            FROM MCQStatistics s
            GROUP BY s.mcqId
            ORDER BY (SUM(s.cntCorrectAnswer)/SUM(s.cntAttempt))
            """ ,
            countQuery = """
                SELECT COUNT(s.mcqId ) FROM MCQStatistics s GROUP BY s.mcqId
            """)
    Page<MCQStatisticsDto> findTotalAttempt(Pageable pageable);
}

