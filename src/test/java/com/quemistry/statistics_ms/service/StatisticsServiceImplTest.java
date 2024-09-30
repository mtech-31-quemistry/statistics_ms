package com.quemistry.statistics_ms.service;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsRequest;
import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;
import com.quemistry.statistics_ms.repository.McqStatisticsRepository;
import com.quemistry.statistics_ms.repository.TopicSkillStatisticsRepository;
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

    @Mock
    TopicSkillStatisticsRepository topicSkillStatisticsRepository;

    @InjectMocks
    StatisticsServiceImpl statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

    @Test
    void testRetrieveTopicSkillStatistic(){
        int pageSize = 5;
        int pageNo = 0;
        StatisticsRequest request = new StatisticsRequest();
        request.setPageSize(pageSize);
        request.setPageNo(pageNo);

        List<TopicSkillStatisticsDto> tsStatisticsList = new ArrayList<>();
        tsStatisticsList.add(new TopicSkillStatisticsDto(1,"topic A",1, "skill A", 10,9));
        Page<TopicSkillStatisticsDto> queryResult = new PageImpl<>(tsStatisticsList);
        when(topicSkillStatisticsRepository.findTotalAttempt(PageRequest.of(pageNo, pageSize))).thenReturn(queryResult);

        var response = statisticsService.retrieveTopicSkillStatics(pageNo, pageSize);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(pageNo, response.getPageNo());
        Assertions.assertEquals(pageSize, response.getPageSize());
        Assertions.assertEquals(1, response.getTotalRecords());
        Assertions.assertNotNull(response.getData());
    }
}
