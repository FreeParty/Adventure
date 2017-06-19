package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class FreY5 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.ending_b;
    }
    @Override
    public int getMessageId() {
        return R.array.message_freY5;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.dateending;
    }
    @Override
    public int getMusicId() { return R.raw.piano22;}
    @Override
    public String getSceneName() { return "FreY5";}
}