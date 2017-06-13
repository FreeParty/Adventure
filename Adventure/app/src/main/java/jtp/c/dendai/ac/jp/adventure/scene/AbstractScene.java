package jtp.c.dendai.ac.jp.adventure.scene;
import jtp.c.dendai.ac.jp.adventure.Handler;
import jtp.c.dendai.ac.jp.adventure.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.SharedPreferences.*;

public abstract class AbstractScene implements Scene {
    private static android.os.Handler osHandler;
    public static void setHandler(android.os.Handler _osHandler){osHandler = _osHandler;}
    private static String log;
    private static boolean isVisible = false;
    private static boolean isAuto = false;
    private boolean release;
    private MediaPlayer mp;
    private static SoundPool pool;
    private static int sound;

    private int index;
    private Handler handler;
    private static Activity activity;
    protected int size(){
        return getMessage().length;
    };
    public static void setActivity(Activity _activity){
        activity = _activity;
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        sound = pool.load(activity, R.raw.prompt,1);
    }
    @Override
    public void onClick(View v){
        ToggleButton autoButton = ((ToggleButton)activity.findViewById(R.id.autoButton));
        isAuto = false;
        autoButton.setChecked(isAuto);

        TextView textView = (TextView) activity.findViewById(R.id.textarea);
        String[] item = getMessage()[index].split(":",2);
        String message = item[item.length - 1];

        if(textView.getText().length() < message.length())  textView.setText(message);
        else nextPage();
    }
    private void askQuestion() {
        Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.question_title));
        builder.setItems(getQuestion(),new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int witch){
                log += "\n\t⇒ " + getQuestion()[witch] + "\n";
                handler.step(next(witch).getScene());
                mp.stop();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void start(Handler h,int _index){
        handler = h;
        index= _index;
        activity.setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
        imageView.setOnClickListener(this);
        imageView.setImageResource(getImageId());

        imageView.setScaleType(ScaleType.FIT_XY);

        final TextView logView = ((TextView)activity.findViewById(R.id.log));
        logView.setMovementMethod(ScrollingMovementMethod.getInstance());
        if(isVisible) logView.setVisibility(View.VISIBLE);
        else logView.setVisibility(View.INVISIBLE);

        ToggleButton logButton = ((ToggleButton)activity.findViewById(R.id.logButton));
        logButton.setChecked(isVisible);
        logButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isVisible = isChecked;
                if(isVisible) logView.setVisibility(View.VISIBLE);
                else logView.setVisibility(View.INVISIBLE);
            }
        });

        final TextView textView = ((TextView)activity.findViewById(R.id.textarea));
        ToggleButton autoButton = ((ToggleButton)activity.findViewById(R.id.autoButton));
        autoButton.setChecked(isAuto);
        autoButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAuto = isChecked;
                String[] item = getMessage()[index].split(":",2);
                String message = item[item.length - 1];
                if (textView.getText().length() == message.length()) nextPage();
            }
        });

        (activity.findViewById(R.id.exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.step(null);
                mp.stop();
                mp.release();
                mp = null;
                release = true;
            }
        });

        (activity.findViewById(R.id.qsave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefer = activity.getPreferences(activity.MODE_PRIVATE);
                Editor editor = prefer.edit();
                String className = new Object(){}.getClass().getEnclosingClass().getName();
                editor.putString("qsave",getSceneName() + ":" + index);
                editor.commit();
                Toast.makeText(activity, activity.getString(R.string.qsavecomplete),Toast.LENGTH_SHORT).show();
            }
        });
        release = false;
        writeMessage();
        writeDate();
        writeName();
        wirteLog();
        mp = MediaPlayer.create(activity, getMusicId());
        mp.setLooping(true);
        mp.start();
    }

    public static void setLog(String text){
        log = text;
    }

    public static void setIsAuto(Boolean bool){
        isAuto = bool;
    }

    public static void setIsVisible(Boolean bool){
        isVisible = bool;
    }

    private void nextPage() {
        log += getMessage()[index] + "\n";
        wirteLog();
        index++;
        if(index < size()) {
            writeName();
            writeMessage();
        }
        else {
            if (getQuestionId() != 0) {
                ImageView imageView = (ImageView) activity.findViewById(R.id.imageView1);
                imageView.setOnClickListener(null);
                askQuestion();
            } else {
                GameState n = next(0);
                Scene scene = n == null ? null : n.getScene();
                handler.step(scene);
                mp.stop();
                mp.release();
                mp = null;
            }
        }
    }

    private void  wirteLog(){
        TextView logView = (TextView) activity.findViewById(R.id.log);
        logView.setText(log);
    }

    private void writeMessage() {
        final TextView textView = (TextView) activity.findViewById(R.id.textarea);
        textView.setText(null);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(release || index == size()) return;
                String[] item = getMessage()[index].split(":",2);
                String message = item[item.length - 1];
                if (textView.getText().length() < message.length()){
                    textView.setText(message.substring(0, textView.getText().length() + 1));
                    pool.play(sound,0.5f,0.5f,0,0,1);
                    osHandler.postDelayed(this,100);
                }else if(isAuto) {
                    osHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextPage();
                        }
                    },2000);
                }
            }
        };
        osHandler.post(runnable);
    }

    private void writeName(){
        TextView nameView = (TextView) activity.findViewById(R.id.name);
        String[] item = getMessage()[index].split(":",2);
        if(item.length == 2) nameView.setText(item[0]);
        else nameView.setText(null);
    }

    private void writeDate(){
        TextView textdate = (TextView) activity.findViewById(R.id.textdate);
        textdate.setText(activity.getResources().getString(getDateId()));
        log += "\n" + activity.getResources().getString(getDateId()) + "\n\n";
    }

    protected String[] getMessage() {
        return getStringArrayById(getMessageId());
    }
    protected String[] getQuestion() {
        return getStringArrayById(getQuestionId());
    }
    protected String[] getStringArrayById(int id) {
        return activity.getResources().getStringArray(id);
    }
}