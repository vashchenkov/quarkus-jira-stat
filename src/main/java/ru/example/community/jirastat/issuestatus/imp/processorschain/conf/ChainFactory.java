package ru.example.community.jirastat.issuestatus.imp.processorschain.conf;

import ru.example.community.jirastat.issuestatus.imp.processorschain.IssueChangeChainLink;
import ru.example.community.jirastat.issuestatus.imp.processorschain.IssueCreationChainLink;
import ru.example.community.jirastat.issuestatus.imp.processorschain.StatusChangeChainLink;
import ru.example.community.jirastat.sprintchange.imp.processorschain.SprintChangeChainLink;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ChainFactory {
    @Inject
    private IssueCreationChainLink creationChainLink;
    @Inject
    private StatusChangeChainLink statusChangeChainLink;
    @Inject
    private SprintChangeChainLink sprintChangeChainLink;
    @Produces
    public List<IssueChangeChainLink> generateLinksChain(){
        return List.of(creationChainLink,statusChangeChainLink,sprintChangeChainLink);
    }
}
