package com.eomcs.pms.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardTag implements Serializable {
  private int tagNumber;
  private String tag;

  public int getTagNumber() {
    return tagNumber;
  }
  public void setTagNumber(int tagNumber) {
    this.tagNumber = tagNumber;
  }
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }


}