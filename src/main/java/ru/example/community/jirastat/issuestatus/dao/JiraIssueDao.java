package ru.example.community.jirastat.issuestatus.dao;

import ru.example.community.jirastat.issuestatus.dao.entity.JiraIssue;

import java.util.Collection;
import java.util.List;

public interface JiraIssueDao {
    void save(JiraIssue jiraIssue);

    JiraIssue findIssue(String projectKey, int issueNum);

    List<JiraIssue> findIssues(String projectKey, Collection<Integer> issueNums);
}
