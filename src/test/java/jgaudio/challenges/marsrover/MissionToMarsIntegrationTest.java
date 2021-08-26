package jgaudio.challenges.marsrover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class MissionToMarsIntegrationTest {

  public static final String LINE_BREAK = System.getProperty("line.separator");

  @Test
  void testMoveSingleRover() {
    StringBuilder sb = new StringBuilder();
    sb.append("4 5"); // plateau size
    sb.append(LINE_BREAK);
    sb.append("0 0 N"); // first rover
    sb.append(LINE_BREAK);
    sb.append("RMMMLMM");

    SpecsParser parser = new StringSpecsParser();
    MissionSpecs specs = parser.parse(sb.toString());

    assertEquals(4, specs.getPlateauWidth());
    assertEquals(5, specs.getPlateauHeight());

    MarsRover firstRover = new MarsRover("rover-1", new Position(0, 0, Orientation.NORTH));
    List<MarsRover> expectedRovers = Collections.singletonList(firstRover);
    assertEquals(expectedRovers, specs.getRovers());
    assertEquals(specs.getCommands("rover-1"), Arrays.asList(RoverCommand.TURN_RIGHT,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.MOVE,
        RoverCommand.TURN_LEFT,
        RoverCommand.MOVE,
        RoverCommand.MOVE
    ));

    MissionController controller = new SimpleMissionController();
    MissionReport report = controller.executeMission(specs);

    assertTrue(report.isSuccess());

    Optional<Position> firstRoverFinalPosition = report.getFinalPosition("rover-1");
    assertTrue(firstRoverFinalPosition.isPresent());
    assertEquals(4, firstRoverFinalPosition.get().getCoordX());
    assertEquals(2, firstRoverFinalPosition.get().getCoordY());
    assertEquals(Orientation.NORTH, firstRoverFinalPosition.get().getOrientation());
  }

}
