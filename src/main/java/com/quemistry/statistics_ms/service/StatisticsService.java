package com.quemistry.statistics_ms.service;

import com.quemistry.statistics_ms.model.StatisticsResponse;

public interface StatisticsService {
   StatisticsResponse retrieveMcqStatics(int pageNo, int pageSize);
}
