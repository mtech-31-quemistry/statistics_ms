package com.quemistry.statistics_ms.repository;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.repository.entity.MCQStatistics;
import com.quemistry.statistics_ms.repository.entity.MCQStatisticsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface McqStatisticsRepository extends ReadOnlyRepository<MCQStatistics, MCQStatisticsPK> {

    @Query("SELECT new com.quemistry.statistics_ms.model.MCQStatisticsDto(s.mcqId, SUM(s.cntAttempt), SUM(s.cntCorrectAnswer)) FROM MCQStatistics s GROUP BY s.mcqId")
    List<MCQStatisticsDto> findTotalAttempt();
}

