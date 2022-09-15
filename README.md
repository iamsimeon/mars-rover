# MarsRoverAssignment

## Prerequisite
- Java jdk 11
- Maven 3.6.* and above

## Build
On the root of the repository run

````shell
mvn package
````

## Execute
````shell
java -jar <path-to-jar>/MarsRoverAssignment-1.0-SNAPSHOT.jar
````

## How to use
1. When program is executed with above command The following prompts that will follow.
   1. `Enter Plateau bounds` enter the Plateau bounds with space in the middle i.e 5 5
   2. `Enter Current Position of Rover` enter position of rover i.e 1 2 N (space separated)
   3. `Enter instructions for rover.` enter the series of L,R,M characters.
2. The program will then output the final position of rover.
3. The program will begin step 1 from sub step 2.

Terminate the program when done (otherwise it will await inputs).
