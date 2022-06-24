package ru.example.community.jirastat.issuestatus.processor.impl;

import ru.example.community.jirastat.issuestatus.dao.JiraStatusDao;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraStatusChange;
import ru.example.community.jirastat.issuestatus.processor.JiraStatusProcessor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class JiraStatusProcessorImpl implements JiraStatusProcessor {
    @Inject
    private JiraStatusDao dao;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void addChange(long issueId, String projectKey, int issueNum, String oldStatus, String newStatus, LocalDateTime changeDateTime, long changeId) {
        var change = new JiraStatusChange(null, issueId, changeId, projectKey, issueNum, oldStatus, newStatus, changeDateTime);
        dao.save(change);
    }

    @Override
    public List<Integer> findUniqueIssuesNumbersForProject(String projectKey) {
        return dao.getUniqueIssueNumsToStatistics(projectKey);
    }

    @Override
    public List<JiraStatusChange> getChangesForIssues(String projectKey, List<Integer> issuesNumbers) {
        return dao.getIssuesChanges(projectKey, issuesNumbers);
    }
}
