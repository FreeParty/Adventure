package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;

public class FreY3 extends AbstractScene {
    @Override
    public GameState next(int no) {
        switch(no){
            case 0:
                return GameState.freY6;
            case 1:
                return GameState.freY5;
            case 2:
                return GameState.deadend;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.pachi;
    }
    @Override
    public int getMessageId() {
        return R.array.message_freY3;
    }
    @Override
    public int getQuestionId() {
        return R.array.question_freY;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.casino01;}
    @Override
    public String getSceneName() { return "FreY3";}
}