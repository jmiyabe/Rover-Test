package com.miyabe.app.ports;

import com.miyabe.app.exceptions.CommandException;

public interface RoverService {
  void moveRover();
  void commandRover(String command) throws CommandException;
  String getPosition();
}
