package com.eomcs.pms.table;

import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BookingTable extends JsonDataTable<BookingList> implements DataProcessor {

  public BookingTable() {
    super("allBookingList.json", BookingList.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "booking.List.insert" : insertList(request, response); break;
      case "booking.insert" : insert(request, response); break;
      case "booking.selectList" : selectList(request, response); break;
      case "booking.selectAllList" : selectAllList(request, response); break;
      case "booking.selectOne" : selectOne(request, response); break;
      case "booking.update" : update(request, response); break;
      case "booking.delete" : delete(request, response); break;
      case "booking.List.delete" : deleteList(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insertList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    BookingList bookingList = new BookingList();
    bookingList.setId(id);
    list.add(bookingList);
    response.setStatus(Response.SUCCESS);
  }

  private void deleteList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {
    Booking booking = request.getObject(Booking.class);
    BookingList bookingList = findById(booking.getId());
    // stock numbering
    booking.setBookingNumber(bookingList.getTotalBookingNumber());
    bookingList.setTotalBookingNumber(booking.getBookingNumber()+1);
    bookingList.getBooking().add(booking);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    String id = request.getParameter("id");
    BookingList bookingList = findById(id);
    response.setStatus(Response.SUCCESS);
    response.setValue(bookingList);
  }

  private void selectAllList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) {
    int no = Integer.parseInt(request.getParameter("no"));
    String id = request.getParameter("id");

    Booking booking = findByNoId(no, id);
    if (booking != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(booking);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 예약이 없습니다.");
    }
  }

  private BookingList findById(String id) {
    for (BookingList bookingList : list) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
  }

  private Booking findByNoId(int no, String id) {
    BookingList bookingList = findById(id);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        return booking;
      }
    }
    return null;
  }

  private void update(Request request, Response response) {
    Booking booking = request.getObject(Booking.class);
    BookingList bookingList = findById(booking.getId());
    BookingList bookingList2 = findById(booking.getTheOtherId());

    int index = indexOf(booking.getBookingNumber(), booking.getId());
    int index2 = indexOf(booking.getTheOtherId(), booking.getId());
    Booking booking2 = bookingList2.getBooking().get(index2);
    booking2.setBookingDate(booking.getBookingDate());
    booking2.setBookingHour(booking.getBookingHour());
    booking2.setBookingMinute(booking.getBookingMinute());
    booking2.setConfirm(booking.isConfirm());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 예약을 찾을 수 없습니다.");
      return;
    }

    bookingList.getBooking().set(index, booking);
    bookingList2.getBooking().set(index2, booking);

    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) {
    Booking booking = request.getObject(Booking.class);
    BookingList bookingList = findById(booking.getId());
    BookingList bookingList2 = findById(booking.getTheOtherId());

    int index = indexOf(booking.getBookingNumber(), booking.getId());
    int index2 = indexOf(booking.getTheOtherId(), booking.getId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 예약을 찾을 수 없습니다.");
    }
    bookingList.getBooking().remove(index);
    bookingList2.getBooking().remove(index2);

    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(int bookingNo, String id) {
    BookingList bookingList = findById(id);
    for (int i = 0; i < bookingList.getBooking().size(); i++) {
      if (bookingList.getBooking().get(i).getBookingNumber() == bookingNo) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf(String theOtherId, String id) {
    BookingList bookingList = findById(id);
    for (int i = 0; i < bookingList.getBooking().size(); i++) {
      if (bookingList.getBooking().get(i).getTheOtherId().equals(theOtherId)) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

}
