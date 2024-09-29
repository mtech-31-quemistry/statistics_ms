package com.quemistry.statistics_ms.service;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsRequest;
import com.quemistry.statistics_ms.repository.McqStatisticsRepository;
import com.quemistry.statistics_ms.service.impl.StatisticsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class StatisticsServiceImplTest {

    @Mock
    McqStatisticsRepository mcqStatisticsRepository;

    @InjectMocks
    StatisticsServiceImpl statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testRetrieveMcqStatistic(){
        int pageSize = 5;
        int pageNo = 0;
        StatisticsRequest request = new StatisticsRequest();
        request.setPageSize(pageSize);
        request.setPageNo(pageNo);

        List<MCQStatisticsDto> mcqStatisticsList = new ArrayList<>();
        mcqStatisticsList.add(new MCQStatisticsDto(1,10,9));
        Page<MCQStatisticsDto> queryResult = new PageImpl<>(mcqStatisticsList);
        when(mcqStatisticsRepository.findTotalAttempt(PageRequest.of(pageNo, pageSize))).thenReturn(queryResult);

        var response = statisticsService.retrieveMcqStatics(pageNo, pageSize);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(pageNo, response.getPageNo());
        Assertions.assertEquals(pageSize, response.getPageSize());
        Assertions.assertEquals(1, response.getTotalRecords());
        Assertions.assertNotNull(response.getData());
    }
}
