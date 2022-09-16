import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MarsRoverAssignment {

    static final String cardinals = "NESW";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MarsRoverAssignment marsRoverAssignment = new MarsRoverAssignment();
        String strInputs;
        List<String> inputs = new ArrayList<>();
        while ((strInputs = reader.readLine()) != null && !strInputs.trim().isEmpty())
            inputs.add(strInputs);
        int countOfInputs = inputs.size() - 1;
        if (countOfInputs >= 2 && countOfInputs % 2 == 0) {
            String bounds = inputs.get(0).trim();
            if (bounds.isEmpty())
                return;
            String[] boundsArr = bounds.split(" ");
            if (boundsArr.length != 2)
                throw new IllegalArgumentException("Invalid Bounds (Expects in form <r c> e.g 5 5).");
            int rowBound = Integer.parseInt(boundsArr[0]);
            int columnBound = Integer.parseInt(boundsArr[1]);

            List<String> resultsList = new ArrayList<>();
            int i = 1;
            while (i < countOfInputs) {
                String currentPosition = inputs.get(i++).trim();
                String instructions = inputs.get(i++).trim();

                if (currentPosition.isEmpty())
                    return;

                String[] posArr = currentPosition.split(" ");
                int x = Integer.parseInt(posArr[0]);
                int y = Integer.parseInt(posArr[1]);
                char cardinal = Character.toUpperCase(posArr[2].charAt(0));
                if (posArr.length != 3)
                    throw new IllegalArgumentException("Invalid Position (Expects in form <x y cardinal> e.g 1 2 N).");
                if (cardinals.indexOf(cardinal) < 0)
                    throw new IllegalArgumentException("Invalid Cardinal Expects N or S or E or W e.g N.");

                Rover rover = new Rover(x, y, cardinal);


                String result = marsRoverAssignment.printFinalPositionOfRover(instructions, rover, rowBound, columnBound);
                resultsList.add(result);
            }

            for (String result : resultsList)
                System.out.println(result);
        } else {
            throw new IllegalArgumentException("Invalid input.");
        }
    }

    static class Rover {
        int x;
        int y;
        char cardinal;

        public Rover(int x, int y, char cardinal) {
            this.x = x;
            this.y = y;
            this.cardinal = cardinal;
        }
    }

    protected String printFinalPositionOfRover(String str, Rover rover, int rowBound, int columnBound) {
        if (rover.x > rowBound || rover.y > columnBound || rover.x < 0 || rover.y < 0)
            throw new ArrayIndexOutOfBoundsException("Out of Bounds of Plateau.");
        for (char instructions : str.toCharArray()) {
            if (instructions == 'M') {
                // For N and S move the y axis a unit.
                // For W and E move the x axis a unit.
                if (rover.cardinal == 'N' && rover.y + 1 <= rowBound)
                    rover.y += 1;
                else if (rover.cardinal == 'S' && rover.y - 1 >= 0)
                    rover.y -= 1;
                else if (rover.cardinal == 'W' && rover.x - 1 >= 0)
                    rover.x -= 1;
                else if (rover.cardinal == 'E' && rover.x + 1 <= columnBound)
                    rover.x += 1;
                else
                    throw new ArrayIndexOutOfBoundsException("Out of Bounds of Plateau.");
            } else if (instructions == 'L' || instructions == 'R') {
                // Get the cardinal at the L or R of the current cardinal.
                int result;
                int cardinalsLen = cardinals.length();
                if (instructions == 'L') {
                    result = cardinals.indexOf(rover.cardinal) - 1;
                    if (result == -1) //if < 0 pick last cardinal (behaves like a circular array).
                        result = cardinalsLen - 1;
                } else {
                    result = cardinals.indexOf(rover.cardinal) + 1;
                    if (result >= cardinalsLen) //if > cardinals.length() pick first cardinal (behaves like a circular array).
                        result = 0;
                }
                rover.cardinal = cardinals.charAt(result);
            } else
                throw new IllegalArgumentException("Invalid Instruction.");
        }
        return String.format("%s %s %s", rover.x, rover.y, rover.cardinal);
    }
}
