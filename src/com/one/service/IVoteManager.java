package com.one.service;

import com.one.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vtstar on 2017/12/29.
 */
@Service
public interface IVoteManager {

    public void addVote(Vote vote);
    public List<Vote> getAllVote();
    public boolean deleteVote(String id);
    public Vote getVote(String id);
    public boolean updateVote(Vote vote);

    public boolean updateVoteCount(Vote vote);

}
