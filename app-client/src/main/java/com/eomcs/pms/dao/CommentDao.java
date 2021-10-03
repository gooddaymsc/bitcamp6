package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
public interface CommentDao {
  void insert(Comment comment) throws Exception;
  Board findBoardByNo(int no) throws Exception;
  List<Comment> findAll(int boardNo) throws Exception;
  //  void update(int no) throws Exception;
  //  void delete(String id) throws Exception;  
}
