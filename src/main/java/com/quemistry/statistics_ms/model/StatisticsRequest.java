package com.quemistry.statistics_ms.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsRequest {
    private int pageNo = 0;
    private int pageSize = 10;
}
