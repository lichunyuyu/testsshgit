package com.one.service;

import com.one.dao.IVoteDao;
import com.one.dao.VoteDao;
import com.one.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vtstar on 2017/12/29.
 */

@Service
public class VoteManager implements IVoteManager{

    ////( 接口 IVoteDao)  否则 会报：
    //org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'voteManager': Unsatisfied dependency expressed through field 'voteDao'; nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'voteDao' is expected to be of type 'com.one.dao.VoteDao' but was actually of type 'com.sun.proxy.$Proxy42'

    @Autowired
    private IVoteDao voteDao;

    public void setVoteDao(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    @Override
    public void addVote(Vote vote) {
        voteDao.addVote(vote);
    }

    @Override
    public List<Vote> getAllVote() {
      return  voteDao.getAllVote();
    }

    @Override
    public boolean deleteVote(String id) {
        return voteDao.delVote(id);
    }

    @Override
    public Vote getVote(String id) {
        return voteDao.getVote(id);
    }

    @Override
    public boolean updateVote(Vote vote) {
        return voteDao.updateVote(vote);
    }

    @Override
    public boolean updateVoteCount(Vote vote) {
        return voteDao.updateVoteCount(vote);
    }
}
