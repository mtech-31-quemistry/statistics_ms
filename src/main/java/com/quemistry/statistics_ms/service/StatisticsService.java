package com.quemistry.statistics_ms.service;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StatisticsService {
   List<MCQStatisticsDto> retrieveMcqStaticsForMonth(int year, int month);
   List<MCQStatisticsDto> retrieveMcqStatics();
}
