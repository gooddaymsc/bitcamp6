package com.eomcs.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import com.eomcs.pms.App;

@SuppressWarnings("serial")
public class Message implements Serializable {
  private int messageNumber;
  private String allContent = "";
  //  private String content;
  private Date registrationDate;
  private String theOtherId; // 대화상대.

  public int getMessageNumber() {
    return messageNumber;
  }
  public void setMessageNumber(int messageNumber) {
    this.messageNumber = messageNumber;
  }
  public String getAllContent() {
    return allContent;
  }
  public void setAllContent(String allContent) {
    if (this.allContent.length()==0) {
      this.allContent = App.getLoginUser().getId() +" : "+ allContent ;
    } else {
      this.allContent += "/"+App.getLoginUser().getId() +" : " + allContent;
      //    this.allContent = allContent;
    }
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }
  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
  public String getTheOtherId() {
    return theOtherId;
  }
  public void setTheOtherId(String theOtherId) {
    this.theOtherId = theOtherId;
  }


}