package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import static com.eomcs.menu.Menu.ACCESS_PRIVACY;
import static com.eomcs.menu.Menu.ACCESS_SELLER;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.eomcs.pms.handler.BookingAddHandler;
import com.eomcs.pms.handler.BookingDeleteHandler;
import com.eomcs.pms.handler.BookingDetailHandler;
import com.eomcs.pms.handler.BookingListHandler;
import com.eomcs.pms.handler.BookingUpdateHandler;
import com.eomcs.pms.handler.CartAddHandler;
import com.eomcs.pms.handler.CartDeleteHandler;
import com.eomcs.pms.handler.CartDetailHandler;
import com.eomcs.pms.handler.CartListHandler;
import com.eomcs.pms.handler.CartUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.FindIdHandler;
import com.eomcs.pms.handler.FindPasswordHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.PrivacyAddHandler;
import com.eomcs.pms.handler.PrivacyDeleteHandler;
import com.eomcs.pms.handler.PrivacyDetailHandler;
import com.eomcs.pms.handler.PrivacyListHandler;
import com.eomcs.pms.handler.PrivacyUpdateHandler;
import com.eomcs.pms.handler.ProductAddHandler;
import com.eomcs.pms.handler.ProductDeleteHandler;
import com.eomcs.pms.handler.ProductDetailHandler;
import com.eomcs.pms.handler.ProductListHandler;
import com.eomcs.pms.handler.ProductUpdateHandler;
import com.eomcs.pms.handler.SellerPrivacyAddHandler;
import com.eomcs.pms.handler.SellerPrivacyDeleteHandler;
import com.eomcs.pms.handler.SellerPrivacyDetailHandler;
import com.eomcs.pms.handler.SellerPrivacyListHandler;
import com.eomcs.pms.handler.SellerPrivacyUpdateHandler;
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

  HashMap<String, Command> commandMap = new HashMap<>();

  LoginHandler loginHandler = new LoginHandler(managerList);

  FindIdHandler findIdHandler = new FindIdHandler(privacyList, sellerPrivacyList);
  FindPasswordHandler findPasswordHandler = new FindPasswordHandler(privacyList, sellerPrivacyList);

  class MenuItem extends Menu{
    String menuId;

    public MenuItem(String title, String menuId) {
      this(title, ACCESS_LOGOUT | ACCESS_PRIVACY | ACCESS_SELLER | ACCESS_ADMIN , menuId);
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {

      Command command  = commandMap.get(menuId);
      command.execute();
    }
  }

  static Manager loginPrivacy = new Manager();
  public static Manager getLoginUser() {
    return loginPrivacy;
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }


  public App() {

    commandMap.put("/privacy/add",    new PrivacyAddHandler(privacyList, managerList));
    commandMap.put("/privacy/list",   new PrivacyListHandler(privacyList));
    commandMap.put("/privacy/detail", new PrivacyDetailHandler(privacyList));
    commandMap.put("/privacy/update", new PrivacyUpdateHandler(privacyList));
    commandMap.put("/privacy/delete", new PrivacyDeleteHandler(privacyList, managerList));

    commandMap.put("/sellerprivacy/add",    new SellerPrivacyAddHandler(sellerPrivacyList, managerList));
    commandMap.put("/sellerprivacy/list",   new SellerPrivacyListHandler(sellerPrivacyList));
    commandMap.put("/sellerprivacy/detail", new SellerPrivacyDetailHandler(sellerPrivacyList));
    commandMap.put("/sellerprivacy/update", new SellerPrivacyUpdateHandler(sellerPrivacyList));
    commandMap.put("/sellerprivacy/delete", new SellerPrivacyDeleteHandler(sellerPrivacyList, managerList));

    commandMap.put("/board/add",    new BoardAddHandler(boardList));
    commandMap.put("/board/list",   new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/like",   new BoardLikeHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));

    commandMap.put("/product/add",    new ProductAddHandler(productList));
    commandMap.put("/product/list",   new ProductListHandler(productList));
    commandMap.put("/product/detail", new ProductDetailHandler(productList));
    commandMap.put("/product/update", new ProductUpdateHandler(productList));
    commandMap.put("/product/delete", new ProductDeleteHandler(productList));

    commandMap.put("/stock/add"  ,  new StockAddHandler(stockList, new ProductListHandler(productList)));
    commandMap.put("/stock/list",   new StockListHandler(stockList));
    commandMap.put("/stock/detail", new StockDetailHandler(stockList));
    commandMap.put("/stock/update", new StockUpdateHandler(stockList));
    commandMap.put("/stock/delete", new StockDeleteHandler(stockList));

    commandMap.put("/cart/add"  ,  new CartAddHandler(cartList, new StockListHandler(stockList)));
    commandMap.put("/cart/list",   new CartListHandler(cartList));
    commandMap.put("/cart/detail", new CartDetailHandler(cartList));
    commandMap.put("/cart/update", new CartUpdateHandler(cartList));
    commandMap.put("/cart/delete", new CartDeleteHandler(cartList));

    commandMap.put("/booking/add",    new BookingAddHandler(bookingList, new CartListHandler(cartList)));
    commandMap.put("/booking/list",   new BookingListHandler(bookingList));
    commandMap.put("/booking/detail", new BookingDetailHandler(bookingList));
    commandMap.put("/booking/update", new BookingUpdateHandler(bookingList));
    commandMap.put("/booking/delete", new BookingDeleteHandler(bookingList));


  }

  void service() {
    managerList.add(new Manager("관리자","1234", Menu.ACCESS_ADMIN));

    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    //////////////////////////////////////////////////////////
    MenuGroup loginMenu = new MenuGroup("로그인 메뉴");
    mainMenuGroup.add(loginMenu);

    MenuGroup joinMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);
    loginMenu.add(joinMenu);

    joinMenu.add(new MenuItem("일반회원", "/privacy/add"));

    joinMenu.add(new MenuItem("판매자", "/sellerprivacy/add"));


    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기", ACCESS_LOGOUT);
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


    loginMenu.add(new Menu("로그인실행", ACCESS_LOGOUT) {
      @Override
      public void execute() {
        Manager prv = loginHandler.InputId(); 
        if (prv==null) {
          System.out.println("다시 로그인 해주세요.");
        } else {
          loginPrivacy = prv;
        }
      }});

    loginMenu.add(new Menu("현재로그인정보", ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER ) {
      @Override
      public void execute() {
        System.out.printf("\n현재 아이디 : %s",loginPrivacy.getId());
        System.out.printf("\n현재 비밀번호 : %s",loginPrivacy.getPassword());
        System.out.printf("\n현재 권한 : %s\n", level(loginPrivacy.getAuthority()));
      }});


    loginMenu.add(new Menu("로그아웃", ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER) {
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

    boardMenu.add(new MenuItem("등록", ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER, "/board/add"));
    boardMenu.add(new MenuItem("목록", "/board/list"));
    boardMenu.add(new MenuItem("상세보기", "/board/detail"));
    boardMenu.add(new MenuItem("좋아요", ACCESS_PRIVACY | ACCESS_SELLER, "/board/like"));
    boardMenu.add(new MenuItem("변경", ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER,"/board/update"));
    boardMenu.add(new MenuItem("삭제",ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER, "/board/delete"));

    ///////////////////////////////////////////

    MenuGroup cartMenu = new MenuGroup("장바구니", ACCESS_PRIVACY );
    mainMenuGroup.add(cartMenu);

    cartMenu.add(new MenuItem("등록", "/cart/add"));
    cartMenu.add(new MenuItem("목록", "/cart/list"));
    cartMenu.add(new MenuItem("상세보기", "/cart/detail"));
    cartMenu.add(new MenuItem("변경", "/cart/update"));
    cartMenu.add(new MenuItem("삭제", "/cart/delete"));

    ///////////////////////////////////////////


    MenuGroup bookingMenu = new MenuGroup("예약", ACCESS_PRIVACY | ACCESS_SELLER);
    mainMenuGroup.add(bookingMenu);

    bookingMenu.add(new MenuItem("등록", ACCESS_PRIVACY, "/booking/add"));
    bookingMenu.add(new MenuItem("목록",  ACCESS_PRIVACY | ACCESS_SELLER, "/booking/list"));
    bookingMenu.add(new MenuItem("상세보기",  ACCESS_PRIVACY | ACCESS_SELLER,"/booking/detail"));
    bookingMenu.add(new MenuItem("변경", "/booking/update"));
    bookingMenu.add(new MenuItem("삭제", ACCESS_PRIVACY, "/booking/delete"));

    ///////////////////////////////////////////

    MenuGroup productMenu = new MenuGroup("상품");
    mainMenuGroup.add(productMenu);

    productMenu.add(new MenuItem("등록", ACCESS_ADMIN | ACCESS_SELLER, "/product/add"));
    productMenu.add(new MenuItem("목록", "/product/list"));
    productMenu.add(new MenuItem("상세보기", "/product/detail"));
    productMenu.add(new MenuItem("변경",  ACCESS_ADMIN | ACCESS_SELLER, "/product/update"));
    productMenu.add(new MenuItem("삭제", ACCESS_ADMIN | ACCESS_SELLER, "/product/delete"));

    ///////////////////////////////////////////

    MenuGroup stockMenu = new MenuGroup("재고", ACCESS_PRIVACY | ACCESS_ADMIN | ACCESS_SELLER);
    mainMenuGroup.add(stockMenu);

    stockMenu.add(new MenuItem("등록", ACCESS_SELLER, "/stock/add"));
    stockMenu.add(new MenuItem("목록", "/stock/list"));
    stockMenu.add(new MenuItem("상세보기", "/stock/detail"));
    stockMenu.add(new MenuItem("변경",  ACCESS_SELLER, "/stock/update"));
    stockMenu.add(new MenuItem("삭제", ACCESS_SELLER, "/stock/delete"));

    ///////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("프로필", ACCESS_PRIVACY | ACCESS_SELLER);
    mainMenuGroup.add(personMenu);

    personMenu.add(new MenuItem("상세보기", "/privacy/detail"));
    personMenu.add(new MenuItem("변경", "/privacy/update"));
    personMenu.add(new MenuItem("삭제", "/privacy/delete"));

    //    personMenu.add(new Menu("상세보기") {
    //      @Override
    //      public void execute() {
    //        privacyDetailHandler.execute(); 
    //      }});
    //    personMenu.add(new Menu("변경") {
    //      @Override
    //      public void execute() {
    //        privacyUpdateHandler.execute(); 
    //      }});
    //    personMenu.add(new Menu("삭제") {
    //      @Override
    //      public void execute() {
    //        privacyDeleteHandler.execute(); 
    //      }});

    ///////////////////////////////////////////

    MenuGroup managerMenu = new MenuGroup("관리자모드", ACCESS_ADMIN );
    mainMenuGroup.add(managerMenu);

    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리"); //1
    managerMenu.add(managerMemberMenu1);

    managerMemberMenu1.add(new MenuItem("목록", "/privacy/list"));
    managerMemberMenu1.add(new MenuItem("상세보기", "/privacy/detail"));
    managerMemberMenu1.add(new MenuItem("변경", "/privacy/update"));
    managerMemberMenu1.add(new MenuItem("삭제", "/privacy/delete"));

    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");  //2
    managerMenu.add(managerSellerMenu1);

    managerSellerMenu1.add(new MenuItem("목록", "/sellerprivacy/list"));
    managerSellerMenu1.add(new MenuItem("상세보기", "/sellerprivacy/detail"));
    managerSellerMenu1.add(new MenuItem("변경", "/sellerprivacy/update"));
    managerSellerMenu1.add(new MenuItem("삭제", "/sellerprivacy/delete"));

    return mainMenuGroup;
  }

  private String level(int i) {
    switch (i) {
      case Menu.ACCESS_LOGOUT : return "비회원";
      case Menu.ACCESS_PRIVACY : return "일반회원";
      case Menu.ACCESS_SELLER : return "판매자";
      default : return "관리자";
    }
  }
}