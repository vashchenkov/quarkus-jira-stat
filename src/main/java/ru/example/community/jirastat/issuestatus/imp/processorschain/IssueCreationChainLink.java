package ru.example.community.jirastat.issuestatus.imp.processorschain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.community.jirastat.issuestatus.imp.dto.AddJiraIssueDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;
import ru.example.community.jirastat.issuestatus.processor.JiraIssueProcessor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ApplicationScoped
public class IssueCreationChainLink extends IssueChangeChainLink<AddJiraIssueDTO> {
    
    @Inject
    private JiraIssueProcessor issueProcessor;

    @Override
    public AddJiraIssueDTO getChangeItem(JiraChangeDTO change) {
        return change.addIssue;
    }

    @Override
    public void processChangeItem(AddJiraIssueDTO changeItem) {
        try {
            var dt = LocalDateTime.parse(changeItem.getCreationTime(), formatter).withNano(0);
            issueProcessor.addIssue(changeItem.getIssueId(), changeItem.getProjectKey(), changeItem.getIssueNum(), changeItem.getIssueType(), dt);
        } catch (DateTimeParseException ex) {
            log.error("Can't process change because of wrong DATE_FORMAT.\n" + changeItem, ex);
        } catch (NumberFormatException ex) {
            log.error("Can't process change because ISSUE_NUMBER not a number.\n" + changeItem, ex);
        }
    }

    private static final Logger log = LoggerFactory.getLogger(IssueCreationChainLink.class);

}
