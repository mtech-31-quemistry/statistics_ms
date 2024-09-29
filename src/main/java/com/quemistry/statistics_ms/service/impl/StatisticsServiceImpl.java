package com.quemistry.statistics_ms.service.impl;

import com.quemistry.statistics_ms.model.StatisticsResponse;
import com.quemistry.statistics_ms.repository.McqStatisticsRepository;
import com.quemistry.statistics_ms.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final McqStatisticsRepository mcqStatisticsRepository;

    public StatisticsServiceImpl(McqStatisticsRepository mcqStatisticsRepository) {
        this.mcqStatisticsRepository = mcqStatisticsRepository;
    }

    @Override
    public StatisticsResponse retrieveMcqStatics(int pageNo, int pageSize) {
        var paging = PageRequest.of(pageNo, pageSize);

        var result = mcqStatisticsRepository.findTotalAttempt(paging);
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setData(result.get().toList());
        statisticsResponse.setPageSize(pageSize);
        statisticsResponse.setPageNo(pageNo);
        statisticsResponse.setTotalRecords(result.getTotalElements());
        return statisticsResponse;
    }
}
