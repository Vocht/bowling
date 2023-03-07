import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame("00");
    }

    @Test
    void getThrow() {
        var throw1 = frame.getThrow(0);
        var throw2 = frame.getThrow(1);
        var throw3 = frame.getThrow(2);

        assertEquals(throw1, 0);
        assertEquals(throw2, 0);
        assertEquals(throw3, 0);
    }

    @Test
    void setThrow() {
        frame.setThrow(0, 1);
        frame.setThrow(1, 1);
        frame.setThrow(2, 1);

        var throw1 = frame.getThrow(0);
        var throw2 = frame.getThrow(1);
        var throw3 = frame.getThrow(2);

        assertEquals(throw1, 1);
        assertEquals(throw2, 1);
        assertEquals(throw3, 1);
    }

    @Test
    void testIsStrike() {
        frame.setThrow(0, 10);
        assertTrue(frame.isStrike());

        frame.setThrow(0, 9);
        assertFalse(frame.isStrike());
    }

    @Test
    void testIsSpare() {
        frame.setThrow(0, 9);
        assertFalse(frame.isSpare());

        frame.setThrow(0, 1);
        assertTrue(frame.isSpare());
    }

    @Test
    void testInitWithFrameData() {
        frame = new Frame("11");

        var throw1 = frame.getThrow(0);
        var throw2 = frame.getThrow(1);
        var throw3 = frame.getThrow(2);

        assertEquals(throw1, 1);
        assertEquals(throw2, 1);
        assertEquals(throw3, 0);
    }

    @Test
    void testInitWithStrikeData() {
        frame = new Frame("XX");

        var throw1 = frame.getThrow(0);
        var throw2 = frame.getThrow(1);
        var throw3 = frame.getThrow(2);

        assertEquals(throw1, 10);
        assertEquals(throw2, 10);
        assertEquals(throw3, 0);
        assertTrue(frame.isStrike());
    }

    @Test
    void testInitWithSpareData() {
        frame = new Frame("1/");

        var throw1 = frame.getThrow(0);
        var throw2 = frame.getThrow(1);
        var throw3 = frame.getThrow(2);

        assertEquals(throw1, 1);
        assertEquals(throw2, 9);
        assertEquals(throw3, 0);
        assertTrue(frame.isSpare());
        assertFalse(frame.isStrike());
    }
}