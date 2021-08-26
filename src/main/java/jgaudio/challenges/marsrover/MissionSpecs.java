package jgaudio.challenges.marsrover;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MissionSpecs {

  private final int plateauWidth;
  private final int plateauHeight;
  private final List<MarsRover> rovers;
  private final Map<String, List<RoverCommand>> commands;

  public MissionSpecs(int plateauWidth, int plateauHeight, List<MarsRover> rovers,
      Map<String, List<RoverCommand>> commands) {
    this.plateauWidth = plateauWidth;
    this.plateauHeight = plateauHeight;
    this.rovers = Collections.unmodifiableList(rovers);
    this.commands = commands;
  }

  public int getPlateauWidth() {
    return this.plateauWidth;
  }

  public int getPlateauHeight() {
    return this.plateauHeight;
  }

  public List<MarsRover> getRovers() {
    return this.rovers;
  }

  public List<RoverCommand> getCommands(String roverId) {
    return this.commands.get(roverId);
  }
}
