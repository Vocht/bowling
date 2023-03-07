import java.text.ParseException;
import java.util.ArrayList;

public class BowlingGame {
    private final ArrayList<Frame> frames = new ArrayList<>();

    public BowlingGame(String gameString) throws ParseException {
        var splitFrames = gameString.split(" ");
        if (splitFrames.length > 10)
            throw new ParseException("Invalid Game: Too many frames", 0);

        for (int i = 0; i < splitFrames.length; i++) {
            String f = splitFrames[i];

            if (f.startsWith("/"))
                throw new ParseException("Invalid Game: Spare on first throw", 0);
            if (f.startsWith("X") && f.endsWith("X") && f.length() > 1  && i != splitFrames.length - 1)
                throw new ParseException("Invalid Game: Two strikes in a frame", 0);
            if (f.length() > 2 && i != splitFrames.length - 1)
                throw new ParseException("Invalid Game: Three throws in a frame", 0);

            frames.add(new Frame(f));
        }
    }

    public int getScore() {
        int totalScore = 0;

        for(int i = 0; i < frames.size(); i++) {
            final var currentFrame = frames.get(i);
            totalScore += currentFrame.getScore();
            if (currentFrame.isStrike()) {
                if (i + 1 < frames.size()) {
                    final var nextFrame = frames.get(i + 1);
                    if (nextFrame.isStrike() && i + 2 < frames.size()) {
                        totalScore += nextFrame.getScore() + frames.get(i + 2).getThrow(0);
                    } else {
                        totalScore += nextFrame.getThrow(0) + nextFrame.getThrow(1);
                    }
                } else {
                    if (i + 1 < frames.size()) {
                        final var nextFrame = frames.get(i + 1);
                        totalScore += nextFrame.getThrow(0) + nextFrame.getThrow(1);
                    }
                }
            } else if (currentFrame.isSpare()) {
                if (i + 1 < frames.size()) {
                    totalScore += frames.get(i + 1).getThrow(0);
                }
            }
        }

        return totalScore;
    }

    public Frame getFrame(int i) {
        return frames.get(i);
    }
}
