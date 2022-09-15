import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MarsRoverAssignment {

    static final String cardinals = "NESW";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MarsRoverAssignment marsRoverAssignment = new MarsRoverAssignment();
        System.out.println("Enter Plateau bounds");
        String bounds = reader.readLine().trim();
        if (bounds.isEmpty())
            return;
        String[] boundsArr = bounds.split(" ");
        if(boundsArr.length != 2)
            throw new IllegalArgumentException("Invalid Bounds (Expects in form <r c> e.g 5 5).");
        int rowBound = Integer.parseInt(boundsArr[0]);
        int columnBound = Integer.parseInt(boundsArr[1]);

        //Since we are not supplying number of rovers program will run till the program is terminated.
        while (true) {
            System.out.println("Enter Current Position of Rover.");
            String currentPosition = reader.readLine().trim();
            if (currentPosition.isEmpty())
                return;

            String[] posArr = currentPosition.split(" ");
            int x = Integer.parseInt(posArr[0]);
            int y = Integer.parseInt(posArr[1]);
            char cardinal = Character.toUpperCase(posArr[2].charAt(0));
            if(posArr.length != 3)
                throw new IllegalArgumentException("Invalid Position (Expects in form <x y cardinal> e.g 1 2 N).");
            if(cardinals.indexOf(cardinal) < 0)
                throw new IllegalArgumentException("Invalid Cardinal Expects N or S or E or W e.g N.");

            Rover rover = new Rover(x, y, cardinal);

            System.out.println("Enter instructions for rover.");
            String instructions = reader.readLine().trim();

            String result = marsRoverAssignment.printFinalPositionOfRover(instructions, rover, rowBound, columnBound);
            System.out.println(result);
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
