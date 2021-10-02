package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_BUYER;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import static com.eomcs.menu.Menu.ACCESS_SELLER;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuFilter;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.impl.NetBuyerDao;
import com.eomcs.pms.dao.impl.NetSellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardDetailHandler2;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.BuyerAddHandler;
import com.eomcs.pms.handler.BuyerDeleteHandler;
import com.eomcs.pms.handler.BuyerDetailHandler;
import com.eomcs.pms.handler.BuyerListHandler;
import com.eomcs.pms.handler.BuyerLoginHandler;
import com.eomcs.pms.handler.BuyerUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.CommandRequest;
import com.eomcs.pms.handler.ProductAddHandler;
import com.eomcs.pms.handler.ProductDeleteHandler;
import com.eomcs.pms.handler.ProductDetailHandler;
import com.eomcs.pms.handler.ProductListHandler;
import com.eomcs.pms.handler.ProductPrompt;
import com.eomcs.pms.handler.ProductSearchHandler;
import com.eomcs.pms.handler.ProductUpdateHandler;
import com.eomcs.pms.handler.SellerAddHandler;
import com.eomcs.pms.handler.SellerDeleteHandler;
import com.eomcs.pms.handler.SellerDetailHandler;
import com.eomcs.pms.handler.SellerListHandler;
import com.eomcs.pms.handler.SellerLoginHandler;
import com.eomcs.pms.handler.SellerUpdateHandler;
import com.eomcs.pms.lisner.AppInitListener;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ClientApp {
  RequestAgent requestAgent;
  HashMap<String, Command> commandMap = new HashMap<>();
  static List<Member> memberList = new ArrayList<>();

  //권한에 따른 메뉴 구성 위함.
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

  // 리스너
  List<ApplicationContextListener> listeners = new ArrayList<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  private void notifyOnApplicationStarted() {
    HashMap<String, Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    HashMap<String, Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }

  public ClientApp() throws Exception {

    requestAgent = new RequestAgent("127.0.0.1",8888);
    BuyerDao buyerDao = new NetBuyerDao(requestAgent);
    SellerDao sellerDao = new NetSellerDao(requestAgent);

    commandMap.put("/buyer/login", new BuyerLoginHandler(buyerDao));
    //    commandMap.put("/seller/login", new SellerLoginHandler(sellerDao));

    commandMap.put("/buyer/add", new BuyerAddHandler(buyerDao));
    commandMap.put("/buyer/list",   new BuyerListHandler(buyerDao));
    commandMap.put("/buyer/detail", new BuyerDetailHandler(buyerDao));
    commandMap.put("/buyer/update", new BuyerUpdateHandler(buyerDao));
    commandMap.put("/buyer/delete", new BuyerDeleteHandler(buyerDao));

    commandMap.put("/seller/login", new SellerLoginHandler(sellerDao));
    //    commandMap.put("/login", new LoginHandler(requestAgent));

    commandMap.put("/seller/add",    new SellerAddHandler(sellerDao));
    commandMap.put("/seller/list",   new SellerListHandler(sellerDao));
    commandMap.put("/seller/detail", new SellerDetailHandler(sellerDao));
    commandMap.put("/seller/update", new SellerUpdateHandler(sellerDao));
    commandMap.put("/seller/delete", new SellerDeleteHandler(sellerDao));

    commandMap.put("/board/add",    new BoardAddHandler(requestAgent));
    commandMap.put("/board/list",   new BoardListHandler(requestAgent));
    commandMap.put("/board/update",   new BoardUpdateHandler(requestAgent));
    commandMap.put("/board/detail",   new BoardDetailHandler(requestAgent));
    commandMap.put("/board/detail2",   new BoardDetailHandler2(requestAgent));
    commandMap.put("/board/update",   new BoardUpdateHandler(requestAgent));
    commandMap.put("/board/delete",   new BoardDeleteHandler(requestAgent));
    commandMap.put("/board/search",   new BoardSearchHandler(requestAgent));

    ProductPrompt productPrompt = new ProductPrompt(requestAgent);
    commandMap.put("/product/add",   new ProductAddHandler(requestAgent));
    commandMap.put("/product/list",   new ProductListHandler(requestAgent));
    commandMap.put("/product/search", new ProductSearchHandler(requestAgent, productPrompt));
    commandMap.put("/product/detail", new ProductDetailHandler(requestAgent, productPrompt));
    commandMap.put("/product/update", new ProductUpdateHandler(requestAgent, productPrompt));
    commandMap.put("/product/delete",   new ProductDeleteHandler(requestAgent));

  }

  MenuFilter menuFilter = menu -> (menu.getAccessScope() & getLoginUser().getAuthority()) > 0;

  public static Member loginMember = new Member();
  public static Member getLoginUser() {
    return loginMember;
  }


  Menu createMainMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setMenuFilter(menuFilter);
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup loginGroup = new MenuGroup("로그인",ACCESS_LOGOUT);
    loginGroup.setMenuFilter(menuFilter);
    mainMenuGroup.add(loginGroup);
    loginGroup.add(new MenuItem("일반회원", ACCESS_LOGOUT, "/buyer/login"));
    loginGroup.add(new MenuItem("판매자", ACCESS_LOGOUT, "/seller/login"));

    mainMenuGroup.add(new Menu("로그아웃", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER) {

      @Override
      public void execute() {
        if (loginMember.getAuthority()!= 0) {
          loginMember = new Member(); 
          System.out.println("로그아웃이 완료되었습니다."); 
        } else {
          System.out.println("로그인 후 사용해주세요");
        }
      }});

    ///////////////////////////////////////////

    //    MenuGroup rankingMenu = new MenuGroup("실시간 랭킹");
    mainMenuGroup.add(new MenuItem("실시간 랭킹",  "/ranking/list"));

    ///////////////////////////////////////////

    //    MenuGroup boardMenu = new MenuGroup("게시판");
    mainMenuGroup.add(new MenuItem("게시판", "/board/list"));

    ///////////////////////////////////////////

    //  mainMenuGroup.add(new MenuItem("상품", "/product/list"));

    //MenuGroup productMenu = new MenuGroup("상품");
    //productMenu.setMenuFilter(menuFilter);
    //mainMenuGroup.add(productMenu);

    mainMenuGroup.add(new MenuItem("상품", "/product/list"));

    //    productMenu.add(new MenuItem("상품상세", "/product/detail"));
    //    productMenu.add(new MenuItem("상품변경", "/product/update"));
    //    productMenu.add(new MenuItem("상품삭제",  "/product/delete"));

    ///////////////////////////////////////////

    //    MenuGroup cartMenu = new MenuGroup("장바구니", ACCESS_BUYER );
    mainMenuGroup.add(new MenuItem("장바구니",  ACCESS_BUYER, "/cart/list"));

    ///////////////////////////////////////////

    //    MenuGroup bookingMenu = new MenuGroup("픽업예약", ACCESS_BUYER | ACCESS_SELLER);
    mainMenuGroup.add(new MenuItem("예약내역",  ACCESS_BUYER | ACCESS_SELLER, "/booking/list"));

    ///////////////////////////////////////////

    MenuGroup joinMenu = new MenuGroup("회원가입", ACCESS_LOGOUT);
    joinMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(joinMenu);
    joinMenu.setMenuFilter(menuFilter);

    joinMenu.add(new MenuItem("일반회원", "/buyer/add"));
    joinMenu.add(new MenuItem("판매자", "/seller/add"));

    MenuGroup findMenu = new MenuGroup("아이디/비번 찾기", ACCESS_LOGOUT);
    findMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(findMenu);

    findMenu.add(new MenuItem("아이디찾기", "/findId"));
    findMenu.add(new MenuItem("비밀번호찾기", "/findPassword"));

    ////////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("프로필", ACCESS_BUYER | ACCESS_SELLER);
    personMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(personMenu);
    personMenu.setMenuFilter(menuFilter);

    personMenu.add(new MenuItem("My Store", ACCESS_SELLER, "/stock/list") {
      @Override
      public void execute() {
        //        Member mine = memberPrompt.findById(App.getLoginUser().getId());
        Member mine = new Member();
        System.out.printf("<<\t%s\t>>\n", ((Seller) mine).getBusinessName());
        System.out.printf("> 주소\t:\t%s\n", ((Seller) mine).getBusinessAddress());
        System.out.printf("> 전화번호\t:\t%s\n", ((Seller) mine).getBusinessPlaceNumber());
        System.out.println();
        Command command  = commandMap.get(menuId);
        try {
          command.execute(new CommandRequest(commandMap));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }});

    personMenu.add(new MenuItem("개인정보", ACCESS_BUYER, "/buyer/detail"));
    personMenu.add(new MenuItem("개인정보", ACCESS_SELLER, "/seller/detail"));
    personMenu.add(new MenuItem("내 게시글", "/findBoard"));
    personMenu.add(new MenuItem("내 댓글", "/findComment"));
    personMenu.add(new MenuItem("내 리뷰", "/findReview"));


    MenuGroup managerMenu = new MenuGroup("관리자모드", ACCESS_ADMIN );
    managerMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(managerMenu);

    //    MenuGroup managerMemberMenu1 = new MenuGroup("일반회원관리"); //1
    managerMenu.add(new MenuItem("일반회원관리", "/buyer/list"));

    //    MenuGroup managerSellerMenu1 = new MenuGroup("판매자관리");  //2
    managerMenu.add(new MenuItem("판매자관리", "/seller/list"));

    //    MenuGroup messageMenu = new MenuGroup("메세지", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER);
    mainMenuGroup.add(new MenuItem("메세지", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER, "/message/list"));

    return mainMenuGroup;
  }

  void service() throws Exception {
    notifyOnApplicationStarted();
    // 관리자 계정 생성

    createMainMenu().execute();

    // memberList.add(new Member("관리자","1234", Menu.ACCESS_ADMIN));

    Prompt.close();

    notifyOnApplicationEnded();

  }
  public static void main(String[] args) throws Exception {
    System.out.println("[PMS 클라이언트]");

    ClientApp app = new ClientApp();
    app.addApplicationContextListener(new AppInitListener());
    app.service();
    Prompt.close();
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