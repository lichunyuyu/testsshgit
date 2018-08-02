package com.one.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;

/**
 * Created by vtstar on 2017/12/29.
 */

//被投票 模型
@Entity
@Table(name="t_vote")
public class Vote {

    @Id
    @GeneratedValue(generator = "system-uuid")  //使用uuid生成主键的方式
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length=32)
    private String id;

    @Column(length=32)
    private String voteName;
    @Column
    private Integer voteCount;

    @Column
    private String recordDate;

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }


}
