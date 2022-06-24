package ru.example.community.jirastat.it.issuestatus.consummer;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.groups.Tuple;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import ru.example.community.jirastat.issuestatus.dao.JiraIssueDao;
import ru.example.community.jirastat.issuestatus.dao.JiraStatusDao;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraIssue;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraStatusChange;
import ru.example.community.jirastat.issuestatus.imp.JiraChangeConsumer;
import ru.example.community.jirastat.issuestatus.imp.dto.AddJiraIssueDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.IssueAddSprintDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraStatusDTO;
import ru.example.community.jirastat.sprintchange.dao.SprintChangeDao;
import ru.example.community.jirastat.sprintchange.dao.entity.IssueSpringAssignment;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class IssueChangeConsumerTests {

    @Inject
    private JiraChangeConsumer sut;

    @Inject
    private JiraStatusDao statusDao;
    @Inject
    private JiraIssueDao issueDao;
    @Inject
    private SprintChangeDao sprintChangeDao;

    @org.junit.jupiter.api.Test
    @DisplayName("Should appears new StatusChange when incoming change contains status change")
    @TestTransaction()
    public void gettingStatusChangeEvent(){

        var statusDTO = new JiraStatusDTO(ISSUE_ID, CHANGE_ID, PROJECT_KEY, ISSUE_NUM, OLD_STATUS, NEW_STATUS, CHANGE_DATE_TIME);
        JiraChangeDTO change = new JiraChangeDTO(statusDTO, null, null);
        sut.consumeJiraChange(change);

        List<JiraStatusChange> issuesChanges = statusDao.getIssuesChanges(PROJECT_KEY, List.of(ISSUE_NUM));
        assertThat(getLastStatusChangeAsList(issuesChanges))
                .extracting(
                        JiraStatusChange::getIssueId,
                        JiraStatusChange::getChangeId,
                        JiraStatusChange::getProjectKey,
                        JiraStatusChange::getIssueNum,
                        JiraStatusChange::getChangeDateTime,
                        JiraStatusChange::getNewStatus,
                        JiraStatusChange::getOldStatus)
                .contains(new Tuple(ISSUE_ID, CHANGE_ID, PROJECT_KEY, ISSUE_NUM, dt, NEW_STATUS, OLD_STATUS));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should appears new Issue when incoming change contains add issue")
    @TestTransaction()
    public void gettingNewIssueEvent(){
        AddJiraIssueDTO issueCreation = new AddJiraIssueDTO(ISSUE_ID, PROJECT_KEY, ISSUE_NUM, ISSUE_TYPE, CHANGE_DATE_TIME);
        JiraChangeDTO change = new JiraChangeDTO(null, issueCreation, null);
        sut.consumeJiraChange(change);

        var issues = issueDao.findIssues(PROJECT_KEY, List.of(ISSUE_NUM));
        assertThat(getLastIssueAsList(issues))
                .extracting(
                        JiraIssue::getIssueId,
                        JiraIssue::getProjectKey,
                        JiraIssue::getIssueNum,
                        JiraIssue::getCreationTime,
                        JiraIssue::getIssueType)
                .contains(new Tuple(ISSUE_ID, PROJECT_KEY, ISSUE_NUM, dt, ISSUE_TYPE));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should appears sprint changes when incoming change contains sprint assignment")
    @TestTransaction()
    public void gettingSprintChangeEvent(){
        IssueAddSprintDTO newSprint = new IssueAddSprintDTO(ISSUE_ID, CHANGE_ID, PROJECT_KEY, ISSUE_NUM, CHANGE_DATE_TIME, SPRINT_NAME);
        JiraChangeDTO change = new JiraChangeDTO(null, null, newSprint);
        sut.consumeJiraChange(change);

        var issues = sprintChangeDao.sprintAssignment(PROJECT_KEY, List.of(ISSUE_NUM));
        assertThat(getLastSprintAssignmentAsList(issues))
                .extracting(
                        IssueSpringAssignment::getIssueId,
                        IssueSpringAssignment::getProjectKey,
                        IssueSpringAssignment::getIssueNum,
                        IssueSpringAssignment::getChangeId,
                        IssueSpringAssignment::getAssignmentTime,
                        IssueSpringAssignment::getSprintName)
                .contains(new Tuple(ISSUE_ID, PROJECT_KEY, ISSUE_NUM, CHANGE_ID, dt, SPRINT_NAME));
    }

    @NotNull
    private List<JiraStatusChange> getLastStatusChangeAsList(List<JiraStatusChange> changes) {
        return changes.stream().max((o1, o2) -> {
            long diff = o1.getIssueId() - o2.getIssueId();
            if (diff > 0) {
                return 1;
            }
            ;
            if (diff < 0) {
                return -1;
            }
            ;
            return 0;
        }).stream().toList();
    }

    @NotNull
    private List<JiraIssue> getLastIssueAsList(List<JiraIssue> changes) {
        return changes.stream().max((o1, o2) -> {
            long diff = o1.getIssueId() - o2.getIssueId();
            if (diff > 0) {
                return 1;
            }
            ;
            if (diff < 0) {
                return -1;
            }
            ;
            return 0;
        }).stream().toList();
    }

    @NotNull
    private List<IssueSpringAssignment> getLastSprintAssignmentAsList(List<IssueSpringAssignment> changes) {
        return changes.stream().max((o1, o2) -> {
            long diff = o1.getIssueId() - o2.getIssueId();
            if (diff > 0) {
                return 1;
            }
            ;
            if (diff < 0) {
                return -1;
            }
            ;
            return 0;
        }).stream().toList();
    }


    public static final long CHANGE_ID = 845;
    public static final String PROJECT_KEY = "IM";
    public static final int ISSUE_NUM = 834;
    public static final String OLD_STATUS = "NEW";
    public static final String ISSUE_TYPE = "Task";
    public static final String SPRINT_NAME = "Sprint 848";
    public static final String NEW_STATUS = "OPEN";
    public static final String CHANGE_DATE_TIME = "2022-06-15 14:13:54";
    private static final LocalDateTime dt = LocalDateTime.of(2022,6,15,14,13,54);

    private static final long ISSUE_ID = System.currentTimeMillis();
}
