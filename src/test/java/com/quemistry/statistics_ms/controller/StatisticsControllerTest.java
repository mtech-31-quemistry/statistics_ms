package com.quemistry.statistics_ms.controller;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsResponse;
import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;
import com.quemistry.statistics_ms.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService statisticsService;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(new StatisticsController(statisticsService))
                .build();
    }

    @Test
    void givenGetMcqStatistics_thenStatus200() throws Exception {
        int pageNo = 0;
        int pageSize = 5;
        List<MCQStatisticsDto> mcqStatisticsList = new ArrayList<>();
        mcqStatisticsList.add(new MCQStatisticsDto(1,10,9));

        StatisticsResponse<List<MCQStatisticsDto>> response = new StatisticsResponse<>();
        response.setPageSize(pageSize);
        response.setPageNo(pageNo);
        response.setTotalRecords(1);
        response.setData(mcqStatisticsList);

        when(statisticsService.retrieveMcqStatics(pageNo, pageSize)).thenReturn(response);

        mockMvc.perform(get("/v1/stats/mcq")
                .param("pageno", "0")
                .param("pagesize", "5")
                );

        verify(statisticsService).retrieveMcqStatics(pageNo, pageSize);
    }

    @Test
    void givenGetTopicSkillStatistics_thenStatus200() throws Exception {
        int pageNo = 0;
        int pageSize = 5;
        List<TopicSkillStatisticsDto> tsStatisticsList = new ArrayList<>();
        tsStatisticsList.add(new TopicSkillStatisticsDto(1,"topic A",1, "skill A", 10,9));

        StatisticsResponse<List<TopicSkillStatisticsDto>> response = new StatisticsResponse<>();
        response.setPageSize(pageSize);
        response.setPageNo(pageNo);
        response.setTotalRecords(1);
        response.setData(tsStatisticsList);

        when(statisticsService.retrieveTopicSkillStatics(pageNo, pageSize)).thenReturn(response);

        mockMvc.perform(get("/v1/stats/topic-skill")
                        .param("pageno", "0")
                        .param("pagesize", "5")
                );

        verify(statisticsService).retrieveTopicSkillStatics(pageNo, pageSize);
    }
}
