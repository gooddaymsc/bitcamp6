package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_BUYER;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import static com.eomcs.menu.Menu.ACCESS_SELLER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuFilter;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.MessageDao;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardFindDetailHandler;
import com.eomcs.pms.handler.BoardFindHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardSearchHandler2;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.BookingAddHandler;
import com.eomcs.pms.handler.BookingConfirmHandler;
import com.eomcs.pms.handler.BookingDeleteHandler;
import com.eomcs.pms.handler.BookingDetailHandler;
import com.eomcs.pms.handler.BookingHandlerHelper;
import com.eomcs.pms.handler.BookingListHandler;
import com.eomcs.pms.handler.BookingUpdateHandler;
import com.eomcs.pms.handler.BuyerAddHandler;
import com.eomcs.pms.handler.BuyerDeleteHandler;
import com.eomcs.pms.handler.BuyerDetailHandler;
import com.eomcs.pms.handler.BuyerListHandler;
import com.eomcs.pms.handler.BuyerUpdateHandler;
import com.eomcs.pms.handler.CartAddHandler;
import com.eomcs.pms.handler.CartDeleteHandler;
import com.eomcs.pms.handler.CartDetailHandler;
import com.eomcs.pms.handler.CartHandlerHelper;
import com.eomcs.pms.handler.CartListHandler;
import com.eomcs.pms.handler.CartUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.CommandRequest;
import com.eomcs.pms.handler.CommentAddHandler;
import com.eomcs.pms.handler.CommentDeleteHandler;
import com.eomcs.pms.handler.CommentFindHandler;
import com.eomcs.pms.handler.CommentListHandler;
import com.eomcs.pms.handler.CommentUpdateHandler;
import com.eomcs.pms.handler.FindIdHandler;
import com.eomcs.pms.handler.LikeHandler;
import com.eomcs.pms.handler.LoginHandler;
import com.eomcs.pms.handler.MessageAddHandler;
import com.eomcs.pms.handler.MessageDeleteHandler;
import com.eomcs.pms.handler.MessageDetailHandler;
import com.eomcs.pms.handler.MessageListHandler;
import com.eomcs.pms.handler.MessageUpdateHandler;
import com.eomcs.pms.handler.ProductAddHandler;
import com.eomcs.pms.handler.ProductDeleteHandler;
import com.eomcs.pms.handler.ProductDetailHandler;
import com.eomcs.pms.handler.ProductListHandler;
import com.eomcs.pms.handler.ProductSearchHandler;
import com.eomcs.pms.handler.ProductUpdateHandler;
import com.eomcs.pms.handler.ProductValidation;
import com.eomcs.pms.handler.RankingHandler;
import com.eomcs.pms.handler.ReviewAddHandler;
import com.eomcs.pms.handler.ReviewDeleteHandler;
import com.eomcs.pms.handler.ReviewFindHandler;
import com.eomcs.pms.handler.ReviewListHandler;
import com.eomcs.pms.handler.ReviewUpdateHandler;
import com.eomcs.pms.handler.SellerAddHandler;
import com.eomcs.pms.handler.SellerDeleteHandler;
import com.eomcs.pms.handler.SellerDetailHandler;
import com.eomcs.pms.handler.SellerListHandler;
import com.eomcs.pms.handler.SellerUpdateHandler;
import com.eomcs.pms.handler.StockAddHandler;
import com.eomcs.pms.handler.StockDeleteHandler;
import com.eomcs.pms.handler.StockDetailHandler;
import com.eomcs.pms.handler.StockListHandler;
import com.eomcs.pms.handler.StockUpdateHandler;
import com.eomcs.pms.handler.UpdatePasswordHandler;
import com.eomcs.pms.lisner.AppInitListener;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class ClientApp {

  Connection con;
  RequestAgent requestAgent;
  HashMap<String, Command> commandMap = new HashMap<>();

  //????????? ?????? ?????? ?????? ??????.
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
        System.out.printf("%s ????????? ???????????? ??? ?????? ??????!\n",  menuId);
        e.printStackTrace();
      }
    }
  }

  // ?????????
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

    con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/drinkerdb?user=drinker&password=1111");

    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/pms/conf/mybatis-config.xml")).openSession();

    MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
    SellerDao sellerDao = sqlSession.getMapper(SellerDao.class);
    BuyerDao buyerDao = sqlSession.getMapper(BuyerDao.class);
    BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
    CommentDao commentDao = sqlSession.getMapper(CommentDao.class);
    StockDao stockDao = sqlSession.getMapper(StockDao.class);
    ProductDao productDao = sqlSession.getMapper(ProductDao.class);
    ReviewDao reviewDao = sqlSession.getMapper(ReviewDao.class);
    MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
    CartDao cartDao = sqlSession.getMapper(CartDao.class);
    BookingDao bookingDao = sqlSession.getMapper(BookingDao.class);

    ProductValidation productValidation = new ProductValidation(sellerDao, stockDao);
    CartHandlerHelper cartHelper = new CartHandlerHelper(stockDao);
    BookingHandlerHelper bookingHelper = new BookingHandlerHelper(bookingDao);

    commandMap.put("/buyer/add", new BuyerAddHandler(buyerDao, sqlSession));
    commandMap.put("/buyer/list",   new BuyerListHandler(buyerDao));
    commandMap.put("/buyer/detail", new BuyerDetailHandler(buyerDao));
    commandMap.put("/buyer/update", new BuyerUpdateHandler(buyerDao, sqlSession));
    commandMap.put("/buyer/delete", new BuyerDeleteHandler(buyerDao, sqlSession));

    commandMap.put("/login", new LoginHandler(memberDao));

    commandMap.put("/seller/add",    new SellerAddHandler(sellerDao, sqlSession));
    commandMap.put("/seller/list",   new SellerListHandler(sellerDao));
    commandMap.put("/seller/detail", new SellerDetailHandler(sellerDao));
    commandMap.put("/seller/update", new SellerUpdateHandler(sellerDao, sqlSession));
    commandMap.put("/seller/delete", new SellerDeleteHandler(sellerDao, sqlSession));

    commandMap.put("/board/add",    new BoardAddHandler(boardDao, sqlSession));
    commandMap.put("/board/list",   new BoardListHandler(boardDao));
    commandMap.put("/board/detail",   new BoardDetailHandler(boardDao, memberDao, sqlSession));
    commandMap.put("/board/detail2",   new BoardFindDetailHandler(boardDao, memberDao));
    commandMap.put("/board/update",   new BoardUpdateHandler(boardDao, sqlSession));
    commandMap.put("/board/delete",   new BoardDeleteHandler(boardDao, sqlSession));
    commandMap.put("/board/search",   new BoardSearchHandler(boardDao));
    commandMap.put("/board/search2",   new BoardSearchHandler2(boardDao));


    commandMap.put("/comment/like",   new LikeHandler(boardDao, sqlSession));
    commandMap.put("/comment/add",   new CommentAddHandler(commentDao, memberDao, sqlSession));
    commandMap.put("/comment/list",   new CommentListHandler(commentDao));
    commandMap.put("/comment/update",   new CommentUpdateHandler(commentDao, sqlSession));
    commandMap.put("/comment/delete",   new CommentDeleteHandler(commentDao, sqlSession));

    commandMap.put("/product/add",   new ProductAddHandler(productDao,sqlSession));
    commandMap.put("/product/list",   new ProductListHandler(productDao));
    commandMap.put("/product/search", new ProductSearchHandler(productDao, productValidation));
    commandMap.put("/product/detail", new ProductDetailHandler(productDao));
    commandMap.put("/product/update", new ProductUpdateHandler(productDao,sqlSession));
    commandMap.put("/product/delete",   new ProductDeleteHandler(productDao,sqlSession));


    commandMap.put("/review/add",   new ReviewAddHandler(reviewDao, productDao, sqlSession));
    commandMap.put("/review/list",   new ReviewListHandler(reviewDao));
    commandMap.put("/review/update",   new ReviewUpdateHandler(reviewDao, productDao, sqlSession));
    commandMap.put("/review/delete",   new ReviewDeleteHandler(reviewDao, sqlSession));

    commandMap.put("/findId"  ,  new FindIdHandler(memberDao));
    commandMap.put("/updatePassword",   new UpdatePasswordHandler(memberDao, sqlSession));
    commandMap.put("/findBoard", new BoardFindHandler(boardDao));
    commandMap.put("/findComment", new CommentFindHandler(boardDao));
    commandMap.put("/findReview",   new ReviewFindHandler(reviewDao, productDao));

    commandMap.put("/stock/add"  ,  new StockAddHandler(stockDao, sellerDao, sqlSession));
    commandMap.put("/stock/list",   new StockListHandler(stockDao));
    commandMap.put("/stock/detail", new StockDetailHandler(stockDao));
    commandMap.put("/stock/update", new StockUpdateHandler(stockDao,sqlSession));
    commandMap.put("/stock/delete", new StockDeleteHandler(stockDao,sqlSession));

    commandMap.put("/cart/add"  ,  new CartAddHandler(cartDao, cartHelper, sqlSession));
    commandMap.put("/cart/list",   new CartListHandler(cartDao, sellerDao));
    commandMap.put("/cart/detail", new CartDetailHandler(cartDao));
    commandMap.put("/cart/update", new CartUpdateHandler(cartDao, sqlSession));
    commandMap.put("/cart/delete", new CartDeleteHandler(cartDao, sqlSession));

    commandMap.put("/booking/add",    new BookingAddHandler(bookingDao, stockDao, cartDao, bookingHelper, sqlSession));
    commandMap.put("/booking/list",   new BookingListHandler(bookingDao));
    commandMap.put("/booking/detail",   new BookingDetailHandler(bookingDao, buyerDao));
    commandMap.put("/booking/confirm",   new BookingConfirmHandler(bookingDao, buyerDao, sellerDao));
    commandMap.put("/booking/update", new BookingUpdateHandler(bookingDao, bookingHelper, sqlSession));
    commandMap.put("/booking/delete", new BookingDeleteHandler(bookingDao, sqlSession));

    commandMap.put("/message/add",    new MessageAddHandler(messageDao, sqlSession, memberDao));
    commandMap.put("/message/update",    new MessageUpdateHandler(messageDao, sqlSession));
    commandMap.put("/message/list",   new MessageListHandler(messageDao));
    commandMap.put("/message/detail", new MessageDetailHandler(messageDao));
    commandMap.put("/message/delete", new MessageDeleteHandler(messageDao, sqlSession));

    //    commandMap.put("/findId"  ,  new FindIdHandler(memberDao));
    //    commandMap.put("/findPassword",   new UpdatePasswordHandler(memberDao, sqlSession));

    commandMap.put("/ranking/list",   new RankingHandler(productDao));
  }

  MenuFilter menuFilter = menu -> (menu.getAccessScope() & getLoginUser().getAuthority()) > 0;

  public static Member loginMember = new Member();
  public static Member getLoginUser() {
    return loginMember;
  }


  Menu createMainMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("??????");
    mainMenuGroup.setMenuFilter(menuFilter);
    mainMenuGroup.setPrevMenuTitle("??????");

    mainMenuGroup.add(new MenuItem("?????????", ACCESS_LOGOUT, "/login"));

    mainMenuGroup.add(new Menu("????????????", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER) {

      @Override
      public void execute() {
        if (loginMember.getAuthority()!= 0) {
          loginMember = new Member(); 
          System.out.println("??????????????? ?????????????????????."); 
        } else {
          System.out.println("????????? ??? ??????????????????");
        }
      }});

    ///////////////////////////////////////////

    //    MenuGroup rankingMenu = new MenuGroup("????????? ??????");
    mainMenuGroup.add(new MenuItem("????????? ??????",  "/ranking/list"));

    ///////////////////////////////////////////

    //    MenuGroup boardMenu = new MenuGroup("?????????");
    mainMenuGroup.add(new MenuItem("?????????", "/board/list"));

    ///////////////////////////////////////////

    //  mainMenuGroup.add(new MenuItem("??????", "/product/list"));

    //MenuGroup productMenu = new MenuGroup("??????");
    //productMenu.setMenuFilter(menuFilter);
    //mainMenuGroup.add(productMenu);

    mainMenuGroup.add(new MenuItem("??????", "/product/list"));

    //    productMenu.add(new MenuItem("????????????", "/product/detail"));
    //    productMenu.add(new MenuItem("????????????", "/product/update"));
    //    productMenu.add(new MenuItem("????????????",  "/product/delete"));

    ///////////////////////////////////////////

    //    MenuGroup cartMenu = new MenuGroup("????????????", ACCESS_BUYER );
    mainMenuGroup.add(new MenuItem("????????????",  ACCESS_BUYER, "/cart/list"));

    ///////////////////////////////////////////

    //    MenuGroup bookingMenu = new MenuGroup("????????????", ACCESS_BUYER | ACCESS_SELLER);
    mainMenuGroup.add(new MenuItem("????????????",  ACCESS_BUYER | ACCESS_SELLER, "/booking/list"));

    ///////////////////////////////////////////

    MenuGroup joinMenu = new MenuGroup("????????????", ACCESS_LOGOUT);
    joinMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(joinMenu);
    joinMenu.setMenuFilter(menuFilter);

    joinMenu.add(new MenuItem("????????????", "/buyer/add"));
    joinMenu.add(new MenuItem("?????????", "/seller/add"));

    MenuGroup findMenu = new MenuGroup("?????????/?????? ??????", ACCESS_LOGOUT);
    findMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(findMenu);

    findMenu.add(new MenuItem("???????????????", "/findId"));
    findMenu.add(new MenuItem("??????????????????", "/updatePassword"));

    ////////////////////////////////////////////

    MenuGroup personMenu = new MenuGroup("?????????", ACCESS_BUYER | ACCESS_SELLER);
    personMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(personMenu);
    personMenu.setMenuFilter(menuFilter);

    personMenu.add(new MenuItem("My Store", ACCESS_SELLER, "/stock/list"));
    personMenu.add(new MenuItem("????????????", ACCESS_BUYER, "/buyer/detail"));
    personMenu.add(new MenuItem("????????????", ACCESS_SELLER, "/seller/detail"));
    personMenu.add(new MenuItem("??? ?????????", "/findBoard"));
    personMenu.add(new MenuItem("??? ??????", "/findComment"));
    personMenu.add(new MenuItem("??? ??????", "/findReview"));


    MenuGroup managerMenu = new MenuGroup("???????????????", ACCESS_ADMIN );
    managerMenu.setMenuFilter(menuFilter);
    mainMenuGroup.add(managerMenu);

    //    MenuGroup managerMemberMenu1 = new MenuGroup("??????????????????"); //1
    managerMenu.add(new MenuItem("??????????????????", "/buyer/list"));

    //    MenuGroup managerSellerMenu1 = new MenuGroup("???????????????");  //2
    managerMenu.add(new MenuItem("???????????????", "/seller/list"));

    //    MenuGroup messageMenu = new MenuGroup("?????????", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER);
    mainMenuGroup.add(new MenuItem("?????????", ACCESS_BUYER | ACCESS_ADMIN | ACCESS_SELLER, "/message/list"));

    return mainMenuGroup;
  }

  void service() throws Exception {
    notifyOnApplicationStarted();
    // ????????? ?????? ??????
    //    requestAgent.request("member.insert", new Member("?????????","1234", Menu.ACCESS_ADMIN));

    createMainMenu().execute();

    Prompt.close();

    notifyOnApplicationEnded();

    con.close();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("[PMS ???????????????]");

    ClientApp app = new ClientApp();
    app.addApplicationContextListener(new AppInitListener());
    app.service();
    Prompt.close();
  }
  public static String level(int i) {
    switch (i) {
      case Menu.ACCESS_LOGOUT : return "?????????";
      case Menu.ACCESS_BUYER : return "????????????";
      case Menu.ACCESS_SELLER : return "?????????";
      default : return "?????????";
    }
  }
}