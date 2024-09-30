package com.quemistry.statistics_ms.service.impl;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsResponse;
import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;
import com.quemistry.statistics_ms.repository.McqStatisticsRepository;
import com.quemistry.statistics_ms.repository.TopicSkillStatisticsRepository;
import com.quemistry.statistics_ms.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final McqStatisticsRepository mcqStatisticsRepository;

    private final TopicSkillStatisticsRepository topicSkillStatisticsRepository;

    public StatisticsServiceImpl(McqStatisticsRepository mcqStatisticsRepository, TopicSkillStatisticsRepository topicSkillStatisticsRepository) {
        this.mcqStatisticsRepository = mcqStatisticsRepository;
        this.topicSkillStatisticsRepository = topicSkillStatisticsRepository;
    }

    @Override
    public StatisticsResponse<List<MCQStatisticsDto>> retrieveMcqStatics(int pageNo, int pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);

        var result = mcqStatisticsRepository.findTotalAttempt(paging);
        StatisticsResponse<List<MCQStatisticsDto>> statisticsResponse = new StatisticsResponse<>();
        statisticsResponse.setData(result.get().toList());
        statisticsResponse.setPageSize(pageSize);
        statisticsResponse.setPageNo(pageNo);
        statisticsResponse.setTotalRecords(result.getTotalElements());
        return statisticsResponse;
    }

    @Override
    public StatisticsResponse<List<TopicSkillStatisticsDto>> retrieveTopicSkillStatics(int pageNo, int pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);
        var result = topicSkillStatisticsRepository.findTotalAttempt(paging);

        StatisticsResponse<List<TopicSkillStatisticsDto>> statisticsResponse = new StatisticsResponse<>();
        statisticsResponse.setData(result.get().toList());
        statisticsResponse.setPageSize(pageSize);
        statisticsResponse.setPageNo(pageNo);
        statisticsResponse.setTotalRecords(result.getTotalElements());

        return statisticsResponse;
    }
}
