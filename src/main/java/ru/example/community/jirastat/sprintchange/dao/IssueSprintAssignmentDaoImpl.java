package ru.example.community.jirastat.sprintchange.dao;

import ru.example.community.jirastat.sprintchange.dao.entity.IssueSpringAssignment;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class IssueSprintAssignmentDaoImpl implements SprintChangeDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(IssueSpringAssignment entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public List<IssueSpringAssignment> sprintAssignment(String projectKey, Collection<Integer> issueNums) {
        return em.createQuery("""
    select change from IssueSpringAssignment as change where change.projectKey = :proj_key and change.issueNum in (:nums)
    order by change.issueNum, change.assignmentTime
    """)
                .setParameter("proj_key", projectKey)
                .setParameter("nums", issueNums)
                .getResultList();

    }
}
