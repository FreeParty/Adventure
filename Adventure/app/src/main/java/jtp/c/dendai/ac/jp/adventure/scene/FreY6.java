package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class FreY6 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return GameState.deadend;
    }
    @Override
    public int getImageId() {
        return R.drawable.pachi;
    }
    @Override
    public int getMessageId() {
        return R.array.message_freY6;
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
    public int getMusicId() { return R.raw.casino01;}
    @Override
    public String getSceneName() { return "FreY6";}
}