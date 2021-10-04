package com.eomcs.pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.eomcs.pms.table.BoardTable;
import com.eomcs.pms.table.BookingTable;
import com.eomcs.pms.table.BuyerTable;
import com.eomcs.pms.table.CartTable;
import com.eomcs.pms.table.CommentTable;
import com.eomcs.pms.table.MemberTable;
import com.eomcs.pms.table.ProductTable;
import com.eomcs.pms.table.ReviewTable;
import com.eomcs.pms.table.SellerTable;
import com.eomcs.pms.table.StockTable;
import com.eomcs.pms.table.TotalNumberTable;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.RequestProcessor;

public class ServerApp {
  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    @SuppressWarnings("resource")
    ServerSocket serverSocket = new ServerSocket(8889);

    HashMap<String, DataProcessor> dataProcessorMap = new HashMap<String, DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    BuyerTable buyerTable = new BuyerTable();
    SellerTable sellerTable = new SellerTable();
    BoardTable boardTable = new BoardTable();
    ProductTable productTable = new ProductTable();
    dataProcessorMap.put("addNumber.", new TotalNumberTable());
    dataProcessorMap.put("buyer.", buyerTable);
    dataProcessorMap.put("seller.", sellerTable);
    dataProcessorMap.put("board.", new BoardTable());
    dataProcessorMap.put("comment.", new CommentTable(boardTable));
    dataProcessorMap.put("product.", new ProductTable());
    dataProcessorMap.put("member.", new MemberTable(buyerTable, sellerTable));
    dataProcessorMap.put("stock.", new StockTable());
    dataProcessorMap.put("cart.", new CartTable());
    dataProcessorMap.put("booking.", new BookingTable());
    dataProcessorMap.put("review.", new ReviewTable(productTable));


    while(true) {
      Socket socket = serverSocket.accept();

      System.out.println("클라이언트가 접속했음");

      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);

      requestProcessor.start();
    }
    //    System.out.println("서버종료");
    //    serverSocket.close();
  }
}

