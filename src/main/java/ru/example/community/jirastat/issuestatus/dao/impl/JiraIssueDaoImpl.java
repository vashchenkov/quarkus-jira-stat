package ru.example.community.jirastat.issuestatus.dao.impl;

import ru.example.community.jirastat.issuestatus.dao.JiraIssueDao;
import ru.example.community.jirastat.issuestatus.dao.entity.JiraIssue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class JiraIssueDaoImpl implements JiraIssueDao {

    @Inject
    private EntityManager em;

    @Override
    public void save(JiraIssue entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    public JiraIssue findIssue(String projectKey, int issueNum) {
        try {
            var result = em.createQuery("""
                    select issue from JiraIssue issue  where issue.projectKey =: proj_key and issue.issueNum =: issue_num
                    """)
                    .setParameter("proj_key", projectKey)
                    .setParameter("issue_num", issueNum)
                    .getSingleResult();
            return (JiraIssue) result;
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Override
    public List<JiraIssue> findIssues(String projectKey, Collection<Integer> issueNums) {
        return em.createQuery("""
    select issue from JiraIssue as issue where issue.projectKey = :proj_key and issue.issueNum in (:nums)
    order by issue.issueNum
    """)
                .setParameter("proj_key", projectKey)
                .setParameter("nums", issueNums)
                .getResultList();
    }
}
