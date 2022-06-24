package ru.example.community.jirastat.issuestatus.imp.dto;

public class AddJiraIssueDTO {
    private long issueId;
    private String projectKey;
    private int issueNum;
    private String issueType;
    private String creationTime;

    public AddJiraIssueDTO(long issueId, String projectKey, int issueNum, String issueType, String creationTime) {
        this.issueId = issueId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.issueType = issueType;
        this.creationTime = creationTime;
    }

    public AddJiraIssueDTO() {
    }

    public long getIssueId() {
        return issueId;
    }

    public AddJiraIssueDTO setIssueId(long issueId) {
        this.issueId = issueId;
        return this;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public AddJiraIssueDTO setProjectKey(String projectKey) {
        this.projectKey = projectKey;
        return this;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public AddJiraIssueDTO setIssueNum(int issueNum) {
        this.issueNum = issueNum;
        return this;
    }

    public String getIssueType() {
        return issueType;
    }

    public AddJiraIssueDTO setIssueType(String issueType) {
        this.issueType = issueType;
        return this;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public AddJiraIssueDTO setCreationTime(String creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    @Override
    public String toString() {
        return """
                {"issueId": %s,"projectKey": "%s","issueNum": %s,"issueType": "%s","creationTime": "%s"}""".formatted(issueId, projectKey, issueNum, issueType, creationTime);
    }
}
