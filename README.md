# Monty-hall problem simulation

Java code that simulates the game described in Monty-hall paradox: [Monty-hall paradox](https://en.wikipedia.org/wiki/Monty_Hall_problem)

*source: Wikipedia*
> Suppose you're on a game show, and you're given the choice of three doors: Behind one door is a car; behind the others, goats. You pick a door, say No. 1, and the host, who knows what's behind the doors, opens another door, say No. 3, which has a goat. He then says to you, "Do you want to pick door No. 2?" Is it to your advantage to switch your choice?


# Requirements
*	Java 11
*	Maven
*   Spring Boot
*	Junit 5

# Run

- Clone the project, navigate to the project root and use Maven to build:
```
mvn clean install
```
- With running the test ```simulate_the_game_with_100_times()``` in ```GameServiceImplTest```
  you can see the result of 100 times running the application.
- Or you can run the application by going to: 
  http://localhost:5000/


# Example outcome

Running the test for 1 million times:

![img_1.png](img_1.png)
