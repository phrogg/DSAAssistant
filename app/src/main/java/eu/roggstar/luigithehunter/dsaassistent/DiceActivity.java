package eu.roggstar.luigithehunter.dsaassistent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    private Random rand;
    private TextView txt_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        this.setTitle(R.string.TitleDice);

        //Start connectors
        final Button but_d3 = findViewById(R.id.but_d3);
        final Button but_d4 = findViewById(R.id.but_d4);
        final Button but_d6 = findViewById(R.id.but_d6);
        final Button but_d8 = findViewById(R.id.but_d8);
        final Button but_d10 = findViewById(R.id.but_d10);
        final Button but_d1010 = findViewById(R.id.but_d1010);
        final Button but_d12 = findViewById(R.id.but_d12);
        final Button but_d20 = findViewById(R.id.but_d20);
        final Button but_d100 = findViewById(R.id.but_d100);
        txt_results = findViewById(R.id.txt_results);
        txt_results.setMovementMethod(new ScrollingMovementMethod());
        rand = new Random();

        but_d3.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) { fadeOutAndHideImage(but_d3,3,1); }});
        but_d4.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d4,4,1);  }});
        but_d6.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d6,6,1);  }});
        but_d8.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d8,8,1);  }});
        but_d10.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d10,10,1);  }});
        but_d1010.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d1010,1010,1);  }});
        but_d12.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d12,12,1);  }});
        but_d20.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d20,20,1);  }});
        but_d100.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d100,100,1);  }});


        //LongClickListener
        but_d3.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d3,3,3);
            return true;}});
        but_d4.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d20,4,3);
            return true;}});
        but_d6.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d6,6,3);
            return true;}});
        but_d8.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d8,8,3);
            return true;}});
        but_d10.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d10,10,3);
            return true;}});
        but_d1010.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d1010,100,3);
            return true;}});
        but_d12.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d12,12,3);
            return true;}});
        but_d20.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d20,20,3);
            return true;}});
        but_d100.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            fadeOutAndHideImage(but_d100,100,3);
            return true;}});

    }

    private void fadeInAndShowImage(final Button object, final int i, final int jclick)
    {
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(250+rand.nextInt(1000));

        fadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            @SuppressLint("SetTextI18n")
            public void onAnimationStart(Animation animation) {
                for (int j = 0; j < jclick; j++) {
                    rand.nextInt(10);
                    rand.nextInt(10);
                    rand.nextInt(10);
                    if (i == 1010) {
                        txt_results.setText("D10:\t" + (rand.nextInt(10) + 1) * 10 + "\n" + txt_results.getText());
                    } else {
                        txt_results.setText("D"+i+":\t" + (rand.nextInt(i) + 1) + "\n" + txt_results.getText());
                    }
                }
            }
        });
        //txt_results.post(new Runnable() { public void run() { txt_results.scrollTo(0,0); } });
        //txt_results.post(new Runnable() { public void run() { txt_results.scrollBy(0,0); } });

        new CountDownTimer(txt_results.getScrollY(), 20) {
            final int pos = txt_results.getScrollY();
            public void onTick(long millisUntilFinished) {
                txt_results.scrollTo(0, (int) (millisUntilFinished)); // from zero to 2000
            }

            public void onFinish() {
            }

        }.start();

        object.startAnimation(fadeIn);
    }

    private void fadeOutAndHideImage(final Button object, final int i, final int jclick)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(250+rand.nextInt(1000));

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                fadeInAndShowImage(object,i,jclick);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        object.startAnimation(fadeOut);
    }
}
