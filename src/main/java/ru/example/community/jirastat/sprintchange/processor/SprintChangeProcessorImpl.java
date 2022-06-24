package ru.example.community.jirastat.sprintchange.processor;

import ru.example.community.jirastat.sprintchange.dao.SprintChangeDao;
import ru.example.community.jirastat.sprintchange.dao.entity.IssueSpringAssignment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class SprintChangeProcessorImpl implements SprintChangeProcessor{

    @Inject
    private SprintChangeDao dao;
    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void addSprintChange(long issueId, long changeId, String projectKey, int issueNum, LocalDateTime changeTime, String sprintName) {
        var assignment = new IssueSpringAssignment(null, issueId, changeId, projectKey, issueNum, changeTime, sprintName);
        dao.save(assignment);
    }
}
