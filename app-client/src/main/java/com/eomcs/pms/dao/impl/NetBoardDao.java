package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;

public class NetBoardDao implements BoardDao{

  RequestAgent requestAgent;

  public NetBoardDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public void insert(Board board) throws Exception {
    requestAgent.request("board.insert", board);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("게시글 데이터 저장 실패");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    requestAgent.request("board.selectList", null);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public Board findByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Board.class);
  }

  @Override
  public Board findByBoard(String keyword) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", keyword);

    requestAgent.request("board.selectOne", params);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Board.class);
  }

  @Override
  public void update(Board board) throws Exception {
    requestAgent.request("board.update", board);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 변경 실패");
    }
  }

  @Override
  public void delete(Board board) throws Exception {    
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("name", product.getProductName());

    requestAgent.request("board.delete", board);

    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 삭제 실패");
    }
  }
}