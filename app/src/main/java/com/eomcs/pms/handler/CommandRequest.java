package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.Map;

public class CommandRequest {

  Map<String, Object> paramMap = new HashMap<>();

  Map<String, Command> commandMap;

  public CommandRequest(Map<String, Command> commandMap) {
    this.commandMap = commandMap;
  }

  public void setAttribute(String name, Object value) {
    paramMap.put(name, value);
  }

  public Object getAttribute(String name) {
    return paramMap.get(name);
  }

  public RequestDispatcher getRequestDispatcher(String commandId) {
    Command  command = commandMap.get(commandId);
    if (command == null) {
      return null;
    }
    return new RequestDispatcher(command);
  }

}
