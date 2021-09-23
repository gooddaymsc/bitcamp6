package com.eomcs.pms.listener;

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
import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.MessageList;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.StockList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileListener implements ApplicationContextListener {

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String, Object> params) {

    List<Board> boardList = (List<Board>) params.get("boardList");
    List<Product> productList = (List<Product>) params.get("productList");
    List<StockList> allStockList = (List<StockList>) params.get("allStockList");
    List<BookingList> allBookingList = (List<BookingList>) params.get("allBookingList");
    List<CartList> allCartList = (List<CartList>) params.get("allCartList");
    List<Member> memberList = (List<Member>) params.get("memberList");
    List<Buyer> buyerList = (List<Buyer>) params.get("buyerList");
    List<Seller> sellerList = (List<Seller>) params.get("sellerList");
    List<MessageList> allMessageList = (List<MessageList>) params.get("allMessageList");
    //     List<Integer> totalNumberList = (List<Integer>) params.get("totalNumberList");

    // List Load.
    loadObjects("board.json", boardList, Board.class);
    // seller와 buyer 정보를 따로 불러옴
    loadObjects("buyer.json", buyerList, Buyer.class);
    loadObjects("seller.json", sellerList, Seller.class);
    // seller 와 buyer 정보를 memberList 에 저장
    mergeMember(memberList, buyerList, sellerList);

    loadObjects("product.json", productList, Product.class);
    loadObjects("stock.json", allStockList, StockList.class);
    loadObjects("cart.json", allCartList, CartList.class);
    loadObjects("booking.json", allBookingList, BookingList.class);
    //    loadObjects("totalNumber.json", totalNumberList, Integer.class);

  }
  private <E> void loadObjects(String filepath, List<E> list, Class<E> domainType) {

    try (BufferedReader in = new BufferedReader(
        new FileReader(filepath, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);
      System.out.printf("%s 데이터 로딩 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filepath);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void contextDestroyed(Map<String, Object> params) {
    List<Board> boardList = (List<Board>) params.get("boardList");
    List<Product> productList = (List<Product>) params.get("productList");
    List<StockList> allStockList = (List<StockList>) params.get("allStockList");
    List<BookingList> allBookingList = (List<BookingList>) params.get("allBookingList");
    List<CartList> allCartList = (List<CartList>) params.get("allCartList");
    List<Member> memberList = (List<Member>) params.get("memberList");
    List<Buyer> buyerList = (List<Buyer>) params.get("buyerList");
    List<Seller> sellerList = (List<Seller>) params.get("sellerList");
    List<MessageList> allMessageList = (List<MessageList>) params.get("allMessageList");
    //   List<Integer> totalNumberList = (List<Integer>) params.get("totalNumberList");

    //List 저장
    saveObjects("board.json", boardList);
    // memberList 를 buyerList, sellerList로 나눈다.
    seperateMember(memberList, buyerList, sellerList);
    // buyerList, sellerList 따로 저장한다.
    saveObjects("buyer.json", buyerList);
    saveObjects("seller.json", sellerList);

    saveObjects("product.json", productList);
    saveObjects("stock.json", allStockList);
    saveObjects("cart.json", allCartList);
    saveObjects("booking.json", allBookingList);
    //    saveObjects("totalNumber.json", totalNumberList);
  }

  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filepath, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filepath);
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

  private void seperateMember(List<Member> memberList, List<Buyer> buyerList, List<Seller> sellerList) {
    buyerList = new ArrayList<>();
    sellerList = new ArrayList<>();
    for (Member member : memberList) {
      if (member instanceof Buyer) {
        buyerList.add((Buyer)member);   
      } else if (member instanceof Seller) {
        sellerList.add((Seller)member);
      }
    }
  }

}
