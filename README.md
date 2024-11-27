# CMPT 276 Fall 2024 Group 10
## Members
- Andrew Kim
- Andrew Yu
- Darren Fok
- Daehyung Kwak

## Arcade Style 2D Game
### Timeline
1. Requirement Engineering and Design (~10.11.2024)

### How to Build, Run, and Test
**NOTE**: This game requires JDE 18+ to build and run. Please ensure that you have it installed before
attempting to build and run Grave Escape.
#### Building
Run `mvn clean package`. This will clean the `/target/` directory and compile the package into a .jar file.
#### Running
Enter `java -jar .\target\CMPT276F24_group10-1.0.jar` into the terminal. This will start the game.
#### Testing
Run `mvn test` to run all tests within the `src/test/java/` directory.
To read the test coverage, `.html` files for each class can be found in `/target/site/jacoco/`. Opening these
a browser of your choice will display the class' coverage report.
