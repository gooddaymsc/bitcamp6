package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public interface BoardDao {
  //board
  void insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  Board findByBoard(String name) throws Exception;
  void update(Board board) throws Exception;
  void delete(int no) throws Exception;
  //comment
  void insert(Comment comment) throws Exception;
  List<Comment> findAll(int boardNo) throws Exception;
  void update(Comment comment) throws Exception;
  void delete(Comment comment) throws Exception;
  Comment findCommentByNo(int boardNo, int commentNo) throws Exception;
  void like(Board board) throws Exception;
}
