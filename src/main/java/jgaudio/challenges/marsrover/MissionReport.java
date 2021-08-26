package jgaudio.challenges.marsrover;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class MissionReport {

  private final boolean success;
  private final Map<String, Position> finalPositions;

  public MissionReport(boolean success,
      Map<String, Position> finalPositions) {
    this.success = success;
    this.finalPositions = Collections.unmodifiableMap(finalPositions);
  }

  public boolean isSuccess() {
    return this.success;
  }

  public Optional<Position> getFinalPosition(String roverId) {
    return Optional.ofNullable(this.finalPositions.get(roverId));
  }
}
