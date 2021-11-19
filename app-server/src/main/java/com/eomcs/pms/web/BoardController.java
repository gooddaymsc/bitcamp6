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
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BoardTag;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.LikeMember;
import com.eomcs.pms.domain.Member;

@Controller
public class BoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BoardDao boardDao;
  @Autowired CommentDao commentDao;

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

  @GetMapping("/board/find")
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

  @GetMapping("/board/like")
  public ModelAndView like(int no, HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    int nowLoginNo = member.getNumber();

    if (boardDao.findLike(nowLoginNo, no)!=null) {
      System.out.println("좋아요를 취소합니다.\n");
      boardDao.likeDelete(nowLoginNo, no);

    } else {
      System.out.println("좋아요를 눌렀습니다.\n");
      boardDao.likeInsert(nowLoginNo, no);
    }
    sqlSessionFactory.openSession().commit();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:show?no="+no);
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
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/show")
  public ModelAndView show(int no) throws Exception {
    Board board = boardDao.findByNo(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    Collection<LikeMember> likeList = boardDao.findLikeList(no);
    board.setLikeMember((List<LikeMember>) likeList);
    board.setLikes(likeList.size());
    board.setViews(board.getViews()+1);
    Collection<Comment> commentList = commentDao.findAll(no);

    boardDao.updateCount(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("board", board);
    mv.addObject("commentList", commentList);
    mv.addObject("pageTitle", "게시글");
    mv.addObject("contentUrl", "board/BoardShow.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/board/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새 글");
    mv.addObject("contentUrl", "board/BoardForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/board/add")
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
  @PostMapping("/board/update")
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
  @GetMapping("/board/delete")
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
