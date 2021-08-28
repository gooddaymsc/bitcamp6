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
import com.eomcs.pms.handler.BookingHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.PrivacyHandler;
import com.eomcs.pms.handler.ProductHandler;
import com.eomcs.pms.handler.SellerPrivacyHandler;
import com.eomcs.pms.handler.StockHandler;
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
  List<Product> ProductList = new ArrayList<>();
  List<Stock> stockList = new ArrayList<>();
  // 관리자
  //List<Member> memberList = new LinkedList<>();
  //고유 id 생성 > 해당 아이디가 이미 있으면 다른 아이디 입력해라.
  List<String> uniqueIdList = new ArrayList<>();
  //  List<Manager> managerList = new ArrayList<>();

  Manager loginPrivacy = new Manager();


  PrivacyHandler privacyHandler = new PrivacyHandler(privacyList, uniqueIdList);
  SellerPrivacyHandler sellerPrivacyHandler = new SellerPrivacyHandler(sellerPrivacyList, uniqueIdList);
  BookingHandler bookingHandler = new BookingHandler(bookingList);
  CartHandler cartHandler = new CartHandler(cartList);
  ProductHandler productHandler = new ProductHandler(ProductList);
  StockHandler stockHandler = new StockHandler(stockList);

  //MemberHandler memberHandler = new MemberHandler(memberList);
  LoginHandler loginHandler = new LoginHandler(privacyList, sellerPrivacyList);



  public static void main(String[] args) {

    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    //메인/1로그인
    MenuGroup loginMenu = new MenuGroup("로그인 하러가기");
    mainMenuGroup.add(loginMenu);
    //메인/1로그인하러가기/1일반회원
    MenuGroup loginMemberMenu = new MenuGroup("일반회원");
    loginMenu.add(loginMemberMenu);
    loginMemberMenu.add(new Menu("로그인") {
      @Override
      public void execute() {
        Privacy prv = loginHandler.memberInputId(); 
        if (prv==null) {
          System.out.println("다시 로그인 해주세요.");
        } else {
          loginPrivacy = prv;

        }
      }});


    //메인/1로그인하러가기/2판매자
    MenuGroup loginSellerMenu = new MenuGroup("판매자");
    loginMenu.add(loginSellerMenu);
    loginSellerMenu.add(new Menu("로그인") {
      @Override
      public void execute() {
        Privacy prv = loginHandler.sellerInputId(); 
        if (prv==null) {
          System.out.println("다시 로그인 해주세요.");
        } else {
          loginPrivacy = prv;
        }
      }});

    MenuGroup loginManagerMenu = new MenuGroup("관리자");
    loginMenu.add(loginManagerMenu);
    loginManagerMenu.add(new Menu("관리자 모드 시작") {
      @Override
      public void execute() {
        Manager man = loginHandler.managerInputId();
        loginPrivacy = man;  
      }});

    //메인/2회원가입/
    MenuGroup joinMenu = new MenuGroup("회원가입");
    mainMenuGroup.add(joinMenu);
    //메인/2회원가입/1일반회원
    MenuGroup memberMenu = new MenuGroup("일반회원");

    joinMenu.add(memberMenu);
    memberMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        privacyHandler.add(1, loginPrivacy.getAuthority()); 
      }});

    memberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.detail(loginPrivacy.getAuthority()); 
      }});
    //메인/2회원가입/2판매자
    MenuGroup member2Menu = new MenuGroup("판매자");
    joinMenu.add(member2Menu);
    member2Menu.add(new Menu("입력") {
      @Override
      public void execute() {
        sellerPrivacyHandler.add(2, loginPrivacy.getAuthority()); 
      }});

    member2Menu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        sellerPrivacyHandler.detail(loginPrivacy.getAuthority()); 
      }});

    //메인/3아이디찾기 ----- 미구현
    MenuGroup findIdMenu = new MenuGroup("아이디찾기");
    mainMenuGroup.add(findIdMenu);
    //메인/4비번찾기 ----- 미구현
    MenuGroup findPasswordMenu = new MenuGroup("비번찾기");
    mainMenuGroup.add(findPasswordMenu);
    //메인/5비회원으로 둘러보기
    MenuGroup nonUserMenu = new MenuGroup("비회원으로 둘러보기");
    mainMenuGroup.add(nonUserMenu);

    MenuGroup nowLoginMenu = new MenuGroup("현재로그인정보");
    mainMenuGroup.add(nowLoginMenu);

    nowLoginMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        System.out.printf("\n현재 아이디 : %s",loginPrivacy.getId());
        System.out.printf("\n현재 비밀번호 : %s",loginPrivacy.getPassword());
        System.out.printf("\n현재 권한 : %s", loginPrivacy.getAuthority());
      }});

    MenuGroup logoutMenu = new MenuGroup("로그아웃");
    mainMenuGroup.add(logoutMenu);
    logoutMenu.add(new Menu("실행") {
      @Override
      public void execute() {
        loginPrivacy = new Manager(); 
        System.out.println("로그아웃이 완료되었습니다.");
      }});


    //    MenuGroup cartMenu = new MenuGroup("장바구니");
    //    mainMenuGroup.add(cartMenu);
    //
    //    cartMenu.add(new Menu("등록") {
    //      @Override
    //      public void execute() {
    //        cartHandler.add(); 
    //      }});
    //    cartMenu.add(new Menu("목록") {
    //      @Override
    //      public void execute() {
    //        cartHandler.list(); 
    //      }});
    //    cartMenu.add(new Menu("상세보기") {
    //      @Override
    //      public void execute() {
    //        cartHandler.detail(); 
    //      }});
    //    cartMenu.add(new Menu("변경") {
    //      @Override
    //      public void execute() {
    //        cartHandler.update(); 
    //      }});
    //    cartMenu.add(new Menu("삭제") {
    //      @Override
    //      public void execute() {
    //        cartHandler.delete(); 
    //      }});
    //
    //
    //    MenuGroup boardMenu = new MenuGroup("게시판");
    //    mainMenuGroup.add(boardMenu);
    //
    //    boardMenu.add(new Menu("등록") {
    //      @Override
    //      public void execute() {
    //        boardHandler.add(); 
    //      }});
    //    boardMenu.add(new Menu("목록") {
    //      @Override
    //      public void execute() {
    //        boardHandler.list(); 
    //      }});
    //    boardMenu.add(new Menu("상세보기") {
    //      @Override
    //      public void execute() {
    //        boardHandler.detail(); 
    //      }});
    //    boardMenu.add(new Menu("변경") {
    //      @Override
    //      public void execute() {
    //        boardHandler.update(); 
    //      }});
    //    boardMenu.add(new Menu("삭제") {
    //      @Override
    //      public void execute() {
    //        boardHandler.delete(); 
    //      }});
    //
    //    MenuGroup bookMenu = new MenuGroup("예약");
    //    mainMenuGroup.add(bookMenu);
    //
    //    bookMenu.add(new Menu("등록") {
    //      @Override
    //      public void execute() {
    //        bookingHandler.add(); 
    //      }});
    //    bookMenu.add(new Menu("목록") {
    //      @Override
    //      public void execute() {
    //        bookingHandler.list(); 
    //      }});
    //    bookMenu.add(new Menu("상세보기") {
    //      @Override
    //      public void execute() {
    //        bookingHandler.detail(); 
    //      }});
    //    bookMenu.add(new Menu("변경") {
    //      @Override
    //      public void execute() {
    //        bookingHandler.update(); 
    //      }});
    //    bookMenu.add(new Menu("삭제") {
    //      @Override
    //      public void execute() {
    //        bookingHandler.delete(); 
    //      }});
    //
    //
    MenuGroup alcoholMenu = new MenuGroup("상품정보");
    mainMenuGroup.add(alcoholMenu);

    alcoholMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        productHandler.add(loginPrivacy.getAuthority()); 
      }});
    alcoholMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        productHandler.list(); 
      }});
    alcoholMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        productHandler.detail(); 
      }});
    alcoholMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        productHandler.update(loginPrivacy.getAuthority()); 
      }});
    alcoholMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        productHandler.delete(loginPrivacy.getAuthority()); 
      }});


    MenuGroup stockMenu = new MenuGroup("재고");
    mainMenuGroup.add(stockMenu);

    stockMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        stockHandler.add(loginPrivacy.getAuthority()); 
      }});
    stockMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        stockHandler.list(loginPrivacy.getAuthority()); 
      }});
    stockMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        stockHandler.detail(loginPrivacy.getAuthority()); 
      }});
    stockMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        stockHandler.update(loginPrivacy.getAuthority()); 
      }});
    stockMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        stockHandler.delete(loginPrivacy.getAuthority()); 
      }});

    MenuGroup personMenu = new MenuGroup("회원관리");
    mainMenuGroup.add(personMenu);

    MenuGroup managerMemberMenu = new MenuGroup("일반회원");
    personMenu.add(managerMemberMenu);

    managerMemberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        privacyHandler.list(loginPrivacy.getAuthority());

      }});
    managerMemberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.detail(loginPrivacy.getAuthority()); 
      }});
    managerMemberMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        privacyHandler.update(loginPrivacy.getAuthority()); 
      }});
    managerMemberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        privacyHandler.delete(loginPrivacy.getAuthority()); 
      }});

    MenuGroup managerSellerMenu = new MenuGroup("판매자");
    personMenu.add(managerSellerMenu);

    managerSellerMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        sellerPrivacyHandler.list(loginPrivacy.getAuthority());

      }});
    managerSellerMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        sellerPrivacyHandler.detail(loginPrivacy.getAuthority()); 
      }});
    managerMemberMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        sellerPrivacyHandler.update(loginPrivacy.getAuthority()); 
      }});
    managerMemberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        sellerPrivacyHandler.delete(loginPrivacy.getAuthority()); 
      }});

    return mainMenuGroup;
  }
}
