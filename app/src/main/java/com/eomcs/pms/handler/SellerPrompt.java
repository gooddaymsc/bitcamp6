package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.domain.Seller;

public class SellerPrompt {

  List<Seller> sellerList;
  public SellerPrompt(List<Seller> sellerList) {
    this.sellerList = sellerList;
  }
  public Seller findBySellerInfo (String SellerId) {
    for (Seller seller : sellerList) {
      if (seller.getId().equals(SellerId)){
        return seller;
      }
    }
    return null;
  }


  public Seller findByPlaceName (String storeName) {
    for (Seller seller : sellerList) {
      if (seller.getBusinessName().equals(storeName)){
        return seller;
      }
    }
    return null;
  }


  //입력한 문자열을 포함하면 adress 리턴.

  public HashMap<String, Seller> findByAdress (String adress) {

    HashMap<String, Seller> hashMap = new HashMap<>();
    String[] splitted = adress.split(" ");
    for (Seller seller : sellerList) { 
      if(splitted[2].equals(seller.getBusinessAddress()) && 
          splitted[1].equals(seller.getBusinessAddress())) {
        System.out.println("\n[해당 주소 근처 판매처] ");
        hashMap.put(seller.getId(), seller);
        break;
      }
      else {
        return null;
      }
    }      
    return hashMap;
  }
}
