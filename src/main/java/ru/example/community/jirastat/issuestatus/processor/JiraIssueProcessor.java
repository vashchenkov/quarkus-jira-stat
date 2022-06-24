package ru.example.community.jirastat.issuestatus.processor;

import ru.example.community.jirastat.issuestatus.dao.entity.JiraIssue;

import java.time.LocalDateTime;
import java.util.List;

public interface JiraIssueProcessor {
    void addIssue(long issueId,
                  String projectKey,
                  int issueNum,
                  String issueType,
                  LocalDateTime creationTime);

    List<JiraIssue> getIssuesByIssueNumbers(String projectKey, List<Integer> issueNums);
}
