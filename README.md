# AP_iRobot
RMIT Uni Assignment
/* notes from fush */

User Manual

About
This program enables the user to simulate games and predict the winners of games that have been preloaded.
There are 3 types of sports: Cycling, Sprinting, Swimming

Using the menu:
The user has 6 options
1. select a game
2. predict a winner
3. run a game
4. display results for all games
5. display points for all users
6. close the program

The game workflow is as follows:
1. the user has to first pick a game
2. the user can predict a winner for a game, if a game has not been chosen, the program will prompt the user to choose a game
3. the user can run the selected game, if a game has not been chosen, the program will prompt the user to choose a game
   if a prediction has not been made, the program will prompt the user to make a prediction
   if the game has fewer than 4 players, the game will not run
4. if the athlete with the lowest time matches the prediction, the total number of successful predictions will go up by one

Notes:
The prediction is reset after the game is run because the workflow doesnt permit the condition that the prediction is reset if the game is rerun
Instead, it will be reset after every run so that there is no prediction when it is chosen again
