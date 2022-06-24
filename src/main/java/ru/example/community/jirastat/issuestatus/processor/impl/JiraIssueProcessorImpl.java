package ru.example.community.jirastat.issuestatus.processor.impl;

import ru.example.community.jirastat.issuestatus.dao.JiraIssueDao;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraIssue;
import ru.example.community.jirastat.issuestatus.processor.JiraIssueProcessor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class JiraIssueProcessorImpl implements JiraIssueProcessor {
    @Inject
    private JiraIssueDao dao;

    @Override
    @Transactional
    public void addIssue(long issueId, String projectKey, int issueNum, String issueType, LocalDateTime creationTime) {
        JiraIssue jiraIssue = new JiraIssue(null, issueId, projectKey, issueNum, issueType, creationTime);
        dao.save(jiraIssue);
    }

    @Override
    public List<JiraIssue> getIssuesByIssueNumbers(String projectKey, List<Integer> issueNums) {
        return dao.findIssues(projectKey, issueNums);
    }
}
