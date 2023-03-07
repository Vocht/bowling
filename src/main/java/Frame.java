
import java.util.ArrayList;

public class Frame {
    private final ArrayList<Integer> bowlingThrows = new ArrayList<>();

    public Frame(String frame) {
        parseFrame(frame);
    }

    private void parseFrame(String frame) {
        var frameThrows = frame.split("");
        for (int i = 0; i < frameThrows.length; i++) {
            bowlingThrows.add(i,
                    switch (frameThrows[i]) {
                        case "X" -> 10;
                        case "/" -> 10 - bowlingThrows.get(i - 1);
                        default -> Integer.parseInt(frameThrows[i]);
                    });
        }
    }

    public int getThrow(int i) {
        try {
            return bowlingThrows.get(i);
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public void setThrow(int i, int score) {
        bowlingThrows.add(i, score);
    }

    public boolean isStrike() {
        return bowlingThrows.get(0) == 10;
    }

    public boolean isSpare() {
        return getThrow(0) + getThrow(1) == 10;
    }

    public int getScore() {
        return bowlingThrows.stream().mapToInt(Integer::intValue).sum();
    }
}
