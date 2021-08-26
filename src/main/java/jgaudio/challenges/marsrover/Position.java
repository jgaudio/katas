package jgaudio.challenges.marsrover;

import java.util.Objects;

public class Position {

  private final int coordX;
  private final int coordY;
  private final Orientation orientation;

  public Position(int coordX, int coordY, Orientation orientation) {
    this.coordX = coordX;
    this.coordY = coordY;
    this.orientation = orientation;
  }

  public int getCoordX() {
    return this.coordX;
  }

  public int getCoordY() {
    return this.coordY;
  }

  public Orientation getOrientation() {
    return this.orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return coordX == position.coordX && coordY == position.coordY
        && orientation == position.orientation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(coordX, coordY, orientation);
  }

  @Override
  public String toString() {
    return "Position{" +
        "coordX=" + coordX +
        ", coordY=" + coordY +
        ", orientation=" + orientation +
        '}';
  }

  public Position moveInDirection() {
    switch (this.orientation) {
      case NORTH:
        return new Position(this.coordX, this.coordY + 1, this.orientation);
      case SOUTH:
        return new Position(this.coordX, this.coordY - 1, this.orientation);
      case EAST:
        return new Position(this.coordX + 1, this.coordY, this.orientation);
      default:
      case WEST:
        return new Position(this.coordX - 1, this.coordY, this.orientation);
    }
  }

  public Position turnLeft() {
    switch (this.orientation) {
      case EAST:
        return new Position(this.coordX, this.coordY, Orientation.NORTH);
      case WEST:
        return new Position(this.coordX, this.coordY, Orientation.SOUTH);
      case NORTH:
        return new Position(this.coordX, this.coordY, Orientation.WEST);
      default:
      case SOUTH:
        return new Position(this.coordX, this.coordY, Orientation.EAST);
    }
  }

  public Position turnRight() {
    switch (this.orientation) {
      case EAST:
        return new Position(this.coordX, this.coordY, Orientation.SOUTH);
      case WEST:
        return new Position(this.coordX, this.coordY, Orientation.NORTH);
      case NORTH:
        return new Position(this.coordX, this.coordY, Orientation.EAST);
      default:
      case SOUTH:
        return new Position(this.coordX, this.coordY, Orientation.WEST);
    }
  }
}
