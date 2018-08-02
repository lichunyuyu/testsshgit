package com.one.dao;

import com.one.entity.Vote;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by vtstar on 2017/12/29.
 */
@Repository
public class VoteDao implements IVoteDao {

    //注入sess
    @Autowired
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    @Override
    public void addVote(Vote vote) {

        sessionFactory.getCurrentSession().save(vote);
    }

    @Override
    public List<Vote> getAllVote() {
        String hql = "from Vote";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public boolean delVote(String id) {
        String hql = "delete Vote v where v.id =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,id);
        return (query.executeUpdate()>0);
    }

    @Override
    public Vote getVote(String id) {
        String hql = "from Vote v where v.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0,id);
        return (Vote) query.uniqueResult();
    }

    @Override
    public boolean updateVote(Vote vote) {
        String hql = "update Vote v set v.voteName=?,v.voteCount=? where v.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        System.out.println("vote.getVoteCount()="+vote.getVoteCount());
        if(vote.getVoteCount()==null){
            vote.setVoteCount(0);
        }
        query.setString(0,vote.getVoteName());
        query.setInteger(1,vote.getVoteCount());
        query.setString(2,vote.getId());
        return (query.executeUpdate()>0);
    }

    @Override
    public boolean updateVoteCount(Vote vote) {
        String hql = "update Vote v set v.voteName=?,v.voteCount=? where v.id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if(vote.getVoteCount()==null){
            vote.setVoteCount(0);
        }
        Integer count = vote.getVoteCount().intValue()+1;

        query.setString(0,vote.getVoteName());
        query.setInteger(1,count);
        query.setString(2,vote.getId());
        return (query.executeUpdate()>0);
    }
}
