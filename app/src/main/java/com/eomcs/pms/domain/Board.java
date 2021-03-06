package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Board implements Serializable {
  private int boardNumber;
  private String title;
  private String content;
  private String writer;
  private Date registrationDate;
  private int views;
  private String tag;
  private int likes;
  private List<String> likeMember = new ArrayList<>();
  private int totalCommentNumber = 1;
  private List<Comment> commentList = new ArrayList<>();

  public int getBoardNumber() {
    return boardNumber;
  }
  public void setBoardNumber(int boardNumber) {
    this.boardNumber = boardNumber;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public Date getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public int getViews() {
    return views;
  }
  public void setViews(int views) {
    this.views = views;
  }
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }
  public int getLikes() {
    return likes;
  }
  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getTotalCommentNumber() {
    return totalCommentNumber;
  }
  public void setTotalCommentNumber(int totalCommentNumber) {
    this.totalCommentNumber = totalCommentNumber;
  }
  public List<Comment> getCommentList() {
    return commentList;
  }
  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }
  public List<String> getLikeMember() {
    return likeMember;
  }
  public void setLikeMember(List<String> likeMember) {
    this.likeMember = likeMember;
  }


}