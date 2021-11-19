package com.eomcs.pms.web;

import java.util.Collection;
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
  public ModelAndView add(Comment comment, HttpSession session) throws Exception {
    Member member = (Member)session.getAttribute("loginUser");
    comment.setWriter(member);

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
