package com.eomcs.pms.web;

import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;

@Controller
public class CommentController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CommentDao commentDao;

  @GetMapping("/board/comment/find")
  public ModelAndView find(HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    Collection<Comment> commentList = commentDao.findMine(member.getNumber());

    ModelAndView mv = new ModelAndView();
    mv.addObject("commentList", commentList);
    mv.addObject("pageTitle", "내댓글목록");
    mv.addObject("contentUrl", "board/comment/CommentList.jsp");
    mv.setViewName("template2");
    return mv;
  }
  @GetMapping("/board/comment/detail")
  public ModelAndView detail(int no) throws Exception {
    Comment comment = commentDao.findByNo(no);
    if (comment == null) {
      throw new Exception("해당 번호의 게시글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("comment", comment);
    mv.addObject("pageTitle", "댓글");
    mv.addObject("contentUrl", "board/comment/CommentDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }
  @PostMapping("/board/comment/add")
  public ModelAndView add(Comment comment, HttpServletRequest request,HttpSession session, HttpServletResponse response) throws Exception {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    comment.setWriter(member);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginForm'</script>");
      out.flush();
    }

    commentDao.insert(comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+comment.getBoardNumber());
    return mv;
  }

  @PostMapping("/board/comment/update")
  public ModelAndView update(Comment comment) throws Exception {
    Comment oldComment = commentDao.findByNo(comment.getCommentNumber());
    if (oldComment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }
    oldComment.setContent(comment.getContent());

    commentDao.update(comment);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+comment.getBoardNumber());
    return mv;
  }

  @GetMapping("/board/comment/delete")
  public ModelAndView delete(int no, HttpSession session) throws Exception {

    Comment comment = commentDao.findByNo(no);
    if (comment == null) {
      throw new Exception("해당 번호의 댓글이 없습니다.");
    }

    commentDao.delete(comment); 
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+comment.getBoardNumber());
    return mv;
  }
}
