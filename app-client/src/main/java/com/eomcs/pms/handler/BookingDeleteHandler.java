
package com.eomcs.pms.handler;

import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.util.Prompt;

public class BookingDeleteHandler  implements Command {
  BookingDao bookingDao;
  SqlSession sqlSession;
  public BookingDeleteHandler(BookingDao bookingDao, SqlSession sqlSession) {
    this.bookingDao = bookingDao;
    this.sqlSession = sqlSession;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[예약 취소]");

    int no = (Integer) request.getAttribute("bookingNo");
    String input = Prompt.inputString("정말 취소하시겠습니까?(y/N) \n");
    if (input.equalsIgnoreCase("y")) {
      bookingDao.delete(no);
      sqlSession.commit();
      System.out.println("예약을 취소하였습니다.\n");
      return;
    }
    System.out.println("예약 삭제를 취소하였습니다.\n");
    return;
  }
}







