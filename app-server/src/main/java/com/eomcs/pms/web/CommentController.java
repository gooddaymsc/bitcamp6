package com.eomcs.pms.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BoardTag;
import com.eomcs.pms.domain.LikeMember;
import com.eomcs.pms.domain.Member;

@Controller
public class CommentController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommentDao commentDao;

  @GetMapping("/board/comment/find")
  public ModelAndView find(HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    Collection<Board> boardList = boardDao.findMine(member.getNumber());

    ModelAndView mv = new ModelAndView();
    mv.addObject("boardList", boardList);
    mv.addObject("pageTitle", "내게시글목록");
    mv.addObject("contentUrl", "board/BoardList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/comment/detail")
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
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/board/comment/add")
  public ModelAndView add(Board board, HttpSession session) throws Exception {

    board.setWriter((Member) session.getAttribute("loginUser"));

    BoardTag boardTag = new BoardTag();
    boardTag.setTag(board.getTag());
    board.setBoardTag(boardTag);

    boardDao.insert(board);
    boardDao.insertTag(board);
    boardDao.insertBoardTag(board.getBoardNumber(), board.getBoardTag().getTagNumber());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:show?no="+board.getBoardNumber());
    return mv;
  }
  @PostMapping("/board/comment/update")
  public ModelAndView update(Board board) throws Exception {
    Board oldBoard = boardDao.findByNo(board.getBoardNumber());
    if (oldBoard == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }
    oldBoard.setTitle(board.getTitle());
    oldBoard.setContent(board.getContent());

    BoardTag boardTag = new BoardTag();
    boardTag.setTag(board.getTag());
    boardTag.setTagNumber(oldBoard.getBoardTag().getTagNumber());
    oldBoard.setBoardTag(boardTag);

    boardDao.update(oldBoard);
    boardDao.update2(oldBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:show?no="+board.getBoardNumber());
    return mv;
  }
  @GetMapping("/board/comment/delete")
  public ModelAndView delete(int no, HttpSession session) throws Exception {

    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    boardDao.deleteTag(no);
    boardDao.likeDelete(((Member)session.getAttribute("loginUser")).getNumber(), board.getBoardNumber()); // nowLoginNo말고 방법 찾아야함.(다른회원도 눌렀을때)
    boardDao.delete(board); 
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}
