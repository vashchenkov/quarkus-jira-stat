package ru.example.community.jirastat.sprintchange.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ISSUE_SPRINT_ASSIGNMENTS")
public class IssueSpringAssignment {
    private Long id;
    private long issueId;
    private long changeId;
    private String projectKey;
    private int issueNum;
    private LocalDateTime assignmentTime;
    private String sprintName;


    public IssueSpringAssignment() {
    }

    public IssueSpringAssignment(Long id, long issueId, long changeId, String projectKey, int issueNum, LocalDateTime assignmentTime, String sprintName) {
        this.id = id;
        this.issueId = issueId;
        this.changeId = changeId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.assignmentTime = assignmentTime;
        this.sprintName = sprintName;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ISSUE_ID")
    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    @Column(name = "CHANGE_ID")
    public long getChangeId() {
        return changeId;
    }

    public void setChangeId(long changeId) {
        this.changeId = changeId;
    }

    @Column(name = "PROJECT_KEY")
    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    @Column(name = "ISSUE_NUM")
    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    @Column(name = "ASSIGNMENT_TIME")
    public LocalDateTime getAssignmentTime() {
        return assignmentTime;
    }

    public void setAssignmentTime(LocalDateTime creationTime) {
        this.assignmentTime = creationTime;
    }

    @Column(name = "SPRINT_NAME")
    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueSpringAssignment that = (IssueSpringAssignment) o;

        if (issueId != that.issueId) return false;
        if (changeId != that.changeId) return false;
        if (issueNum != that.issueNum) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!projectKey.equals(that.projectKey)) return false;
        if (!assignmentTime.equals(that.assignmentTime)) return false;
        return sprintName.equals(that.sprintName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (issueId ^ (issueId >>> 32));
        result = 31 * result + (int) (changeId ^ (changeId >>> 32));
        result = 31 * result + projectKey.hashCode();
        result = 31 * result + issueNum;
        result = 31 * result + assignmentTime.hashCode();
        result = 31 * result + sprintName.hashCode();
        return result;
    }
}
