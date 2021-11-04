package com.eomcs.pms.jsp.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
  private static MemberDAO instance;

  // 싱글톤 패턴
  private MemberDAO(){}
  public static MemberDAO getInstance(){
    if(instance==null)
      instance=new MemberDAO();
    return instance;
  }

  // 로그인시 아이디, 비밀번호 체크 메서드
  // 아이디, 비밀번호를 인자로 받는다.
  public int loginCheck(String id, String pw) 
  {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String dbPW = ""; // db에서 꺼낸 비밀번호를 담을 변수
    int x = -1;

    try {
      // 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
      String dbURL = "jdbc:mysql://localhost:3306/drinkerdb";
      String dbID = "drinker";
      String dbPwd = "1111";
      Class.forName("org.mariadb.jdbc.Driver");
      StringBuffer query = new StringBuffer();
      query.append("SELECT PASSWORD FROM member WHERE ID=?");

      conn = DriverManager.getConnection(dbURL, dbID, dbPwd);
      pstmt = conn.prepareStatement(query.toString());
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();

      if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
      {
        dbPW = rs.getString("password"); // 비번을 변수에 넣는다.

        if (dbPW.equals(pw)) 
          x = 1; // 넘겨받은 비번과 꺼내온 비번 비교. 같으면 인증성공
        else                  
          x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패

      } else {
        x = -1; // 해당 아이디가 없을 경우
      }

      return x;

    } catch (Exception sqle) {
      throw new RuntimeException(sqle.getMessage());
    } finally {
      try{
        if ( pstmt != null ){ pstmt.close(); pstmt=null; }
        if ( conn != null ){ conn.close(); conn=null;    }
      }catch(Exception e){
        throw new RuntimeException(e.getMessage());
      }
    }
  } // end loginCheck()
}