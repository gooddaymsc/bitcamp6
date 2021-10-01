package com.eomcs.pms.table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class JsonDataTable2 {
  protected List<Buyer> buyerList = new ArrayList<>();
  protected List<Seller> sellerList = new ArrayList<>();
  protected List<Member> memberList = new ArrayList<>();
  protected Class<Buyer> buyerelementType;
  protected Class<Seller> sellerelementType;
  protected Class<Member> elementTypeAll;
  protected String buyerfilename;
  protected String sellerfilename;

  public JsonDataTable2(String buyerfilename, String sellerfilename, 
      Class<Buyer> buyerelementType, Class<Seller> sellerelementType) {
    this.buyerfilename = buyerfilename;
    this.sellerfilename = sellerfilename;

    this.buyerelementType = buyerelementType;
    this.sellerelementType = sellerelementType;

    loadObjects(buyerfilename, buyerelementType, buyerList);
    loadObjects(sellerfilename, sellerelementType, sellerList);
    mergeMember(memberList, buyerList, sellerList);

    memberList.add(new Member("admin","0000", Member.ACCESS_ADMIN));
  }

  public void save() {
    seperateBuyer(memberList);
    seperateSeller(memberList);
  }

  @SuppressWarnings("hiding")
  private <E> void loadObjects(String filename, Class<E> elementType, List<E> list) {

    try (BufferedReader in = new BufferedReader(
        new FileReader(filename, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      Type type = TypeToken.getParameterized(Collection.class, elementType).getType();
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);
      System.out.printf("%s 데이터 로딩 완료!\n", filename);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filename);
    }
  }

  private <E> void saveObjects(String filename, List<E> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filename, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filename);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filename);
      e.printStackTrace();
    }
  }

  private void mergeMember(List<Member> memberList, List<Buyer> buyerList, List<Seller> sellerList) {
    for (Buyer buyer : buyerList) {
      memberList.add(buyer);
    }
    for (Seller seller : sellerList) {
      memberList.add(seller);
    }
  }

  private void seperateBuyer(List<Member> listAll) {
    List<Buyer> buyerList1 = new ArrayList<>();
    for (Member member : listAll) {
      if (member instanceof Buyer) {
        buyerList1.add((Buyer)member);   
      } 
    }
    saveObjects(buyerfilename, buyerList1);
  }

  private void seperateSeller(List<Member> listAll) {
    List<Seller> sellerList1 = new ArrayList<>();
    for (Member member : listAll) {
      if (member instanceof Seller) {
        sellerList1.add((Seller)member);   
      } 
    }
    saveObjects(sellerfilename, sellerList1);
  }
}
