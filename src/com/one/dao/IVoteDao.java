package com.one.dao;

import com.one.entity.Vote;

import java.util.List;

/**
 * Created by vtstar on 2017/12/29.
 */
public interface IVoteDao {

    public void addVote(Vote vote);

    public List<Vote> getAllVote();

    public boolean delVote(String id);

    public Vote getVote(String id);

    public boolean updateVote(Vote vote);

    public boolean updateVoteCount(Vote vote);
}
