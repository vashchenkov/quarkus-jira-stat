package ru.example.community.jirastat.issuestatus.imp.processorschain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.community.jirastat.issuestatus.imp.dto.JiraChangeDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class IssueChangeChain {

    @Inject
//    @All
    List<IssueChangeChainLink> links;

    public void processIssueChange(JiraChangeDTO change) {
        logger.info("Try to process " + change + " through " + links);
        if(change == null)
            return;

        links.forEach(link ->{
            var changeItem = link.getChangeItem(change);
            if (changeItem != null) {
                link.processChangeItem(changeItem);
            }
        });

    }

    void setLinks(List<IssueChangeChainLink> links) {
        this.links = links;
    }

    private static final Logger logger = LoggerFactory.getLogger(IssueChangeChain.class);
}
