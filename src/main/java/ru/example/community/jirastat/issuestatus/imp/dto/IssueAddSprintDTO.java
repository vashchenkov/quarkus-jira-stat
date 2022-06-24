package ru.example.community.jirastat.issuestatus.imp.dto;

public class IssueAddSprintDTO {
    public long issueId;
    public long changeId;
    public String projectKey;
    public int issueNum;
    public String changeDateTime;
    public String sprintName;
    public IssueAddSprintDTO() {
    }

    public IssueAddSprintDTO(long issueId, long changeId, String projectKey, int issueNum, String changeDateTime, String sprintName) {
        this.issueId = issueId;
        this.changeId = changeId;
        this.projectKey = projectKey;
        this.issueNum = issueNum;
        this.changeDateTime = changeDateTime;
        this.sprintName = sprintName;
    }

    @Override
    public String toString() {
        return "{" +
                "`issueId`:" + issueId +
                ", `changeId`:" + changeId +
                ", `projectKey`:'" + projectKey + '\'' +
                ", `issueNum`:" + issueNum +
                ", `changeDateTime`:'" + changeDateTime + '\'' +
                ", `sprintName`:'" + sprintName + '\'' +
                '}';
    }
}
