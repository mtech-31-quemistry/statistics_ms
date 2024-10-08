package com.quemistry.statistics_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse<T> {

    private T data;
    private int pageNo;
    private int pageSize;
    private long totalRecords;

}
