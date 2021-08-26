package jgaudio.challenges.marsrover;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleMissionController implements MissionController {

  @Override
  public MissionReport executeMission(MissionSpecs specs) {
    final Map<String, Position> finalPositions = new HashMap<>();
    boolean success = true;

    for (MarsRover rover : specs.getRovers()) {
      List<RoverCommand> commands = specs.getCommands(rover.getId());
      Position currentPosition = rover.getInitialPosition();

      for (RoverCommand command : commands) {
        switch(command) {
          case MOVE:
            currentPosition = currentPosition.moveInDirection();
            break;
          case TURN_LEFT:
            currentPosition = currentPosition.turnLeft();
            break;
          default:
          case TURN_RIGHT:
            currentPosition = currentPosition.turnRight();
            break;
        }
      }

      finalPositions.put(rover.getId(), currentPosition);
    }

    return new MissionReport(success, finalPositions);
  }
}
