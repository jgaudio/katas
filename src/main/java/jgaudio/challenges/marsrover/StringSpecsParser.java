package jgaudio.challenges.marsrover;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSpecsParser implements SpecsParser {

  @Override
  public MissionSpecs parse(String instructions) {
    // TODO Implement proper parse logic
    ;
    MarsRover firstRover = new MarsRover("rover-1", new Position(0, 0, Orientation.NORTH));
    List<MarsRover> rovers = Collections.singletonList(firstRover);

    Map<String, List<RoverCommand>> commands = new HashMap<>();
    commands.put(firstRover.getId(),Arrays.asList(RoverCommand.TURN_RIGHT,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.TURN_LEFT,
        RoverCommand.MOVE,
        RoverCommand.MOVE
    ));

    return new MissionSpecs(4, 5, rovers, commands);
  }
}
