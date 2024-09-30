package com.quemistry.statistics_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicSkillStatisticsDto {
    private long topicId;
    private String topicName;
    private long skillId;
    private String skillName;
    private long cntAttempt;
    private long cntCorrectAnswer;
}
