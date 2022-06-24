package ru.example.community.jirastat.issuestatus.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "JIRA_ISSUES")
public class JiraIssue {
    private Long id;
    private long issueId;
    private String projectKey;
    private int issueNum;
    private String issueType;
    private LocalDateTime creationTime;

    public JiraIssue() {
    }

    public JiraIssue(Long id, long issueId, String projectKey, int issueNum, String issueType, LocalDateTime creationTime) {
        this.id = id;
        this.issueId = issueId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.issueType = issueType;
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JiraIssue jiraIssue = (JiraIssue) o;
        return issueId == jiraIssue.issueId && issueNum == jiraIssue.issueNum && Objects.equals(id, jiraIssue.id) && Objects.equals(projectKey, jiraIssue.projectKey) && Objects.equals(issueType, jiraIssue.issueType) && Objects.equals(creationTime, jiraIssue.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueId, projectKey, issueNum, issueType, creationTime);
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

    @Column(name = "ISSUE_TYPE")
    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    @Column(name = "CREATION_TIMESTAMP")
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
