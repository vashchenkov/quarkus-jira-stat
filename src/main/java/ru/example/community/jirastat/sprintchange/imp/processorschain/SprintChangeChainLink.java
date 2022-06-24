package ru.example.community.jirastat.sprintchange.imp.processorschain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.community.jirastat.issuestatus.imp.dto.IssueAddSprintDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;
import ru.example.community.jirastat.issuestatus.imp.processorschain.IssueChangeChainLink;
import ru.example.community.jirastat.sprintchange.processor.SprintChangeProcessor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ApplicationScoped
public class SprintChangeChainLink extends IssueChangeChainLink<IssueAddSprintDTO> {

    @Inject
    private SprintChangeProcessor processor;
    @Override
    public IssueAddSprintDTO getChangeItem(JiraChangeDTO change) {
        return change.newSprint;
    }

    @Override
    public void processChangeItem(IssueAddSprintDTO changeItem) {
        try {
            var dt = LocalDateTime.parse(changeItem.changeDateTime, formatter).withNano(0);
            processor.addSprintChange(changeItem.issueId, changeItem.changeId, changeItem.projectKey, changeItem.issueNum, dt, changeItem.sprintName);
        } catch (DateTimeParseException ex) {
            log.error("Can't process change because of wrong DATE_FORMAT.\n" + changeItem, ex);
        } catch (NumberFormatException ex) {
            log.error("Can't process change because ISSUE_NUMBER not a number.\n" + changeItem, ex);
        }

    }

    private static final Logger log = LoggerFactory.getLogger(SprintChangeChainLink.class);

}
