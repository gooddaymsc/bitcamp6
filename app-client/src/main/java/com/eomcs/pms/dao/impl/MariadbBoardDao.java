package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;

public class MariadbBoardDao implements BoardDao{

  Connection con;

  public MariadbBoardDao(Connection con) {
    this.con =  con;
  }

  @Override
  public void insert(Board board) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert"
            + " into board(member_no,title,content)"
            + " values(?,?,?)")){

      stmt.setInt(1, board.getWriter().getNumber());
      stmt.setString(2, board.getTitle());
      stmt.setString(3, board.getContent());


      if(stmt.executeUpdate() == 0) {
        throw new Exception("게시글 저장 실패");
      }
    }
  }


  @Override
  public List<Board> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " b.board_no,"
            + " b.member_no,"
            + " b.title,"
            + " b.content,"
            + " b.registeredDate,"
            + " b.views, "
            + " m.name, "
            + " m.id "
            + " from board b inner join member m"
            + " on b.member_no=m.member_no"
            + " order by b.board_no desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setBoardNumber(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));

        Member member = new Member();
        member.setNumber(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setId(rs.getString("id"));
        board.setWriter(member);

        board.setViews(rs.getInt("views"));
        board.setRegistrationDate(rs.getDate("registeredDate"));

        list.add(board);
      }

      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " b.board_no,b.title,b.content,b.registeredDate,b.views,"
            + " m.member_no, m.id, m.name,m.email"
            + " from board b inner join member m on b.member_no=m.member_no"
            + " where board_no=" + no);
        ResultSet rs = stmt.executeQuery()) {

      if (rs.next()) {
        Board board = new Board();
        board.setBoardNumber(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setRegistrationDate(rs.getDate("registeredDate"));
        board.setViews(rs.getInt("views"));

        Member member = new Member();
        member.setNumber(rs.getInt("member_no"));
        member.setId(rs.getString("id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));

        board.setWriter(member);

        // 조회수 증가하기
        try (PreparedStatement stmt2 = con.prepareStatement(
            "update board set views=views + 1"
                + " where board_no=" + no)) {
          stmt2.executeUpdate();
        }

        return board;
      }

      return null;
    }
  }

  @Override
  public Board findByBoard(String keyword) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("keyword", keyword);
    //
    //    requestAgent.request("board.selectOne", params);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      return null;
    //    }
    //    return requestAgent.getObject(Board.class);
    return null;
  }

  @Override
  public void update(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update board set"
            + " title=?,content=?"
            + " where board_no=?")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getBoardNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception { 
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from board where board_no="+no)) {

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 삭제 실패!");
      }
    }
  }

  // 댓글 등록
  @Override
  public void insert(Comment comment) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert"
            + " into comment(board_no,member_no,content)"
            + " values(?,?,?)")){

      stmt.setInt(1, comment.getBoardNumber());
      stmt.setInt(2, comment.getWriter().getNumber());
      stmt.setString(3, comment.getContent());


      if(stmt.executeUpdate() == 0) {
        throw new Exception("댓글 저장 실패");
      }
    }
  }

  // 댓글 조회
  @Override
  public List<Comment> findAll(int boardNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " c.comment_no, c.content, c.registeredDate,"
            + " m.id, m.member_no"
            + " from comment c join member m on c.member_no=m.member_no"
            + " where board_no="+boardNo)) {

      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Comment> list = new ArrayList<>();

        while (rs.next()) {
          Comment comment = new Comment();
          comment.setBoardNumber(boardNo);
          comment.setCommentNumber(rs.getInt("comment_no"));
          comment.setContent(rs.getString("content"));
          comment.setRegistrationDate(rs.getTimestamp("registeredDate"));

          Member member = new Member();
          member.setId(rs.getString("id"));
          member.setNumber(rs.getInt("member_no"));
          comment.setWriter(member);
          list.add(comment);
        }

        return list;
      }
    }
  }

  // 댓글 변경
  @Override
  public void update(Comment comment) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update comment set"
            + " content=?"
            + " where comment_no=?")) {

      stmt.setString(1, comment.getContent());
      stmt.setInt(2, comment.getCommentNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("게시글 데이터 변경 실패!");
      }
    }
  }

  // 댓글 삭제
  @Override
  public void delete(Comment comment) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from comment where comment_no="+comment.getCommentNumber())) {

      if (stmt.executeUpdate() == 0) {
        throw new Exception("댓글 데이터 삭제 실패!");
      }
    }
  }

  // 댓글 선택
  @Override
  public Comment findCommentByNo(int commentNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " c.board_no, c.comment_no, c.content, c.registeredDate,"
            + " m.member_no, m.id, m.name,m.email"
            + " from comment c inner join member m on c.member_no=m.member_no"
            + " where comment_no=" + commentNo);
        ResultSet rs = stmt.executeQuery()) {

      if (rs.next()) {
        Comment comment = new Comment();
        comment.setBoardNumber(rs.getInt("board_no"));
        comment.setCommentNumber(rs.getInt("comment_no"));
        comment.setContent(rs.getString("content"));
        comment.setRegistrationDate(rs.getTimestamp("registeredDate"));

        Member member = new Member();
        member.setId(rs.getString("id"));
        member.setNumber(rs.getInt("member_no"));
        comment.setWriter(member);

        return comment;
      }

      return null;
    }
  }

  @Override
  public void like(Board board) throws Exception {
    //
    //    requestAgent.request("board.comment.like", board);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("좋아요 데이터 변경 실패");
    //    }
  }
}
