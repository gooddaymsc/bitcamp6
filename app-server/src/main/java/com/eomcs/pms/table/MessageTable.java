package com.eomcs.pms.table;

import com.eomcs.pms.domain.Message;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;
import com.google.gson.Gson;

public class MessageTable extends JsonDataTable<MessageList> implements DataProcessor {

  public MessageTable() {
    super("allMessageList.json", MessageList.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "message.List.insert" : insertList(request, response); break;
      case "message.insert" : insert(request, response); break;
      case "message.selectList" : selectList(request, response); break;
      case "message.selectAllList" : selectAllList(request, response); break;
      case "message.selectOne" : selectOne(request, response); break;
      case "message.update" : update(request, response); break;
      case "message.delete" : delete(request, response); break;
      case "message.List.delete" : deleteList(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }
  private void insertList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    MessageList messageList = new MessageList();
    messageList.setId(id);
    list.add(messageList);
    response.setStatus(Response.SUCCESS);
  }

  private void deleteList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 회원의 재고목록을 삭제할 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    Message message = new Gson().fromJson(request.getParameter("message"), Message.class);
    //    Message message = request.getObject(Message.class);
    MessageList messageList = findById(id);
    // stock numbering
    message.setMessageNumber(messageList.getMessageListNumber());
    messageList.setMessageListNumber(message.getMessageNumber()+1);
    messageList.getMessage().add(message);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    String id = request.getParameter("id");
    MessageList messageList = findById(id);
    response.setStatus(Response.SUCCESS);
    response.setValue(messageList);
  }

  private void selectAllList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  private void selectOne(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    MessageList messageList = findById(id);
    if (messageList != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(messageList);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 아이디의 메세지방이 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Message message = request.getObject(Message.class);
    MessageList messageList = findById(message.getId());
    MessageList messageList2 = findById(message.getTheOtherId());

    int index = indexOf(message.getMessageNumber(), message.getId());
    int index2 = indexOf(message.getId(), message.getTheOtherId());
    Message message2 = messageList2.getMessage().get(index2);
    message2.setAllContent(message.getAllContent());
    
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 메세지를 찾을 수 없습니다.");
      return;
    }

    messageList.getMessage().set(index, message);
    messageList2.getMessage().set(index2, message2);

    response.setStatus(Response.SUCCESS);
  }


  private void delete(Request request, Response response) throws Exception {
    Message message = request.getObject(Message.class);
    MessageList messageList = findById(message.getId());
    MessageList messageList2 = findById(message.getTheOtherId());

    int index = indexOf(message.getMessageNumber(), message.getId());
    int index2 = indexOf(message.getId(), message.getTheOtherId());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 메세지를 찾을 수 없습니다.");
      return;
    }

    messageList.getMessage().remove(index);
    messageList2.getMessage().remove(index2);

    response.setStatus(Response.SUCCESS);
  }
  private int indexOf(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }
  //
  private int indexOf(int messageNo, String id) {
    MessageList messageList = findById(id);
    for (int i = 0; i < messageList.getMessage().size(); i++) {
      if (messageList.getMessage().get(i).getMessageNumber() == messageNo) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf(String otherId, String id) {
    MessageList messageList = findById(id);
    for (int i = 0; i < messageList.getMessage().size(); i++) {
      if (messageList.getMessage().get(i).getTheOtherId().equals(otherId)) {
        return i;
      }
    }
    return -1;
  }

  private MessageList findById(String id) {
    for (MessageList messageList : list) {
      if (messageList.getId().equals(id)) {
        return messageList;
      }
    }
    return null;
  }

}
