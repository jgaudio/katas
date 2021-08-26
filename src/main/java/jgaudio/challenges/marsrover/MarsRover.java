package jgaudio.challenges.marsrover;

import java.util.Objects;

public class MarsRover {

  private final String id;
  private final Position initialPositon;
  public MarsRover(String id, Position position) {
    this.id = id;
    this.initialPositon = position;
  }

  public String getId() {
    return this.id;
  }

  public Position getInitialPosition() {
    return initialPositon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MarsRover marsRover = (MarsRover) o;
    return id.equals(marsRover.id) && initialPositon.equals(marsRover.initialPositon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, initialPositon);
  }

  @Override
  public String toString() {
    return "MarsRover{" +
        "id='" + id + '\'' +
        ", initialPositon=" + initialPositon +
        '}';
  }
}

