# Magical Arena

## Description
Magical Arena is a console-based game where players can compete against each other in a magical arena. Each player has attributes such as health, strength, and attack. Players can be added to the arena, and battles can be initiated between them.

## Features
- Add players with customizable attributes.
- Display all players currently in the arena.
- Initiate battles between players.
- Determine the winner based on player attributes and random dice rolls.


## Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your machine.
- Apache Maven

## How to Run the Project

### Step 1: Clone the Repository
If you haven't already, clone this repository to your local machine:
```bash
git clone <repository-url>
cd Ajeet-MagicalArena
```
### Step 2: Compile the Code
Navigate to the src/main/java directory and compile the Java files. You can do this using the command line:
```bash
cd src/main/java
javac org/example/*.java
```
### Step 3: Run the Main Class
After compiling, you can run the Main class to start the game:
```bash
java org.example.Main
```
### Interact with the game
Follow the on-screen prompts to add players, display players, and initiate battles. The game will guide you through each step.

### Running Tests 
You should have JUnit set up in your project to run tests and ensure that everything is functioning correctly. Go to the root of the Project and then run:
```bash
mvn test
```

### Dependencies
This project uses JUnit for testing. If you're using Maven, dependencies are managed in the pom.xml file.



