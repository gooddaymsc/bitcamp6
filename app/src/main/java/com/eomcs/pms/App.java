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
import com.eomcs.pms.handler.FindHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.ManagerBoardHandler;
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
  List<Product> productList = new ArrayList<>();
  List<Stock> stockList = new ArrayList<>();
  // 관리자
  List<Manager> managerList = new ArrayList<>();
  //고유 id 생성 > 해당 아이디가 이미 있으면 다른 아이디 입력해라.
  //List<String> uniqueIdList = new ArrayList<>();
  // 로그인 식별번호
  //  Manager loginPrivacy = new Manager();
  //static Manager loginPrivacy;

  static Manager loginPrivacy = new Manager();
  public static Manager getLoginUser() {
    return loginPrivacy;
  }

  PrivacyHandler privacyHandler = new PrivacyHandler(privacyList, managerList);
  SellerPrivacyHandler sellerPrivacyHandler = new SellerPrivacyHandler(sellerPrivacyList,managerList);
  BoardHandler boardHandler = new BoardHandler(boardList); 
  BookingHandler bookingHandler = new BookingHandler(bookingList);
  CartHandler cartHandler = new CartHandler(cartList);
  ProductHandler productHandler = new ProductHandler(productList);
  StockHandler stockHandler = new StockHandler(stockList, productList);

  MemberHandler memberHandler = new MemberHandler(privacyList, sellerPrivacyList); 
  //관리자가 전체게시판을 다루는..
  ManagerBoardHandler managerBoardHandler = new ManagerBoardHandler(boardList);

  LoginHandler loginHandler = new LoginHandler(managerList);

  FindHandler findHandler = new FindHandler(privacyList, sellerPrivacyList);

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
        sellerPrivacyHandler.sellerAdd(2,loginPrivacy.getAuthority()); 
      }});


    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기");
    loginMenu.add(findMenu);

    findMenu.add(new Menu("아이디찾기") {
      @Override
      public void execute() {
        findHandler.findId();
      }});

    findMenu.add(new Menu("비밀번호찾기") {
      @Override
      public void execute() {
        findHandler.findPassword();
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
        System.out.printf("\n현재 아이디 : %s",getLoginUser().getId());
        System.out.printf("\n현재 비밀번호 : %s",getLoginUser().getPassword());
        System.out.printf("\n현재 권한 : %s\n", level(getLoginUser().getAuthority()));

      }});


    loginMenu.add(new Menu("로그아웃") {
      @Override
      public void execute() {
        if (loginPrivacy.getAuthority() != 0) {
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
        boardHandler.add(); 
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

    ///////////////////////////////////////////

    MenuGroup cartMenu = new MenuGroup("장바구니");
    mainMenuGroup.add(cartMenu);

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

    ///////////////////////////////////////////

    MenuGroup bookMenu = new MenuGroup("예약");
    mainMenuGroup.add(bookMenu);

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

    ///////////////////////////////////////////

    MenuGroup alcoholMenu = new MenuGroup("상품");
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

    ///////////////////////////////////////////

    MenuGroup stockMenu = new MenuGroup("재고");
    mainMenuGroup.add(stockMenu);

    stockMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        stockHandler.add(); 
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

    ///////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("프로필");
    mainMenuGroup.add(personMenu);
    personMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        privacyHandler.memberDetail(loginPrivacy.getAuthority()); 
      }});
    personMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        privacyHandler.memberUpdate(loginPrivacy.getAuthority()); 
      }});
    personMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        privacyHandler.delete(loginPrivacy.getAuthority()); 
      }});

    ///////////////////////////////////////////

    MenuGroup managerMenu = new MenuGroup("관리자모드");
    mainMenuGroup.add(managerMenu);

    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리");
    managerMenu.add(managerMemberMenu1);

    managerMemberMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberHandler.list(loginPrivacy.getAuthority(), 1); 
      }});
    managerMemberMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberHandler.detail(loginPrivacy.getAuthority(), 1); 
      }});
    managerMemberMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberHandler.update(loginPrivacy.getAuthority(), 1); 
      }});
    managerMemberMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(loginPrivacy.getAuthority(), 1); 
      }});

    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");
    managerMenu.add(managerSellerMenu1);

    managerSellerMenu1.add(new Menu("목록") {  
      @Override
      public void execute() {
        memberHandler.list(loginPrivacy.getAuthority(), 2); 
      }});
    managerSellerMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        memberHandler.detail(loginPrivacy.getAuthority(), 2); 
      }});
    managerSellerMenu1.add(new Menu("변경") {
      @Override
      public void execute() {
        memberHandler.update(loginPrivacy.getAuthority(), 2); 
      }});
    managerSellerMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        memberHandler.delete(loginPrivacy.getAuthority(), 2); 
      }});

    MenuGroup boardMenu1 = new MenuGroup("게시판관리");
    managerMenu.add(boardMenu1);

    boardMenu1.add(new Menu("목록") {
      @Override
      public void execute() {
        managerBoardHandler.list(loginPrivacy.getAuthority()); 
      }});
    boardMenu1.add(new Menu("상세보기") {
      @Override
      public void execute() {
        managerBoardHandler.detail(loginPrivacy.getAuthority()); 
      }});

    boardMenu1.add(new Menu("삭제") {
      @Override
      public void execute() {
        managerBoardHandler.delete(loginPrivacy.getAuthority()); 
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