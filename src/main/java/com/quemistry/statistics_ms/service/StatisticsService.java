package com.quemistry.statistics_ms.service;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsResponse;
import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;

import java.util.List;

public interface StatisticsService {
   StatisticsResponse<List<MCQStatisticsDto>>  retrieveMcqStatics(int pageNo, int pageSize);
   StatisticsResponse<List<TopicSkillStatisticsDto>>  retrieveTopicSkillStatics(int pageNo, int pageSize);
}
