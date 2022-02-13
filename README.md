# Menu Ordering System

##Project
An ordering system for text based input.  Results are output to console.  Typical input follows the pattern:

    <MENU NAME> <id>, <id>, ... <id>

Example input:

    Breakfast 1, 2, 3

##Build
This project is built with the Java 1.8 JVM using JUnit 5.7.1.

Run the following commands to build and run this from your repository:

    mvn clean
    mvn compile
    mvn package 

##Run
After the build steps are complete, the file `menu-ordering-system-1.0-SNAPSHOT.jar` will be written in the `projectroot/target` directory.

###Run with Command Line arguments
To execute the jar, run the following from the project root:

    java -jar ./target/menu-ordering-system-1.0-SNAPSHOT.jar "Breakfast 1, 2, 3, 3" "Lunch 1, 2, 3"

Output:

    Eggs, Toast, Coffee(2)
    Sandwich, Chips, Soda

###Run Interactively

Use the `-i` flag to run this jar interactively.  `Ctrl+C` or `exit` to exit out.

    java -jar ./target/menu-ordering-system-1.0-SNAPSHOT.jar -i

        


##Test

Maven tests can be run from the project folder with the following:

    mvn test
