package ru.example.community.jirastat.issuestatus.dao;

import ru.example.community.jirastat.issuestatus.dao.entity.JiraStatusChange;

import java.util.List;

public interface JiraStatusDao {
    void save(JiraStatusChange entity);

    List<Integer> getUniqueIssueNumsToStatistics(String projectKey);

    List<JiraStatusChange> getIssuesChanges(String projectKey, List<Integer> issueNum);
}
