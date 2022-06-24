package ru.example.community.jirastat.issuestatus.dao.impl;

import ru.example.community.jirastat.issuestatus.dao.JiraStatusDao;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraStatusChange;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class JiraStatusDaoImpl implements JiraStatusDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(JiraStatusChange entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public List<Integer> getUniqueIssueNumsToStatistics(String projectKey) {
        var result = em.createQuery("select distinct (change.issueNum) from JiraStatusChange as change where change.projectKey =: proj_key")
                .setParameter("proj_key", projectKey).getResultList();
        return result;
    }

    @Override
    public List<JiraStatusChange> getIssuesChanges(String projectKey, List<Integer> issueNum) {
        return em.createQuery("""
    select change from JiraStatusChange as change where change.projectKey = :proj_key and change.issueNum in (:nums)
    order by change.issueNum, change.changeDateTime
    """)
                .setParameter("proj_key", projectKey)
                .setParameter("nums", issueNum)
                .getResultList();
    }
}
