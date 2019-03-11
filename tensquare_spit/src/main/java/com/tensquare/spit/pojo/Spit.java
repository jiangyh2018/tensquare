package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author:
 * @create: 2019-03-11 21:45
 **/
public class Spit implements Serializable {
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public Integer getComment() {
        return comment;
    }

    public String getState() {
        return state;
    }

    public String getParentid() {
        return parentid;
    }
}