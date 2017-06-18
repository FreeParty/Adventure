package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.R;
import java.util.Random;

public class FreY1 extends AbstractScene {
    @Override
    public GameState next(int no) {
        Random ran = new Random();
        int n = ran.nextInt(3);
        switch(n){
            case 0:
                return GameState.freY2;
            case 1:
                return GameState.freY3;
            case 2:
                return GameState.freY4;
        }
        return null;
    }
    @Override
    public int getImageId() {
        return R.drawable.first;
    }
    @Override
    public int getMessageId() {
        return R.array.message_freY1;
    }
    @Override
    public int getQuestionId() {
        return 0;
    }
    @Override
    public int getDateId() {
        return R.string.date3;
    }
    @Override
    public int getMusicId() { return R.raw.daily;}
    @Override
    public String getSceneName() { return "FreY1";}
}