package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class God3 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.deadend;
            case 1:
                return GameState.god4;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.god02;
    }
    @Override
    public int getMessageId() {
        return R.array.message_god3;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_god3;
    }
    @Override
    public int getDateId() {
        return R.string.date2;
    }
    @Override
    public int getMusicId() { return R.raw.orchestra23;}
    @Override
    public String getSceneName() { return "God3";}
}