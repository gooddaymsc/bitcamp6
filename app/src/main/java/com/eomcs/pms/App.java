package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_BUYER;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import static com.eomcs.menu.Menu.ACCESS_SELLER;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.StockList;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardFindHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardPrompt;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.BookingAddHandler;
import com.eomcs.pms.handler.BookingDeleteHandler;
import com.eomcs.pms.handler.BookingListHandler;
import com.eomcs.pms.handler.BookingPrompt;
import com.eomcs.pms.handler.BookingUpdateHandler;
import com.eomcs.pms.handler.BuyerAddHandler;
import com.eomcs.pms.handler.BuyerDeleteHandler;
import com.eomcs.pms.handler.BuyerDetailHandler;
import com.eomcs.pms.handler.BuyerListHandler;
import com.eomcs.pms.handler.BuyerUpdateHandler;
import com.eomcs.pms.handler.CartAddHandler;
import com.eomcs.pms.handler.CartDeleteHandler;
import com.eomcs.pms.handler.CartDetailHandler;
import com.eomcs.pms.handler.CartListHandler;
import com.eomcs.pms.handler.CartPrompt;
import com.eomcs.pms.handler.CartUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.CommentFindHandler;
import com.eomcs.pms.handler.FindIdHandler;
import com.eomcs.pms.handler.FindPasswordHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.MemberPrompt;
import com.eomcs.pms.handler.ProductAddHandler;
import com.eomcs.pms.handler.ProductDeleteHandler;
import com.eomcs.pms.handler.ProductDetailHandler;
import com.eomcs.pms.handler.ProductListHandler;
import com.eomcs.pms.handler.ProductPrompt;
import com.eomcs.pms.handler.ProductSearchHandler;
import com.eomcs.pms.handler.ProductUpdateHandler;
import com.eomcs.pms.handler.RankingHandler;
import com.eomcs.pms.handler.SellerAddHandler;
import com.eomcs.pms.handler.SellerDeleteHandler;
import com.eomcs.pms.handler.SellerDetailHandler;
import com.eomcs.pms.handler.SellerListHandler;
import com.eomcs.pms.handler.SellerUpdateHandler;
import com.eomcs.pms.handler.StockAddHandler;
import com.eomcs.pms.handler.StockDeleteHandler;
import com.eomcs.pms.handler.StockDetailHandler;
import com.eomcs.pms.handler.StockListHandler;
import com.eomcs.pms.handler.StockPrompt;
import com.eomcs.pms.handler.StockUpdateHandler;
import com.eomcs.util.Prompt;

public class App {
  List<Board> boardList = new ArrayList<>();
  List<Product> productList = new ArrayList<>();
  List<StockList> allStockList = new ArrayList<>();
  List<BookingList> allBookingList = new ArrayList<>();
  List<CartList> allCartList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  //  int[] totalNumber = {1,1,1}; // totalMemberNumber, totalBoardNumber, totalProductNumber

  HashMap<String, Command> commandMap = new HashMap<>();
  ProductPrompt productPrompt = new ProductPrompt(productList);
  LoginHandler loginHandler = new LoginHandler(memberList);
  MemberPrompt memberPrompt = new MemberPrompt(memberList);
  StockPrompt stockPrompt = new StockPrompt(allStockList, memberPrompt);
  BookingPrompt bookingPrompt = new BookingPrompt(allBookingList);
  CartPrompt cartPrompt = new CartPrompt(allCartList, memberPrompt);
  BoardPrompt boardPrompt = new BoardPrompt(boardList);

  // 권한에 따른 메뉴 구성 위함.
  class MenuItem extends Menu {
    String menuId;
    public MenuItem(String title, String menuId) {
      this(title, ACCESS_LOGOUT | ACCESS_BUYER | ACCESS_SELLER | ACCESS_ADMIN , menuId);
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

  // 현재 로그인한 정보를 저장 (id, pw, auth)
  public static Member loginMember = new Member();
  public static Member getLoginUser() {
    return loginMember;
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  public App() {
    // List Load.
    loadBoards();
    loadManagers();
    loadProducts();
    loadStockLists();
    loadCartLists();
    loadBookingLists();

    commandMap.put("/buyer/add",    new BuyerAddHandler(memberList, cartPrompt, bookingPrompt, memberPrompt));
    commandMap.put("/buyer/list",   new BuyerListHandler(memberList));
    commandMap.put("/buyer/detail", new BuyerDetailHandler(memberList));
    commandMap.put("/buyer/update", new BuyerUpdateHandler(memberList));
    commandMap.put("/buyer/delete", new BuyerDeleteHandler(memberList, memberPrompt, cartPrompt, bookingPrompt));

    commandMap.put("/seller/add",    new SellerAddHandler(memberList, bookingPrompt, stockPrompt));
    commandMap.put("/seller/list",   new SellerListHandler(memberList));
    commandMap.put("/seller/detail", new SellerDetailHandler(memberList));
    commandMap.put("/seller/update", new SellerUpdateHandler(memberList));
    commandMap.put("/seller/delete", new SellerDeleteHandler(memberList));

    commandMap.put("/board/add",    new BoardAddHandler(boardList));
    commandMap.put("/board/list",   new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList, boardPrompt, memberPrompt));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/search", new BoardSearchHandler(boardList));

    commandMap.put("/product/add",    new ProductAddHandler(productList, productPrompt));
    commandMap.put("/product/list",   new ProductListHandler(stockPrompt, productPrompt, cartPrompt, productList, allStockList, allCartList, memberPrompt));
    commandMap.put("/product/search", new ProductSearchHandler(productPrompt, stockPrompt, memberPrompt, cartPrompt, productList));

    commandMap.put("/product/detail", new ProductDetailHandler(productPrompt, productList));
    commandMap.put("/product/update", new ProductUpdateHandler(productPrompt));
    commandMap.put("/product/delete", new ProductDeleteHandler(productPrompt, productList));

    commandMap.put("/stock/add"  ,  new StockAddHandler(allStockList, stockPrompt,productPrompt));
    commandMap.put("/stock/list",   new StockListHandler(allStockList, stockPrompt));
    commandMap.put("/stock/detail", new StockDetailHandler(stockPrompt));
    commandMap.put("/stock/update", new StockUpdateHandler(stockPrompt));
    commandMap.put("/stock/delete", new StockDeleteHandler(stockPrompt));

    commandMap.put("/cart/add"  ,  new CartAddHandler(allCartList, cartPrompt, stockPrompt, memberPrompt));
    commandMap.put("/cart/list",   new CartListHandler(allCartList, cartPrompt, memberPrompt));
    commandMap.put("/cart/detail", new CartDetailHandler(cartPrompt));
    commandMap.put("/cart/update", new CartUpdateHandler(cartPrompt));
    commandMap.put("/cart/delete", new CartDeleteHandler(cartPrompt));

    commandMap.put("/booking/add",    new BookingAddHandler(allBookingList, cartPrompt, stockPrompt, bookingPrompt, memberPrompt));
    commandMap.put("/booking/list",   new BookingListHandler(allBookingList, bookingPrompt, memberPrompt));
    commandMap.put("/booking/update", new BookingUpdateHandler(allBookingList));
    commandMap.put("/booking/delete", new BookingDeleteHandler(allBookingList));

    commandMap.put("/findId", new FindIdHandler(memberPrompt));
    commandMap.put("/findPassword", new FindPasswordHandler(memberPrompt));

    commandMap.put("/findBoard", new BoardFindHandler(boardList, boardPrompt, memberPrompt));
    commandMap.put("/findComment", new CommentFindHandler(boardList, boardPrompt, memberPrompt));

    commandMap.put("/ranking/list", new RankingHandler(productList));
  }

  void service() {
    memberList.add(new Member("관리자","1234", Menu.ACCESS_ADMIN));

    System.out.println();
    System.out.println("   *****************      ");   
    System.out.println("  | ALCOHOLE FINDER |     ");
    System.out.println("   *****************      ");

    createMenu().execute();
    Prompt.close();

    //List 저장
    saveManagers();
    saveBoards();
    saveProducts();
    saveStockLists();
    saveCartLists();
    saveBookingLists();

  }
  @SuppressWarnings("unchecked")
  private void loadManagers() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("manager.data"))) {
      memberList.addAll((List<Member>) in.readObject());
      //   System.out.println("관리자 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 관리자 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveManagers() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("manager.data"))) {
      out.writeObject(memberList);
      System.out.println("관리자 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 관리자 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoards() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("board.data"))) {
      boardList.addAll((List<Board>) in.readObject());
      //   System.out.println("게시글 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 게시글 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveBoards() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("board.data"))) {
      out.writeObject(boardList);
      System.out.println("게시글 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 게시글 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadProducts() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("product.data"))) {
      productList.addAll((List<Product>) in.readObject());
      //  System.out.println("상품 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 상품 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveProducts() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("product.data"))) {
      out.writeObject(productList);
      System.out.println("상품 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 상품 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadStockLists() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("stockList.data"))) {
      allStockList.addAll((List<StockList>) in.readObject());
      //   System.out.println("재고리스트 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 재고리스트 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveStockLists() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("stockList.data"))) {
      out.writeObject(allStockList);
      System.out.println("재고리스트 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 재고리스트 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadCartLists() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cartList.data"))) {
      allCartList.addAll((List<CartList>) in.readObject());
      //  System.out.println("장바구니리스트 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 장바구니리스트 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveCartLists() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cartList.data"))) {
      out.writeObject(allCartList);
      System.out.println("장바구니 리스트 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 장바구니 리스트 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBookingLists() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("bookingList.data"))) {
      allBookingList.addAll((List<BookingList>) in.readObject());
      //    System.out.println("예약리스트 데이터 로딩 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 예약리스트 데이터를 읽어오는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveBookingLists() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bookingList.data"))) {
      out.writeObject(allBookingList);
      System.out.println("예약리스트 데이터 저장 완료!");
    } catch (Exception e) {
      System.out.println("파일에서 예약리스트 데이터를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }

  Menu createMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup joinMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);
    mainMenuGroup.add(joinMenu);

    joinMenu.add(new MenuItem("일반회원", "/buyer/add"));

    joinMenu.add(new MenuItem("판매자", "/seller/add"));


    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기", ACCESS_LOGOUT);
    mainMenuGroup.add(findMenu);

    findMenu.add(new MenuItem("아이디찾기", "/findId"));

    findMenu.add(new MenuItem("비밀번호찾기", "/findPassword"));

    mainMenuGroup.add(new Menu("로그인", ACCESS_LOGOUT) {
      @Override
      public void execute() {
        Member prv = loginHandler.InputId(); 
        if (prv==null) {
          System.out.println("다시 로그인 해주세요.");
        } else {
          loginMember = prv;
        }
      }});

    mainMenuGroup.add(new Menu("로그아웃", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER) {
      @Override
      public void execute() {
        if ( loginMember.getAuthority()!= 0) {
          loginMember = new Member(); 
          System.out.println("로그아웃이 완료되었습니다."); 
        } else {
          System.out.println("로그인 후 사용해주세요");
        }
      }});

    ///////////////////////////////////////////

    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(boardMenu);

    boardMenu.add(new MenuItem("등록", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER, "/board/add"));
    boardMenu.add(new MenuItem("목록", "/board/list"));
    boardMenu.add(new MenuItem("상세보기", "/board/detail"));
    boardMenu.add(new MenuItem("변경", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER,"/board/update"));
    boardMenu.add(new MenuItem("삭제",ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER, "/board/delete"));
    boardMenu.add(new MenuItem("검색", "/board/search"));

    ///////////////////////////////////////////
    MenuGroup rankingMenu = new MenuGroup("이달의 술");
    mainMenuGroup.add(rankingMenu);
    rankingMenu.add(new MenuItem("이달의 술", "/ranking/list"));

    ///////////////////////////////////////////

    MenuGroup cartMenu = new MenuGroup("장바구니", ACCESS_BUYER );
    mainMenuGroup.add(cartMenu);

    cartMenu.add(new MenuItem("등록", "/cart/add"));
    cartMenu.add(new MenuItem("목록", "/cart/list"));
    cartMenu.add(new MenuItem("상세보기", "/cart/detail"));
    cartMenu.add(new MenuItem("변경", "/cart/update"));
    cartMenu.add(new MenuItem("삭제", "/cart/delete"));

    ///////////////////////////////////////////


    MenuGroup bookingMenu = new MenuGroup("픽업예약", ACCESS_BUYER | ACCESS_SELLER);
    mainMenuGroup.add(bookingMenu);

    bookingMenu.add(new MenuItem("예약등록", ACCESS_BUYER, "/booking/add"));
    bookingMenu.add(new MenuItem("예약내역",  ACCESS_BUYER | ACCESS_SELLER, "/booking/list"));
    bookingMenu.add(new MenuItem("예약변경", "/booking/update"));
    bookingMenu.add(new MenuItem("예약취소", ACCESS_BUYER, "/booking/delete"));

    ///////////////////////////////////////////

    MenuGroup productMenu = new MenuGroup("상품");
    mainMenuGroup.add(productMenu);

    productMenu.add(new MenuItem("등록", ACCESS_ADMIN | ACCESS_SELLER, "/product/add"));
    productMenu.add(new MenuItem("목록", "/product/list"));
    productMenu.add(new MenuItem("상품검색",  "/product/search"));
    productMenu.add(new MenuItem("상세보기", "/product/detail"));
    productMenu.add(new MenuItem("변경",  ACCESS_ADMIN | ACCESS_SELLER, "/product/update"));
    productMenu.add(new MenuItem("삭제", ACCESS_ADMIN | ACCESS_SELLER, "/product/delete"));

    ///////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("프로필", ACCESS_BUYER | ACCESS_SELLER);
    mainMenuGroup.add(personMenu);

    personMenu.add(new MenuItem("개인정보", ACCESS_BUYER, "/buyer/detail"));
    personMenu.add(new MenuItem("개인정보", ACCESS_SELLER, "/seller/detail"));
    personMenu.add(new MenuItem("개인정보 변경", ACCESS_BUYER, "/buyer/update"));
    personMenu.add(new MenuItem("개인정보 변경", ACCESS_SELLER, "/seller/update"));
    personMenu.add(new MenuItem("내 게시글", "/findBoard"));
    personMenu.add(new MenuItem("내 댓글", "/findComment"));
    personMenu.add(new MenuItem("탈퇴", ACCESS_BUYER, "/buyer/delete"));
    personMenu.add(new MenuItem("탈퇴", ACCESS_SELLER, "/seller/delete"));


    MenuGroup sellerStoreMenu = new MenuGroup("My Store", ACCESS_SELLER);
    personMenu.add(sellerStoreMenu);
    sellerStoreMenu.add(new MenuItem("가게 정보 및 재고", "/stock/list") {
      @Override
      public void execute() {
        Member mine = memberPrompt.findById(App.getLoginUser().getId());
        System.out.printf("\n> 가게명\t:\t%s\n", ((Seller) mine).getBusinessName());
        System.out.printf("> 주소\t:\t%s\n", ((Seller) mine).getBusinessAddress());
        System.out.printf("> 전화번호\t:\t%s\n", ((Seller) mine).getBusinessPlaceNumber());
        System.out.println("-----------------------------------------------");
        Command command  = commandMap.get(menuId);
        command.execute();
      }});


    sellerStoreMenu.add(new MenuItem("재고등록", "/stock/add"));
    sellerStoreMenu.add(new MenuItem("상세보기", "/stock/detail"));
    sellerStoreMenu.add(new MenuItem("재고변경", "/stock/update"));
    sellerStoreMenu.add(new MenuItem("재고삭제", "/stock/delete"));

    ///////////////////////////////////////////

    MenuGroup managerMenu = new MenuGroup("관리자모드", ACCESS_ADMIN );
    mainMenuGroup.add(managerMenu);

    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리"); //1
    managerMenu.add(managerMemberMenu1);

    managerMemberMenu1.add(new MenuItem("목록", "/buyer/list"));
    managerMemberMenu1.add(new MenuItem("상세보기", "/buyer/detail"));
    managerMemberMenu1.add(new MenuItem("변경", "/buyer/update"));
    managerMemberMenu1.add(new MenuItem("삭제", "/buyer/delete"));

    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");  //2
    managerMenu.add(managerSellerMenu1);

    managerSellerMenu1.add(new MenuItem("목록", "/seller/list"));
    managerSellerMenu1.add(new MenuItem("상세보기", "/seller/detail"));
    managerSellerMenu1.add(new MenuItem("변경", "/seller/update"));
    managerSellerMenu1.add(new MenuItem("삭제", "/seller/delete"));

    return mainMenuGroup;
  }

  public static String level(int i) {
    switch (i) {
      case Menu.ACCESS_LOGOUT : return "비회원";
      case Menu.ACCESS_BUYER : return "일반회원";
      case Menu.ACCESS_SELLER : return "판매자";
      default : return "관리자";
    }
  }

}