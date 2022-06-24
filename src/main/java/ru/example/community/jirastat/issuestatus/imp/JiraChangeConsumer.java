package ru.example.community.jirastat.issuestatus.imp;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;
import ru.example.community.jirastat.issuestatus.imp.processorschain.IssueChangeChain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class JiraChangeConsumer {

    @Inject
    private IssueChangeChain chain;

    @Incoming("status-change")
    @Blocking
    public void consumeJiraChange(JiraChangeDTO change) {
        try {
            log.info("Receive change " + change + " from kafka");

            chain.processIssueChange(change);

        } catch (Throwable e) {
            log.error("Error reading change "+ change, e);
        }
    }

    private static final Logger log = LoggerFactory.getLogger("JIRA_CONSUMER");
}
