package com.quemistry.statistics_ms.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="topics_skills_stats")
@IdClass(TopicSkillStatisticsPK.class)
public class TopicSkillStatistics {
    @Id
    private int attemptYear;
    @Id
    private int attemptMonth;
    @Id
    private int attemptDay;
    @Id
    private long skillId;

    private long topicId;

    private String topicName;

    private String skillName;

    private long cntAttempt;

    private long cntCorrectAnswer;
}

