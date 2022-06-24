package ru.example.community.jirastat.sprintchange.dao;

import ru.example.community.jirastat.sprintchange.dao.entity.IssueSpringAssignment;

import java.util.Collection;
import java.util.List;

public interface SprintChangeDao {

    void save(IssueSpringAssignment assignment);

    List<IssueSpringAssignment> sprintAssignment(String projectKey, Collection<Integer> issueNums);
}
