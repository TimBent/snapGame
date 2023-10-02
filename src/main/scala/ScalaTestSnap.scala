import scala.io.StdIn._
import scala.util.Random

// Define a card
case class Card(suit: String, value: String, colour: String)

// Define a player
case class Player(name: String, var hand: List[Card] = Nil, var score : Int = 0)

object ScalaTestSnap {
  def main(args: Array[String]): Unit = {
    // Ask the user for the number of players and decks
    print("Enter the number of players: ")
    val numberOfPlayers = readInt()
    print("Enter the number of decks: ")
    val numberOfDecks : Int = readInt()
    print("Enter the rule for matching cards of decks (c/s/v): ")
    val snapOn = readLine()

    // Define a deck of cards
    def createDeck( numberOfDecks : Int ): List[Card] = {
      val suits = List(("Spades", "Black"), ("Hearts", "Red"), ("Clubs", "Black"), ("Diamonds", "Red"))
      val values = List("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")
      val deck = for {
        _ <- 1 to numberOfDecks
        suit <- suits
        value <- values
      } yield Card(suit._1, value, suit._2)
      deck.toList
    }

    // Shuffle the deck
    val shuffledDeck = Random.shuffle(createDeck(numberOfDecks))

    // Define the players
   def createPlayers( numberOfPlayers : Int ) = {
     (1 to numberOfPlayers).map(i => Player(s"Player $i")).toList
   }

    def dealCards(deck: List[Card], players: List[Player]): Unit = {
      players.zipWithIndex.foreach { case (player, i) =>
        player.hand = deck.slice(i * (deck.size / players.size), (i + 1) * (deck.size / players.size)).toList
      }
    }

    def isSnap( card1: Card, card2: Card, snapOn : String ): Boolean = {
        snapOn match {
          case "c" => card1.colour == card2.colour
          case "v" => card1.value == card2.value
          case "s" => card1.suit == card2.suit
        }
    }

    def playTurns( player : Player, remainingPlayers : List[Player], pile: List[Card] = Nil, snapOn : String ): Unit = {
      val card :: remainingHand = player.hand
      player.hand = remainingHand
      println(s"${player.name} plays: $card")
      if (isSnap(card, pile.headOption.getOrElse(Card("", "", "")), snapOn)) {
        println(s"${player.name} says: Snap!")
        player.score = player.score + 1
        player.hand = player.hand ++ pile
        playGame(remainingPlayers :+ player, snapOn)
      } else {
        playGame(remainingPlayers :+ player, snapOn, card :: pile)
      }
    }

    def continueGame( player : List[Player], snapOn : String, pile : List[Card]): Unit = {
      player match {
        case Nil => println("The Game has ended")
        case player :: remainingPlayers if player.hand.nonEmpty =>
          playTurns(player, remainingPlayers, pile, snapOn)
        case _ :: remainingPlayers =>
          playGame(remainingPlayers, snapOn, pile)
      }
    }

    def decidingWinner(players: List[Player]): Unit = {
      val winner = players.maxBy(_.hand.size)
      println(s"The winner is ${winner.name} with ${winner.score} points!")
    }

    // Simulate the game
    def playGame(players: List[Player], snapOn : String, pile: List[Card] = Nil): Unit =
      players.find( _.hand.isEmpty ) match {
        case Some(_) => decidingWinner(players)
        case None => continueGame(players, snapOn, pile)

    }

    val players = createPlayers(numberOfPlayers)
    val pile = shuffledDeck
    dealCards( pile, players)
    // Start the game
    playGame(players, snapOn, pile)

  }
}

