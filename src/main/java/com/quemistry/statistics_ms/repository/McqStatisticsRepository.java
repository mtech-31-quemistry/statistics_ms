package com.quemistry.statistics_ms.repository;

import com.quemistry.statistics_ms.repository.entity.MCQStatistics;
import com.quemistry.statistics_ms.repository.entity.MCQStatisticsPK;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StatisticsRepository extends ReadOnlyRepository<MCQStatistics, MCQStatisticsPK> {

}

