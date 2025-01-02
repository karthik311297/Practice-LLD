package lld.bowling;

import java.util.Arrays;

public class BowlDemo {
    /*
    Rules of the Game

    The game can be played by multiple players.

    Primary goal is to knock off all the pins to gain scores. The game consist of ten frames. In each frame the player has two chances to knock off the pins.

    If the bowler is able to knock down all the pins in one strike, it is called a strike. If the bowler does it with two it is called a spare.

    If there is a spare, the player gets 5 bonus points. For strike, the player gets 10 bonus points.

    In the final set, a player who rolls a spare or a strike is allowed to roll the extra balls to complete the set. However, only a maximum of three balls can be rolled in the final set.

    During the game, players and their scores will be maintained and shown by the system and winner will be declared at the end of the game.

    Likewise, multiple games can be played in parallel on multiple free lanes.

--------------------------------

    Player

    Game
        id
        Scorecard
        Players[]
        Scorecard
        currentFrame = 0
        roll(currentPlayer)


    Frame
        standingPins
        score
        numRollsRemaining

    Scorecard
        Map<Player, Frame[10]>

    GameConfig
        STANDING_PINS = 30
        ROLLS PER FRAME = 2
     */

    public static void main(String[] args) {
        Game game = new Game(1, Arrays.asList(new Player("p1"), new Player("p2"), new Player("p3")), new GameConfig(30, 2, 10));
        game.play();
        game.displayWinner();
    }
}
