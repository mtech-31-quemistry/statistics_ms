package com.quemistry.statistics_ms.service.impl;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.repository.McqStatisticsRepository;
import com.quemistry.statistics_ms.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final McqStatisticsRepository mcqStatisticsRepository;

    public StatisticsServiceImpl(McqStatisticsRepository mcqStatisticsRepository) {
        this.mcqStatisticsRepository = mcqStatisticsRepository;
    }

    @Override
    public List<MCQStatisticsDto> retrieveMcqStaticsForMonth(int year, int month) {

        return List.of();
    }

    @Override
    public List<MCQStatisticsDto> retrieveMcqStatics() {
        return mcqStatisticsRepository.findTotalAttempt();
    }
}
