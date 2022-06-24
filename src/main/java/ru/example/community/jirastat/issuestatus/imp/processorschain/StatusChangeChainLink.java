package ru.example.community.jirastat.issuestatus.imp.processorschain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraStatusDTO;
import ru.example.community.jirastat.issuestatus.processor.JiraStatusProcessor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ApplicationScoped
public class StatusChangeChainLink extends IssueChangeChainLink<JiraStatusDTO> {

    @Inject
    private JiraStatusProcessor statusProcessor;
    @Override
    public JiraStatusDTO getChangeItem(JiraChangeDTO change) {
        return change.statusChange;
    }

    @Override
    public void processChangeItem(JiraStatusDTO changeItem) {
        try {
            log.info("Start to add status change for issue");
            var dt = LocalDateTime.parse(changeItem.changeDateTime, formatter).withNano(0);
            statusProcessor.addChange(changeItem.issueId, changeItem.projectKey, changeItem.issueNum, changeItem.oldStatus, changeItem.newStatus, dt, changeItem.changeId);
        } catch (DateTimeParseException ex) {
            log.error("Can't process change because of wrong DATE_FORMAT.\n" + changeItem, ex);
        } catch (NumberFormatException ex) {
            log.error("Can't process change because ISSUE_NUMBER not a number.\n" + changeItem, ex);
        }
    }

    private static final Logger log = LoggerFactory.getLogger(StatusChangeChainLink.class);
}
