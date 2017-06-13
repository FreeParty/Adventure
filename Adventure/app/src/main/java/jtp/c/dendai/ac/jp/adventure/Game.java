package jtp.c.dendai.ac.jp.adventure;
import jtp.c.dendai.ac.jp.adventure.scene.AbstractScene;
import jtp.c.dendai.ac.jp.adventure.scene.GameState;
import jtp.c.dendai.ac.jp.adventure.scene.Scene;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class Game implements Handler {
    private AppCompatActivity activity;
    private Title title;
    private Scene scene;
    private SoundPool pool;
    private int sound;
    public Game(AppCompatActivity mainActivity) {
        this.activity = mainActivity;
        title = new Title();
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = pool.load(activity, R.raw.start,1);
    }
    @Override
    public void step(Scene s) {
        scene = s;
        start(0);
    }
    public void start(int index) {
        if(scene==null) {
            AbstractScene.setLog("");
            AbstractScene.setIsVisible(false);
            AbstractScene.setIsAuto(false);
            activity.setContentView(title.getContentView());
            title.init(activity,new OnStartButtonClickListener(true), new OnStartButtonClickListener(false));
        }else{
            activity.setContentView(R.layout.activity_main);
            scene.start(this,index);
        }
    }
    class OnStartButtonClickListener implements OnClickListener {
        private boolean intialize;
        public OnStartButtonClickListener(boolean b) {
            intialize = b;
        }
        @Override
        public void onClick(View v) {
            if(intialize || scene==null){
                scene=GameState.getInitialScene();
            }
            switch (v.getId()){
                case R.id.startbutton:
                    start(0);
                    break;
                case R.id.continuebutton:
                    SharedPreferences prefer = activity.getPreferences(activity.MODE_PRIVATE);
                    String qsave = prefer.getString("qsave",null);
                    int index = 0;
                    if(qsave != null){
                        String[] data = qsave.split(":");
                        switch (data[0]){
                            case "First": scene = GameState.first == null ? null : GameState.first.getScene();
                                break;
                            case "Second": scene = GameState.first == null ? null : GameState.second.getScene();
                                break;
                            case "BadEnd": scene = GameState.first == null ? null : GameState.badend.getScene();
                                break;
                            case "Ending": scene = GameState.first == null ? null : GameState.ending.getScene();
                                break;
                        }
                        index = Integer.parseInt(data[1]);
                    }
                    start(index);
                    break;
            }
            pool.play(sound,1,1,0,0,1);
        }
    }
}