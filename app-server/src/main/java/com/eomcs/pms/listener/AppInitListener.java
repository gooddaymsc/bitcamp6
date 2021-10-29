package com.eomcs.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.SellerDao;


@WebListener
public class AppInitListener implements ServletContextListener {

  SqlSession sqlSession;

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    try {
      sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
          "com/eomcs/pms/conf/mybatis-config.xml")).openSession();

      BuyerDao buyerDao = sqlSession.getMapper(BuyerDao.class);
      SellerDao sellerDao = sqlSession.getMapper(SellerDao.class);
      ProductDao productDao = sqlSession.getMapper(ProductDao.class);

      ServletContext 웹애플리케이션공용저장소 = sce.getServletContext();

      웹애플리케이션공용저장소.setAttribute("buyerDao", buyerDao);
      웹애플리케이션공용저장소.setAttribute("sellerDao", sellerDao);
      웹애플리케이션공용저장소.setAttribute("productDao", productDao);
      웹애플리케이션공용저장소.setAttribute("sqlSession", sqlSession);      

    } catch (Exception e) {
      System.out.println("DAO 객체 준비 중 오류 발생!");
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("애플리케이션 종료됨!");
    sqlSession.close();
  }
}
