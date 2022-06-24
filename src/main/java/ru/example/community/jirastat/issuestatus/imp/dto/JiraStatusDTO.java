package ru.example.community.jirastat.issuestatus.imp.dto;

public class JiraStatusDTO {
    public long issueId;
    public long changeId;
    public String projectKey;
    public int issueNum;
    public String oldStatus;
    public String newStatus;
    public String changeDateTime;

    public JiraStatusDTO() {
    }

    public JiraStatusDTO(long issueId, long changeId, String projectKey, int issueNum, String oldStatus, String newStatus, String changeDateTime) {
        this.issueId = issueId;
        this.changeId = changeId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changeDateTime = changeDateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "issueId:" + issueId +
                ", projectKey:'" + projectKey + '\'' +
                ", changeId:" + changeId  +
                ", issueNum:'" + issueNum + '\'' +
                ", oldStatus:'" + oldStatus + '\'' +
                ", newStatus:'" + newStatus + '\'' +
                ", changeDateTime:'" + changeDateTime + '\'' +
                '}';
    }
}
