package ru.example.community.jirastat.sprintchange.processor;

import java.time.LocalDateTime;

public interface SprintChangeProcessor {
    void addSprintChange(long issueId, long changeId, String projectKey, int issueNum, LocalDateTime changeTime, String sprintName);
}
