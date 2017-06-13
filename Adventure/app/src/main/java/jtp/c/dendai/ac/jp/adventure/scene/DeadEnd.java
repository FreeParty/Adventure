package jtp.c.dendai.ac.jp.adventure.scene;

import jtp.c.dendai.ac.jp.adventure.R;

public class DeadEnd extends AbstractScene {
    @Override
    public GameState next(int no) {
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.why;
    }
    @Override
    public int getDateId(){
        return R.string.datebadend;
    }
    @Override
    public int getMessageId() {
        return R.array.messagedeadend;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getMusicId() { return R.raw.badend;}
    @Override
    public String getSceneName() { return "BadEnd";}
}
