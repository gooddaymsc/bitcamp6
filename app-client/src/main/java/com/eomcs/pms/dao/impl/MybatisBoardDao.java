package com.eomcs.pms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public class MybatisBoardDao implements BoardDao{

  SqlSession sqlSession;

  public MybatisBoardDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Board board) throws Exception {
    sqlSession.insert("BoardMapper.insert", board);
    sqlSession.commit();
  }


  @Override
  public List<Board> findAll() throws Exception {
    return sqlSession.selectList("BoardMapper.findAll");
  }

  @Override
  public Board findByNo(int no) throws Exception {
    return sqlSession.selectOne("BoardMapper.findByNo", no);
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
    sqlSession.update("BoardMapper.update", board);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception { 
    sqlSession.delete("BoardMapper.delete", no);
    sqlSession.commit();
  }

  // 댓글 등록
  @Override
  public void insert(Comment comment) throws Exception {
    //    try(PreparedStatement stmt = con.prepareStatement(
    //        "insert"
    //            + " into comment(board_no,member_no,content)"
    //            + " values(?,?,?)")){
    //
    //      stmt.setInt(1, comment.getBoardNumber());
    //      stmt.setInt(2, comment.getWriter().getNumber());
    //      stmt.setString(3, comment.getContent());
    //
    //
    //      if(stmt.executeUpdate() == 0) {
    //        throw new Exception("댓글 저장 실패");
    //      }
    //    }
  }

  // 댓글 조회
  @Override
  public List<Comment> findAll(int boardNo) throws Exception {
    return null;
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " c.comment_no, c.content, c.registeredDate,"
    //            + " m.id, m.member_no"
    //            + " from comment c join member m on c.member_no=m.member_no"
    //            + " where board_no="+boardNo)) {
    //
    //      try (ResultSet rs = stmt.executeQuery()) {
    //
    //        ArrayList<Comment> list = new ArrayList<>();
    //
    //        while (rs.next()) {
    //          Comment comment = new Comment();
    //          comment.setBoardNumber(boardNo);
    //          comment.setCommentNumber(rs.getInt("comment_no"));
    //          comment.setContent(rs.getString("content"));
    //          comment.setRegistrationDate(rs.getTimestamp("registeredDate"));
    //
    //          Member member = new Member();
    //          member.setId(rs.getString("id"));
    //          member.setNumber(rs.getInt("member_no"));
    //          comment.setWriter(member);
    //          list.add(comment);
    //        }
    //
    //        return list;
    //      }
    //    }
  }

  // 댓글 변경
  @Override
  public void update(Comment comment) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "update comment set"
    //            + " content=?"
    //            + " where comment_no=?")) {
    //
    //      stmt.setString(1, comment.getContent());
    //      stmt.setInt(2, comment.getCommentNumber());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("게시글 데이터 변경 실패!");
    //      }
    //    }
  }

  // 댓글 삭제
  @Override
  public void delete(Comment comment) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "delete from comment where comment_no="+comment.getCommentNumber())) {
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("댓글 데이터 삭제 실패!");
    //      }
    //    }
  }

  // 댓글 선택
  @Override
  public Comment findCommentByNo(int commentNo) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " c.board_no, c.comment_no, c.content, c.registeredDate,"
    //            + " m.member_no, m.id, m.name,m.email"
    //            + " from comment c inner join member m on c.member_no=m.member_no"
    //            + " where comment_no=" + commentNo);
    //        ResultSet rs = stmt.executeQuery()) {
    //
    //      if (rs.next()) {
    //        Comment comment = new Comment();
    //        comment.setBoardNumber(rs.getInt("board_no"));
    //        comment.setCommentNumber(rs.getInt("comment_no"));
    //        comment.setContent(rs.getString("content"));
    //        comment.setRegistrationDate(rs.getTimestamp("registeredDate"));
    //
    //        Member member = new Member();
    //        member.setId(rs.getString("id"));
    //        member.setNumber(rs.getInt("member_no"));
    //        comment.setWriter(member);
    //
    //        return comment;
    //      }
    //
    //    }
    return null;
  }

  @Override
  public void like(Board board, boolean check) throws Exception {
    //    if (check) {
    //      try (PreparedStatement stmt = con.prepareStatement(
    //          "insert into board_like(member_no, board_no) values(?,?)")) {
    //
    //        stmt.setInt(1, ClientApp.getLoginUser().getNumber());
    //        stmt.setInt(2, board.getBoardNumber());
    //
    //        if (stmt.executeUpdate() == 0) {
    //          throw new Exception("좋아요 변경 실패!");
    //        }
    //      }
    //    } else {
    //      try (PreparedStatement stmt = con.prepareStatement(
    //          "delete from board_like where member_no="+ClientApp.getLoginUser().getNumber())) {
    //
    //        if (stmt.executeUpdate() == 0) {
    //          throw new Exception("좋아요 변경 실패!");
    //        }
    //      }
    //    }
  }
}
