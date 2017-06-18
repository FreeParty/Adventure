package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class FreZ3 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return GameState.deadend;
    }
    @Override
    public int getImageId() {
        return R.drawable.second;
    }
    @Override
    public int getMessageId() {
        return R.array.message_freZ3;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date6;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "FreZ3";}
}