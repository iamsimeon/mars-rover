import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MarsRoverAssignmentTest {

    private MarsRoverAssignment marsRoverAssignment;

    @BeforeEach
    void setUp() {
        marsRoverAssignment = new MarsRoverAssignment();
    }

    @Test
    void testPrintFinalPositionOfRoverShouldThrowOutOfBoundsOnInstructionsException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            marsRoverAssignment.printFinalPositionOfRover("M", new MarsRoverAssignment.Rover(0, 0, 'N'), 0, 0);
        });
    }

    @Test
    void testPrintFinalPositionOfRoverShouldThrowOutOfBoundsOnInitialPosException() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            marsRoverAssignment.printFinalPositionOfRover("M", new MarsRoverAssignment.Rover(1, 0, 'N'), 0, 0);
        });
    }

    @Test
    void testPrintFinalPositionOfRoverShouldThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            marsRoverAssignment.printFinalPositionOfRover("T", new MarsRoverAssignment.Rover(0, 0, 'N'), 0, 0);
        });
    }

    @Test
    void testPrintFinalPositionOfRoverScenarioOne() {
        String result = marsRoverAssignment.printFinalPositionOfRover("LMLMLMLMM", new MarsRoverAssignment.Rover(1, 2, 'N'), 5, 5);
        Assertions.assertEquals("1 3 N", result);
    }

    @Test
    void testPrintFinalPositionOfRoverScenarioTwo() {
        String result = marsRoverAssignment.printFinalPositionOfRover("MMRMMRMRRM", new MarsRoverAssignment.Rover(3, 3, 'E'), 5, 5);
        Assertions.assertEquals("5 1 E", result);
    }
}
