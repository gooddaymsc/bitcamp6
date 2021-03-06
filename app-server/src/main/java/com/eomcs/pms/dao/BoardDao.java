package com.eomcs.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.LikeMember;

public interface BoardDao {
  void insert(Board board) throws Exception;
  void insertTag(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  List<Board> findMine(int no) throws Exception;
  Board findByNo(int no) throws Exception;
  Board findByBoard(String name) throws Exception;
  void update(Board board) throws Exception;
  void update2(Board board) throws Exception;
  void delete(Board board) throws Exception;
  void deleteTag(int no) throws Exception;
  void updateCount(int no) throws Exception;
  List<LikeMember> findLikeList(int no) throws Exception;
  LikeMember findLike(@Param("memberNo")int memberNo, @Param("boardNo")int boardNo) throws Exception;
  void likeInsert(@Param("memberNo")int memberNo, @Param("boardNo")int boardNo) throws Exception;
  void likeDelete(@Param("memberNo")int memberNo, @Param("boardNo")int boardNo) throws Exception;
  void insertBoardTag(@Param("boardNo")int boardNo, @Param("tagNo")int tagNo) throws Exception;
  List<Board> search(@Param("input")String input) throws Exception;
}
