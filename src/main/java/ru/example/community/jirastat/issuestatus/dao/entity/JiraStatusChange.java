package ru.example.community.jirastat.issuestatus.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "JIRA_STATUS_CHANGES")
public class JiraStatusChange {
    private Long id;
    private long issueId;
    private long changeId;
    private String projectKey;
    private int issueNum;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime changeDateTime;

    public JiraStatusChange() {
    }

    public JiraStatusChange(Long id, long issueId, long changeId, String projectKey, int issueNum, String oldStatus, String newStatus, LocalDateTime changeDateTime) {
        this.id = id;
        this.issueId = issueId;
        this.changeId = changeId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changeDateTime = changeDateTime;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ISSUE_ID")
    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
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

    @Column(name = "OLD_STATUS")
    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    @Column(name = "NEW_STATUS")
    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    @Column(name = "CHANGE_TIMESTAMP")
    public LocalDateTime getChangeDateTime() {
        return changeDateTime;
    }

    public void setChangeDateTime(LocalDateTime changeDateTime) {
        this.changeDateTime = changeDateTime;
    }

    @Column(name = "CHANGE_ID")
    public long getChangeId() {
        return changeId;
    }

    public JiraStatusChange setChangeId(long changeId) {
        this.changeId = changeId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JiraStatusChange that = (JiraStatusChange) o;
        return issueId == that.issueId && issueNum == that.issueNum && Objects.equals(id, that.id) && Objects.equals(projectKey, that.projectKey) && Objects.equals(oldStatus, that.oldStatus) && Objects.equals(newStatus, that.newStatus) && Objects.equals(changeDateTime, that.changeDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueId, projectKey, issueNum, oldStatus, newStatus, changeDateTime);
    }
}
