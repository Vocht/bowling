import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameTest {
    private BowlingGame bowlingGame;

    @Test
    void testInvalidGame1() {
        assertThrows(ParseException.class, () -> new BowlingGame("111 11 11 11 11 11 11 11 11 11"));
    }

    @Test
    void testInvalidGame2() {
        assertThrows(ParseException.class, () -> new BowlingGame("11 11 11 11 11 111 11 11 11 11"));
    }

    @Test
    void testInvalidGame3() {
        assertThrows(ParseException.class, () -> new BowlingGame("11 11 XX 11 11 111 11 11 11 11"));
    }

    @Test
    void testInvalidGame4() {
        assertThrows(ParseException.class, () -> new BowlingGame("/1 11 11 11 11 11 11 11 11 11"));
    }

    @Test
    void testInvalidGame5() {
        assertThrows(ParseException.class, () -> new BowlingGame("11 11 11 11 11 /1 11 11 11 11"));
    }

    @Test
    void testInvalidGame6() {
        assertThrows(ParseException.class, () -> new BowlingGame("11 11 11 11 11 11 11 11 11 11 11"));
    }

    @Test
    void testParseGame_givenNormalScore1() throws ParseException {
        bowlingGame = new BowlingGame("11 11 11 11 11 11 11 11 11 11");
        var score = bowlingGame.getScore();

        assertEquals(20, score);
    }

    @Test
    void testParseGame_givenNormalScore2() throws ParseException {
        bowlingGame = new BowlingGame("81 44 11 12 52 81 01 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(57, score);
    }

    @Test
    void testParseGame_givenOneStrike1() throws ParseException {
        bowlingGame = new BowlingGame("X 44 11 12 52 81 01 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(66, score);
    }

    @Test
    void testParseGame_givenSeveralStrikes() throws ParseException {
        bowlingGame = new BowlingGame("X X 11 12 52 X 01 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(75, score);
    }

    @Test
    void testParseGame_givenSeveralStrikes2() throws ParseException {
        bowlingGame = new BowlingGame("X X 11 12 X X X 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(123, score);
    }

    @Test
    void testParseGame_givenOneSpare() throws ParseException {
        bowlingGame = new BowlingGame("11 11 11 12 11 1/ 11 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(42, score);
    }

    @Test
    void testParseGame_givenSeveralSpares() throws ParseException {
        bowlingGame = new BowlingGame("11 11 11 1/ 11 1/ 11 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(50, score);
    }

    @Test
    void testParseGame_givenSeveralSpares2() throws ParseException {
        bowlingGame = new BowlingGame("1/ 11 1/ 12 11 1/ 11 23 51 43");
        var score = bowlingGame.getScore();

        assertEquals(60, score);
    }

    @Test
    void testParseGame_givenRandomScore() throws ParseException {
        bowlingGame = new BowlingGame("1/ 11 1/ 12 X 1/ X X 51 43");
        var score = bowlingGame.getScore();

        assertEquals(121, score);
    }

    @Test
    void testParseGame_givenRandomThreeStrikesAtEnd() throws ParseException {
        bowlingGame = new BowlingGame("1/ 11 1/ 12 X 1/ X X 51 XXX");
        var score = bowlingGame.getScore();

        assertEquals(144, score);
    }

    @Test
    void testParseGame_givenPerfectGame() throws ParseException {
        bowlingGame = new BowlingGame("X X X X X X X X X XXX");
        var score = bowlingGame.getScore();

        assertEquals(300, score);
    }

    @Test
    void testGetFrames() throws ParseException {
        bowlingGame = new BowlingGame("11 11 11 11 11 11 11 11 11 11");
        var frame = bowlingGame.getFrame(0);

        assertEquals(frame.getThrow(0), 1);
    }
}