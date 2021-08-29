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
import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.BookingHandler;
import com.eomcs.pms.handler.CartHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.MemberHandler;
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
  // 로그인 식별번호
  Manager loginPrivacy = new SellerPrivacy();
  PrivacyHandler privacyHandler = new PrivacyHandler(privacyList,uniqueIdList);
  SellerPrivacyHandler sellerPrivacyHandler = new SellerPrivacyHandler(sellerPrivacyList,uniqueIdList);
  BoardHandler boardHandler = new BoardHandler(boardList); 
  BookingHandler bookingHandler = new BookingHandler(bookingList);
  CartHandler cartHandler = new CartHandler(cartList);
  ProductHandler productHandler = new ProductHandler(ProductList);
  StockHandler stockHandler = new StockHandler(stockList, ProductList);

  MemberHandler memberHandler = new MemberHandler(privacyList, sellerPrivacyList); 
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
    MenuGroup loginMenu = new MenuGroup("로그인 메뉴");
    mainMenuGroup.add(loginMenu);

    //메인/1로그인/1비회원으로 둘러보기
    MenuGroup nonUserMenu = new MenuGroup("비회원으로 둘러보기");
    loginMenu.add(nonUserMenu);

    //메인/1로그인/2회원가입
    MenuGroup joinMenu = new MenuGroup("회원가입");
    loginMenu.add(joinMenu);

    //메인/1로그인/2회원가입/1일반회원
    MenuGroup memberMenu = new MenuGroup("일반회원");
    joinMenu.add(memberMenu);
    memberMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        privacyHandler.memberAdd(1); 
      }});

    memberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.memberDetail(); 
      }});

    //메인/1로그인/2회원가입/2판매자
    MenuGroup sellerMenu = new MenuGroup("판매자");
    joinMenu.add(sellerMenu);
    sellerMenu.add(new Menu("입력") {
      @Override
      public void execute() {
        sellerPrivacyHandler.sellerAdd(2); 
      }});

    sellerMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        sellerPrivacyHandler.sellerDetail(); 
      }});

    //메인/1로그인/3아이디비번찾기
    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기");
    loginMenu.add(findMenu);

    //메인/1로그인/3아이디비번찾기/1아이디찾기
    MenuGroup findIdMenu = new MenuGroup("아이디찾기");
    findMenu.add(findIdMenu);
    //메인/1로그인/3아이디비번찾기/2비번찾기
    MenuGroup findPasswordMenu = new MenuGroup("비번찾기");
    findMenu.add(findPasswordMenu);

    //메인/1로그인/4로그인실행
    MenuGroup goMenu = new MenuGroup("로그인실행");
    loginMenu.add(goMenu);

    //메인/1로그인/4로그인실행/1일반회원
    MenuGroup loginMemberMenu = new MenuGroup("일반회원");
    goMenu.add(loginMemberMenu);
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

    //메인/1로그인/4로그인실행/2판매자
    MenuGroup loginSellerMenu = new MenuGroup("판매자");
    goMenu.add(loginSellerMenu);
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

    //메인/1로그인/4로그인실행/3관리자
    MenuGroup loginManagerMenu = new MenuGroup("관리자");
    goMenu.add(loginManagerMenu);
    loginManagerMenu.add(new Menu("관리자 모드 시작") {
      @Override
      public void execute() {
        Manager man = loginHandler.managerInputId();
        loginPrivacy = man;  
      }});

    //메인/1로그인/4로그인실행/4현재로그인정보
    MenuGroup nowLoginMenu = new MenuGroup("현재로그인정보");
    goMenu.add(nowLoginMenu);

    nowLoginMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        System.out.printf("\n현재 아이디 : %s",loginPrivacy.getId());
        System.out.printf("\n현재 비밀번호 : %s",loginPrivacy.getPassword());
        System.out.printf("\n현재 권한 : %s", loginPrivacy.getAuthority());
      }});

    //메인/1로그인/5로그아웃
    MenuGroup logoutMenu = new MenuGroup("로그아웃");
    loginMenu.add(logoutMenu);
    logoutMenu.add(new Menu("실행") {
      @Override
      public void execute() {
        loginPrivacy = new Manager(); 
        System.out.println("로그아웃이 완료되었습니다.");
      }});

    //메인/2회원
    MenuGroup member = new MenuGroup("회원");
    mainMenuGroup.add(member);   

    //메인/2회원/1개인정보관리
    MenuGroup personMenu = new MenuGroup("개인정보관리");
    member.add(personMenu);

    //메인/2회원/1개인정보관리/1일반회원
    MenuGroup managerMemberMenu = new MenuGroup("일반회원"); //1
    personMenu.add(managerMemberMenu);

    managerMemberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        privacyHandler.memberList();

      }});
    managerMemberMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.memberDetail(); 
      }});
    managerMemberMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        privacyHandler.memberUpdate(); 
      }});
    managerMemberMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        privacyHandler.delete(); 
      }});

    //메인/2회원/1개인정보관리/2판매자
    MenuGroup managerSellerMenu = new MenuGroup("판매자"); 
    personMenu.add(managerSellerMenu);

    managerSellerMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        sellerPrivacyHandler.sellerAdd(2); 
      }});

    managerSellerMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        sellerPrivacyHandler.list();

      }});
    managerSellerMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        sellerPrivacyHandler.sellerDetail(); 
      }});
    managerSellerMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        sellerPrivacyHandler.update(); 
      }});
    managerSellerMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        sellerPrivacyHandler.delete(); 
      }});

    //메인/2회원/2게시판관리
    MenuGroup boardMenu = new MenuGroup("게시판");
    member.add(boardMenu);

    boardMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        boardHandler.add(loginPrivacy.getAuthority()); 
      }});
    boardMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        boardHandler.list(); 
      }});
    boardMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        boardHandler.detail(); 
      }});
    boardMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        boardHandler.update(loginPrivacy.getAuthority()); 
      }});
    boardMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        boardHandler.delete(loginPrivacy.getAuthority()); 
      }});

    //메인/2회원/3장바구니
    MenuGroup cartMenu = new MenuGroup("장바구니");
    member.add(cartMenu);

    cartMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        cartHandler.add(loginPrivacy.getAuthority()); 
      }});
    cartMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        cartHandler.list(loginPrivacy.getAuthority()); 
      }});
    cartMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        cartHandler.detail(loginPrivacy.getAuthority()); 
      }});
    cartMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        cartHandler.update(loginPrivacy.getAuthority()); 
      }});
    cartMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        cartHandler.delete(loginPrivacy.getAuthority()); 
      }});

    //메인/2회원/4예약
    MenuGroup bookMenu = new MenuGroup("예약");
    member.add(bookMenu);

    bookMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        bookingHandler.add(loginPrivacy.getAuthority()); 
      }});
    bookMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        bookingHandler.list(loginPrivacy.getAuthority()); 
      }});
    bookMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        bookingHandler.detail(loginPrivacy.getAuthority()); 
      }});
    bookMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        bookingHandler.update(loginPrivacy.getAuthority()); 
      }});
    bookMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        bookingHandler.delete(loginPrivacy.getAuthority()); 
      }});

    //메인/3판매자
    MenuGroup seller = new MenuGroup("판매자");
    mainMenuGroup.add(seller);

    //메인/3판매자/1상품관리
    MenuGroup alcoholMenu = new MenuGroup("상품관리");
    seller.add(alcoholMenu);

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

    //메인/3판매자/2재고관리
    MenuGroup stockMenu = new MenuGroup("재고관리");
    seller.add(stockMenu);

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

    //메인/4관리자
    MenuGroup manager = new MenuGroup("관리자");
    mainMenuGroup.add(manager);

    //메인/4관리자/일반회원관리
    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리");
    manager.add(managerMemberMenu1);

    managerMemberMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberHandler.list(1); 
      }});
    managerMemberMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberHandler.detail(1); 
      }});
    managerMemberMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberHandler.update(1); 
      }});
    managerMemberMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(1); 
      }});

    //메인/4관리자/판매자관리
    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");
    manager.add(managerSellerMenu1);

    managerSellerMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberHandler.list(2); 
      }});
    managerSellerMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberHandler.detail(2); 
      }});
    managerSellerMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberHandler.update(2); 
      }});
    managerSellerMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(2); 
      }});
    //메인/4관리자/게시판관리
    MenuGroup boardMenu1 = new MenuGroup("게시판관리");
    manager.add(boardMenu1);

    return mainMenuGroup;
  }
}
