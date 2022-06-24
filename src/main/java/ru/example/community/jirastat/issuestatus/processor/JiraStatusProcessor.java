package ru.example.community.jirastat.issuestatus.processor;

import ru.example.community.jirastat.issuestatus.dao.entity.JiraStatusChange;

import java.time.LocalDateTime;
import java.util.List;

public interface JiraStatusProcessor {
    void addChange(long issueId,
                   String projectKey,
                   int issueNum,
                   String oldStatus,
                   String newStatus,
                   LocalDateTime changeDateTime,
                   long changeId);

    List<Integer> findUniqueIssuesNumbersForProject(String projectKey);

    List<JiraStatusChange> getChangesForIssues(String projectKey, List<Integer> issuesNumbers);
}
