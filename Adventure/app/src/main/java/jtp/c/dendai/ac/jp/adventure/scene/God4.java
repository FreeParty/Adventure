package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class God4 extends AbstractScene {
    @Override
    public GameState next(int no) {
        return GameState.ben1;
    }
    @Override
    public int getImageId() {
        return R.drawable.night;
    }
    @Override
    public int getMessageId() {
        return R.array.message_god4;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date2;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "God4";}
}