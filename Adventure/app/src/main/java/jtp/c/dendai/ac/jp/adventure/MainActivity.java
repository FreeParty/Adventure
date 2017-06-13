package jtp.c.dendai.ac.jp.adventure;
import jtp.c.dendai.ac.jp.adventure.scene.AbstractScene;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbstractScene.setActivity(this);
        AbstractScene.setHandler(new Handler());
        setContentView(R.layout.title);
        game = new Game(this);
        game.start(0);
    }
}
