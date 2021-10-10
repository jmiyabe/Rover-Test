package com.miyabe.app.core;

import com.miyabe.app.exceptions.CardinalException;
import com.miyabe.app.exceptions.CommandException;
import com.miyabe.app.ports.RoverService;

import java.util.Arrays;
import java.util.List;

public class RoverServiceImpl implements RoverService {
  private int platformX;
  private int platformY;
  private int positionX;
  private int positionY;
  private int cardinalPosition;
  private final List<String> arrayCardinals = Arrays.asList(new String[] {"N", "E", "S", "W"});

  public RoverServiceImpl(int platformX, int platformY, int positionX, int positionY, String cardinal) throws CardinalException {
    this.platformX = platformX;
    this.platformY = platformY;
    this.positionX = positionX;
    this.positionY = positionY;
    if(arrayCardinals.toString().contains(cardinal)) {
      this.cardinalPosition = arrayCardinals.indexOf(cardinal);
    } else {
      throw new CardinalException("Invalid Cardinal " + cardinal);
    }
  }

  @Override
  public void commandRover(String command) throws CommandException {
    String[] commands = command.split("");
    for(String action: commands){
      if(action.equals(Commands.LEFT.toString()) || action.equals(Commands.RIGHT.toString())){
        setRoverCardinalDirection(action);
      } else if(action.equals(Commands.MOVE.toString())) {
        moveRover();
      } else {
        throw new CommandException("Invalid Command: " + action);
      }
    }
  }

  @Override
  public void moveRover(){
    switch (this.cardinalPosition) {
      case 0:
        this.setPositionY(++this.positionY);
        break;
      case 1:
        this.setPositionX(++this.positionX);
        break;
      case 2:
        this.setPositionY(--this.positionY);
        break;
      case 3:
        this.setPositionX(--this.positionX);
        break;
      default:
        break;
    }
  }
  @Override
  public String getPosition(){
    if(this.platformX < this.positionX || this.platformY < this.positionY){
      return "Rover not landed";
    }
    return String.format("%d %d %s", this.positionX, this.positionY, arrayCardinals.get(this.cardinalPosition));
  }

  private void setPositionX(int x) {
    this.positionX = x;
  }

  private void setPositionY(int y) {
    this.positionY = y;
  }

  private void setCardinalPosition(int position) {
    this.cardinalPosition = position;
  }

  private void setRoverCardinalDirection(String cardinalDirection) {
    if(cardinalDirection.equals(Commands.LEFT.toString())) {
      if(this.cardinalPosition == 0){
        setCardinalPosition(3);
      } else {
        setCardinalPosition(--this.cardinalPosition);
      }
    } else if (cardinalDirection.equals(Commands.RIGHT.toString())) {
      if(this.cardinalPosition == 3){
        setCardinalPosition(0);
      } else {
        setCardinalPosition(++this.cardinalPosition);
      }
    }
  }
}
