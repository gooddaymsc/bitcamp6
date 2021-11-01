package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Comment;

public interface CommentDao {
  void insert(Comment comment) throws Exception;
  List<Comment> findAll(int boardNo) throws Exception;
  void update(Comment comment) throws Exception;
  void delete(Comment comment) throws Exception;
  Comment findByNo(int commentNo) throws Exception;
}
