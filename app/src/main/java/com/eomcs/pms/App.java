package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_BUYER;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import static com.eomcs.menu.Menu.ACCESS_SELLER;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Buyer;
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
import com.eomcs.pms.handler.CommandRequest;
import com.eomcs.pms.handler.CommentAddHandler;
import com.eomcs.pms.handler.CommentDeleteHandler;
import com.eomcs.pms.handler.CommentFindHandler;
import com.eomcs.pms.handler.CommentListHandler;
import com.eomcs.pms.handler.CommentUpdateHandler;
import com.eomcs.pms.handler.FindIdHandler;
import com.eomcs.pms.handler.FindPasswordHandler;
import com.eomcs.pms.handler.LikeHandler;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
  public static final int MEMBER_NUMBER_INDEX = 0;
  public static final int BOARD_NUMBER_INDEX = 1;
  public static final int PROUDCT_NUMBER_INDEX = 2;

  List<Board> boardList = new ArrayList<>();
  List<Product> productList = new ArrayList<>();
  List<StockList> allStockList = new ArrayList<>();
  List<BookingList> allBookingList = new ArrayList<>();
  List<CartList> allCartList = new ArrayList<>();
  List<Member> memberList = new ArrayList<>();
  List<Buyer> buyerList = new ArrayList<>();
  List<Seller> sellerList = new ArrayList<>();
  public static List<Integer> totalNumberList = new ArrayList<>();// totalMemberNumber, totalBoardNumber, totalProductNumber

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
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!\n",  menuId);
        e.printStackTrace();
      }
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
    loadObjects("totalNumber.json", totalNumberList, Integer.class);

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

    commandMap.put("/comment/like",    new LikeHandler(boardPrompt));
    commandMap.put("/comment/add",    new CommentAddHandler(boardPrompt));
    commandMap.put("/comment/list",    new CommentListHandler(boardPrompt));
    commandMap.put("/comment/update",    new CommentUpdateHandler(boardPrompt));
    commandMap.put("/comment/delete",    new CommentDeleteHandler(boardPrompt));


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
    if (totalNumberList.size() == 0) {
      totalNumberList.add(MEMBER_NUMBER_INDEX, 1); 
      totalNumberList.add(BOARD_NUMBER_INDEX, 1); 
      totalNumberList.add(PROUDCT_NUMBER_INDEX, 1);
    }

    System.out.println();
    System.out.println("   *****************      ");   
    System.out.println("  | ALCOHOLE FINDER |     ");
    System.out.println("   *****************      ");

    createMenu().execute();
    Prompt.close();

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
    saveObjects("totalNumber.json", totalNumberList);

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
    for (Member member : memberList) {
      if (member instanceof Buyer) {
        buyerList.add((Buyer)member);   
      } else if (member instanceof Seller) {
        sellerList.add((Seller)member);
      }
    }
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

    MenuGroup rankingMenu = new MenuGroup("실시간 랭킹");
    mainMenuGroup.add(rankingMenu);

    rankingMenu.add(new MenuItem("실시간 랭킹",  "/ranking/list"));

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
        try {
          command.execute(new CommandRequest(commandMap));
        } catch (Exception e) {
          e.printStackTrace();
        }
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