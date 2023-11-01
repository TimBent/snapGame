# snapGame

ScalaTestSnap is a card game simulation written in Scala. The game involves multiple players and decks, with the ability to customize the rules for matching cards.

## Features

- Define a card and a player
- Create a deck of cards
- Shuffle the deck
- Define the players
- Deal cards to players
- Determine if two cards match based on a given rule
- Play turns and continue the game until a player has no cards left
- Decide the winner based on the player with the most points

## Usage

To run the game, execute the `main` function in `ScalaTestSnap`. You will be prompted to enter the number of players, number of decks. You will also be prompted to enter the rule for matching cards of decks (c/s/v):

c: cards match in color
s: cards match in suit
v: cards match in value

To play the game, simply run the following command:

`scala ScalaTestSnap.scala`

## Code Structure

The code is organized into several functions, each responsible for a specific part of the game:

- `createDeck`: Creates a deck of cards.
- `createPlayers`: Defines the players.
- `dealCards`: Deals cards to players.
- `isSnap`: Determines if two cards match based on a given rule.
- `playTurns`: Plays turns for each player.
- `continueGame`: Continues the game until a player has no cards left.
- `decidingWinner`: Decides the winner based on the player with the most points.
- `playGame`: Simulates the game.

## Future Work

The script is planned to be updated using Cats library for more functional programming paradigms.

## Contributing

Contributions are welcome. Please feel free to submit a pull request.

## License

This project is licensed under the terms of the MIT license. 

Please note that this is just an example README file. You may want to add more sections such as Installation, Requirements, Testing, etc., depending on your project's needs. Also, remember to replace "ScalaTestSnap" with your actual project name if it's different.
