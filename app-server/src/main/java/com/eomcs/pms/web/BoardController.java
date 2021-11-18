package com.eomcs.pms.web;

import java.util.Collection;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.LikeMember;

@Controller
public class BoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;

  @GetMapping("/board/list")
  public ModelAndView list() throws Exception {
    Collection<Board> boardList = boardDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "게시글목록");
    mv.addObject("contentUrl", "board/BoardList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/detail")
  public ModelAndView detail(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Collection<LikeMember> likeList = boardDao.findLikeList(no);
    board.setLikeMember((List<LikeMember>) likeList);
    board.setLikes(likeList.size());
    board.setViews(board.getViews()+1);

    boardDao.updateCount(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

}
