package com.eomcs.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Manager;
import com.eomcs.pms.domain.Privacy;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardLikeHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.BookingHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.pms.handler.FindIdHandler;
import com.eomcs.pms.handler.FindPasswordHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.MemberDeleteHandler;
import com.eomcs.pms.handler.MemberDetailHandler;
import com.eomcs.pms.handler.MemberListHandler;
import com.eomcs.pms.handler.MemberUpdateHandler;
import com.eomcs.pms.handler.PrivacyHandler;
import com.eomcs.pms.handler.ProductAddHandler;
import com.eomcs.pms.handler.ProductDeleteHandler;
import com.eomcs.pms.handler.ProductDetailHandler;
import com.eomcs.pms.handler.ProductListHandler;
import com.eomcs.pms.handler.ProductUpdateHandler;
import com.eomcs.pms.handler.SellerPrivacyHandler;
import com.eomcs.pms.handler.StockAddHandler;
import com.eomcs.pms.handler.StockDeleteHandler;
import com.eomcs.pms.handler.StockDetailHandler;
import com.eomcs.pms.handler.StockListHandler;
import com.eomcs.pms.handler.StockUpdateHandler;
import com.eomcs.util.Prompt;

public class App {
  // 7개 CRUD 
  // 개인회원 정보
  List<Privacy> privacyList = new LinkedList<>();
  List<SellerPrivacy> sellerPrivacyList = new LinkedList<>();
  // 일반회원
  List<Board> boardList = new ArrayList<>();

  List<Booking> bookingList = new LinkedList<>();
  List<Cart> cartList = new ArrayList<>();
  // 판매자
  List<Product> productList = new ArrayList<>();
  List<Stock> stockList = new ArrayList<>();
  // 관리자
  List<Manager> managerList = new ArrayList<>();

  PrivacyHandler privacyHandler = new PrivacyHandler(privacyList, managerList);
  SellerPrivacyHandler sellerPrivacyHandler = new SellerPrivacyHandler(sellerPrivacyList,managerList);

  BoardAddHandler boardAddHandler = new BoardAddHandler(boardList); 
  BoardListHandler boardListHandler = new BoardListHandler(boardList); 
  BoardDetailHandler boardDetailHandler = new BoardDetailHandler(boardList); 
  BoardLikeHandler boardLikeHandler = new BoardLikeHandler(boardList); 
  BoardUpdateHandler boardUpdateHandler = new BoardUpdateHandler(boardList); 
  BoardDeleteHandler boardDeleteHandler = new BoardDeleteHandler(boardList); 

  BookingHandler bookingHandler = new BookingHandler(bookingList);
  CartHandler cartHandler = new CartHandler(cartList);

  ProductAddHandler productAddHandler = new ProductAddHandler(productList);
  ProductListHandler productListHandler = new ProductListHandler(productList);
  ProductDetailHandler productDetailHandler = new ProductDetailHandler(productList);
  ProductUpdateHandler productUpdateHandler = new ProductUpdateHandler(productList);
  ProductDeleteHandler productDeleteHandler = new ProductDeleteHandler(productList);

  StockAddHandler stockAddHandler = new StockAddHandler(stockList, productListHandler);
  StockListHandler stockListHandler = new StockListHandler(stockList);
  StockDetailHandler stockDetailHandler = new StockDetailHandler(stockList);
  StockUpdateHandler stockUpdateHandler = new StockUpdateHandler(stockList);
  StockDeleteHandler stockDeleteHandler = new StockDeleteHandler(stockList);

  MemberListHandler memberListHandler = new MemberListHandler(privacyList, sellerPrivacyList); 
  MemberDetailHandler memberDetailHandler = new MemberDetailHandler(privacyList, sellerPrivacyList); 
  MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(privacyList, sellerPrivacyList); 
  MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(privacyList, sellerPrivacyList); 

  LoginHandler loginHandler = new LoginHandler(managerList);

  FindIdHandler findIdHandler = new FindIdHandler(privacyList, sellerPrivacyList);
  FindPasswordHandler findPasswordHandler = new FindPasswordHandler(privacyList, sellerPrivacyList);


  static Manager loginPrivacy = new Manager();
  public static Manager getLoginUser() {
    return loginPrivacy;
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    managerList.add(new Manager("관리자","1234",3));

    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    //////////////////////////////////////////////////////////
    MenuGroup loginMenu = new MenuGroup("로그인 메뉴");
    mainMenuGroup.add(loginMenu);

    MenuGroup joinMenu = new MenuGroup("회원가입");
    loginMenu.add(joinMenu);

    joinMenu.add(new Menu("일반회원") {
      @Override
      public void execute() {
        privacyHandler.memberAdd(1); 
      }});

    joinMenu.add(new Menu("판매자") {
      @Override
      public void execute() {
        sellerPrivacyHandler.sellerAdd(2); 
      }});


    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기");
    loginMenu.add(findMenu);

    findMenu.add(new Menu("아이디찾기") {
      @Override
      public void execute() {
        findIdHandler.findId();
      }});

    findMenu.add(new Menu("비밀번호찾기") {
      @Override
      public void execute() {
        findPasswordHandler.findPassword();
      }});


    loginMenu.add(new Menu("로그인실행") {
      @Override
      public void execute() {
        Manager prv = loginHandler.InputId(); 
        if (prv==null) {
          System.out.println("다시 로그인 해주세요.");
        } else {
          loginPrivacy = prv;
        }
      }});

    loginMenu.add(new Menu("현재로그인정보") {
      @Override
      public void execute() {
        System.out.printf("\n현재 아이디 : %s",loginPrivacy.getId());
        System.out.printf("\n현재 비밀번호 : %s",loginPrivacy.getPassword());
        System.out.printf("\n현재 권한 : %s\n", level(loginPrivacy.getAuthority()));
      }});


    loginMenu.add(new Menu("로그아웃") {
      @Override
      public void execute() {
        if ( loginPrivacy.getAuthority()!= 0) {
          loginPrivacy = new Manager(); 
          System.out.println("로그아웃이 완료되었습니다."); 
        } else {
          System.out.println("로그인 후 사용해주세요");
        }
      }});

    ///////////////////////////////////////////

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        boardAddHandler.add(); 
      }});
    boardMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        boardListHandler.list(); 
      }});
    boardMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        boardDetailHandler.detail(); 
      }});
    boardMenu.add(new Menu("좋아요") {
      @Override
      public void execute() {
        boardLikeHandler.like(); 
      }});
    boardMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        boardUpdateHandler.update(); 
      }});
    boardMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        boardDeleteHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup cartMenu = new MenuGroup("장바구니");
    mainMenuGroup.add(cartMenu);

    cartMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        cartHandler.add(); 
      }});
    cartMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        cartHandler.list(); 
      }});
    cartMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        cartHandler.detail(); 
      }});
    cartMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        cartHandler.update(); 
      }});
    cartMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        cartHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup bookMenu = new MenuGroup("예약");
    mainMenuGroup.add(bookMenu);

    bookMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        bookingHandler.add(); 
      }});
    bookMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        bookingHandler.list(); 
      }});
    bookMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        bookingHandler.detail(); 
      }});
    bookMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        bookingHandler.update(); 
      }});
    bookMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        bookingHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup alcoholMenu = new MenuGroup("상품");
    mainMenuGroup.add(alcoholMenu);

    alcoholMenu.add(new Menu("등록", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        productAddHandler.add(); 
      }});
    alcoholMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        productListHandler.list(); 
      }});
    alcoholMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        productDetailHandler.detail(); 
      }});
    alcoholMenu.add(new Menu("변경", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        productUpdateHandler.update(); 
      }});
    alcoholMenu.add(new Menu("삭제", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        productDeleteHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup stockMenu = new MenuGroup("재고");
    mainMenuGroup.add(stockMenu);

    stockMenu.add(new Menu("등록", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        stockAddHandler.add(); 
      }});
    stockMenu.add(new Menu("목록", Menu.ENABLE_PRIVACY) {
      @Override
      public void execute() {
        stockListHandler.list(); 
      }});
    stockMenu.add(new Menu("상세보기", Menu.ENABLE_PRIVACY) {
      @Override
      public void execute() {
        stockDetailHandler.detail(); 
      }});
    stockMenu.add(new Menu("변경", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        stockUpdateHandler.update(); 
      }});
    stockMenu.add(new Menu("삭제", Menu.ENABLE_SELLERPIVACY) {
      @Override
      public void execute() {
        stockDeleteHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("프로필");
    mainMenuGroup.add(personMenu);
    personMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.memberDetail(); 
      }});
    personMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        privacyHandler.memberUpdate(); 
      }});
    personMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        privacyHandler.delete(); 
      }});

    ///////////////////////////////////////////

    MenuGroup managerMenu = new MenuGroup("관리자모드");
    mainMenuGroup.add(managerMenu);

    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리");
    managerMenu.add(managerMemberMenu1);

    managerMemberMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberListHandler.list(1); 
      }});
    managerMemberMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberDetailHandler.detail(1); 
      }});
    managerMemberMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberUpdateHandler.update(1); 
      }});
    managerMemberMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberDeleteHandler.delete(1); 
      }});

    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");
    managerMenu.add(managerSellerMenu1);

    managerSellerMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberListHandler.list(2); 
      }});
    managerSellerMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberDetailHandler.detail(2); 
      }});
    managerSellerMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberUpdateHandler.update(2); 
      }});
    managerSellerMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberDeleteHandler.delete(2); 
      }});

    return mainMenuGroup;
  }

  private String level(int i) {
    switch (i) {
      case 0 : return "비회원";
      case 1 : return "일반회원";
      case 2 : return "판매자";
      default : return "관리자";
    }
  }
}