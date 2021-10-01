package com.eomcs.pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.eomcs.pms.table.BoardTable;
import com.eomcs.pms.table.BuyerTable;
import com.eomcs.pms.table.ProductTable;
import com.eomcs.pms.table.SellerTable;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.RequestProcessor;

public class ServerApp {
  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    @SuppressWarnings("resource")
    ServerSocket serverSocket = new ServerSocket(8888);


    HashMap<String, DataProcessor> dataProcessorMap = new HashMap<String, DataProcessor>();

    //    dataProcessorMap.put("member.", new BuyerTable());
    dataProcessorMap.put("buyer.", new BuyerTable());
    dataProcessorMap.put("seller.", new SellerTable());
    dataProcessorMap.put("board.", new BoardTable());
    dataProcessorMap.put("product", new ProductTable());

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