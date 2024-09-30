package com.quemistry.statistics_ms.controller;

import com.quemistry.statistics_ms.constant.Pagination;
import com.quemistry.statistics_ms.model.MCQStatisticsDto;
import com.quemistry.statistics_ms.model.StatisticsResponse;
import com.quemistry.statistics_ms.model.TopicSkillStatisticsDto;
import com.quemistry.statistics_ms.service.StatisticsService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("mcq")
    public ResponseEntity<StatisticsResponse<List<MCQStatisticsDto>>> getMcqStatistics(
            @RequestParam(name="pageno", required = false, defaultValue = "0") @PositiveOrZero Integer pageNo,
            @RequestParam(name="pagesize", required = false, defaultValue = "10") @PositiveOrZero @Max(Pagination.MAX_PAGE_SIZE) Integer pageSize ) {
        var result = statisticsService.retrieveMcqStatics(pageNo, pageSize);
        return ResponseEntity.ok(result);
    }

    @GetMapping("topic-skill")
    public ResponseEntity<StatisticsResponse<List<TopicSkillStatisticsDto>>> getTopicSkillStatistics(
            @RequestParam(name="pageno", required = false, defaultValue = "0") @PositiveOrZero Integer pageNo,
            @RequestParam(name="pagesize", required = false, defaultValue = "10") @PositiveOrZero @Max(Pagination.MAX_PAGE_SIZE) Integer pageSize){
        var result = statisticsService.retrieveTopicSkillStatics(pageNo, pageSize);
        return ResponseEntity.ok(result);
    }
}
