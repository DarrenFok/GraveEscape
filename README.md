# CMPT 276 Fall 2024 Group 10
## Members
- Andrew Kim
- Andrew Yu
- Darren Fok
- Daehyung Kwak

## Grave Escape
Grave Escape is a Turn-Based Movement Puzzle game, where each move the player makes changes the layout of the level.
Players must strategize on how to collect all the keys on each level while avoiding ghosts and traps in order to make
it out to the next level.
### Timeline
1. Requirement Engineering and Design (~10.11.2024)
2. Game Implementation (~10.27.2024)
3. Game Improvement, Unit Test Writing, and Manual Testing (~11.22.2024)
4. Finalization and Presentation (~12.6.2024)

### How to Build, Run, and Test
**NOTE**: This game requires JDE 18+ to build and run. Please ensure that you have it installed before
attempting to build and run Grave Escape.
#### Building
In a terminal, navigate to the directory that the game is under (directory `CMPT276F24_group10`) and run `mvn clean package`. This will clean the `/target/` directory and compile the package into a .jar file.
#### Running
Enter `java -jar .\target\CMPT276F24_group10-1.0.jar` into the terminal. This will start the game.
#### Testing
Run `mvn test` to run all tests within the `src/test/java/` directory.
To read the test coverage, `.html` files for each class can be found in `/target/site/jacoco/`. Opening these
a browser of your choice will display the class' coverage report.
