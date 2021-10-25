package com.eomcs.pms.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.eomcs.pms.domain.Board;

public interface BoardDao {
  void insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findByNo(int no) throws Exception;
  Board findByBoard(String name) throws Exception;
  void update(Board board) throws Exception;
  void update2(Board board) throws Exception;
  void delete(int no) throws Exception;
  void updateCount(int no) throws Exception;
  void like(@Param("likeId")String likeId, @Param("boardNo")int boardNo, @Param("registrationDate")Date registrationDate) throws Exception;
  void likeDelete(@Param("likeId")String likeId, @Param("boardNo")int boardNo) throws Exception;
}
