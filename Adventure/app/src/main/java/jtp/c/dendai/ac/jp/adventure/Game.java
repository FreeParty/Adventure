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

                            case "Ben1": scene = GameState.first == null ? null : GameState.ben1.getScene();
                                break;
                            case "Ben2": scene = GameState.first == null ? null : GameState.ben2.getScene();
                                break;
                            case "Ben3": scene = GameState.first == null ? null : GameState.ben3.getScene();
                                break;
                            case "Ben4": scene = GameState.first == null ? null : GameState.ben4.getScene();
                                break;

                            case "Fre1": scene = GameState.first == null ? null : GameState.fre1.getScene();
                                break;
                            case "FreX1": scene = GameState.first == null ? null : GameState.freX1.getScene();
                                break;
                            case "FreX2": scene = GameState.first == null ? null : GameState.freX2.getScene();
                                break;
                            case "FreY1": scene = GameState.first == null ? null : GameState.freY1.getScene();
                                break;
                            case "FreY2": scene = GameState.first == null ? null : GameState.freY2.getScene();
                                break;
                            case "FreY3": scene = GameState.first == null ? null : GameState.freY3.getScene();
                                break;
                            case "FreY4": scene = GameState.first == null ? null : GameState.freY4.getScene();
                                break;
                            case "FreY5": scene = GameState.first == null ? null : GameState.freY5.getScene();
                                break;
                            case "FreY6": scene = GameState.first == null ? null : GameState.freY6.getScene();
                                break;
                            case "FreZ1": scene = GameState.first == null ? null : GameState.freZ1.getScene();
                                break;
                            case "FreZ2": scene = GameState.first == null ? null : GameState.freZ2.getScene();
                                break;
                            case "FreZ3": scene = GameState.first == null ? null : GameState.freZ3.getScene();
                                break;

                            case "God1": scene = GameState.first == null ? null : GameState.god1.getScene();
                                break;
                            case "God2": scene = GameState.first == null ? null : GameState.god2.getScene();
                                break;
                            case "God3": scene = GameState.first == null ? null : GameState.god3.getScene();
                                break;
                            case "God4": scene = GameState.first == null ? null : GameState.god4.getScene();
                                break;
                            case "God5": scene = GameState.first == null ? null : GameState.god5.getScene();
                                break;

                            case "Ending": scene = GameState.first == null ? null : GameState.ending.getScene();
                                break;
                            case "BadEnd": scene = GameState.first == null ? null : GameState.badend.getScene();
                                break;
                            case "DeadEnd": scene = GameState.first == null ? null : GameState.deadend.getScene();
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