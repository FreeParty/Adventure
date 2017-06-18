package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class Ben1 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.ben2;
            case 1:
                return GameState.ben3;
            case 2:
                return GameState.ben4;
            case 3:
                return GameState.deadend;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.second;
    }
    @Override
    public int getMessageId() {
        return R.array.message_ben1;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_ben1;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "Ben1";}
}