package com.quemistry.statistics_ms.controller;

import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsRequest;
import com.quemistry.statistics_ms.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("mcq")
    public ResponseEntity<List<MCQStatisticsDto>> getStatistics(@RequestBody StatisticsRequest request){
        var result = statisticsService.retrieveMcqStatics();
        return ResponseEntity.ok(result);
    }
}
