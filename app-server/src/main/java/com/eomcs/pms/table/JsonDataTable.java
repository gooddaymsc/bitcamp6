package com.eomcs.pms.table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class JsonDataTable<T> {
  protected List<T> list = new ArrayList<>();
  protected Class<T> elementType;
  protected String filename;

  public JsonDataTable() {
  }

  public JsonDataTable(String filename, Class<T> elementType) {
    this.filename = filename;
    this.elementType = elementType;
    loadObjects();
  }

  public void save() {
    saveObjects();
  }
  private void loadObjects() {

    try (BufferedReader in = new BufferedReader(
        new FileReader(filename, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      Type type = TypeToken.getParameterized(Collection.class, elementType).getType();
      Collection<T> collection = new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);
      System.out.printf("%s 데이터 로딩 완료!\n", filename);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filename);
    }
  }

  private void saveObjects() {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filename, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filename);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filename);
      e.printStackTrace();
    }
  }
}
