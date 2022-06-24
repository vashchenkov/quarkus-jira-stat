package ru.example.community.jirastat.issuestatus.imp.dto;

public class JiraChangeDTO {
    public JiraChangeDTO() {
    }

    public JiraChangeDTO(JiraStatusDTO statusChange, AddJiraIssueDTO addIssue, IssueAddSprintDTO newSprint) {
        this.statusChange = statusChange;
        this.addIssue = addIssue;
        this.newSprint = newSprint;
    }

    public JiraStatusDTO statusChange;

    public AddJiraIssueDTO addIssue;

    public IssueAddSprintDTO newSprint;

    @Override
    public String toString() {
        return """
                {
                    "statusChange":" %s",
                    "addIssue":" %s",
                    "newSprint":" %s"
                }""".formatted(statusChange, addIssue, newSprint);
    }
}
