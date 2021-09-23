package com.eomcs.pms.listener;

import java.util.Map;
import com.eomcs.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> params) {
    System.out.println("   *****************      ");   
    System.out.println("  | ALCOHOLE FINDER |     ");
    System.out.println("   *****************      ");


  }
  @Override
  public void contextDestroyed(Map<String, Object> params) {
    System.out.println("   *****************      ");   
    System.out.println("  |     BuyBuy      |     ");
    System.out.println("   *****************      ");


  }
}
