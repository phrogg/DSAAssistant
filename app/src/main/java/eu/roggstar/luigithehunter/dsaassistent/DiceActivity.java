package eu.roggstar.luigithehunter.dsaassistent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        rand = new Random();

        but_d3.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) { fadeOutAndHideImage(but_d3,3); }});
        but_d4.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d4,4);  }});
        but_d6.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d6,6);  }});
        but_d8.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d8,8);  }});
        but_d10.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d10,10);  }});
        but_d1010.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d1010,1010);  }});
        but_d12.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d12,12);  }});
        but_d20.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d20,20);  }});
        but_d100.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) {  fadeOutAndHideImage(but_d100,100);  }});


        //LongClickListener
        but_d3.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D3:\n"+String.valueOf(rand.nextInt(3) + 1)+"\n"+String.valueOf(rand.nextInt(3) + 1)+"\n"+String.valueOf(rand.nextInt(3) + 1), Toast.LENGTH_LONG).show();
            return true;}});
        but_d4.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D4:\n"+String.valueOf(rand.nextInt(4) + 1)+"\n"+String.valueOf(rand.nextInt(4) + 1)+"\n"+String.valueOf(rand.nextInt(4) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d6.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D6:\n"+String.valueOf(rand.nextInt(6) + 1)+"\n"+String.valueOf(rand.nextInt(6) + 1)+"\n"+String.valueOf(rand.nextInt(6) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d8.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D8:\n"+String.valueOf(rand.nextInt(8) + 1)+"\n"+String.valueOf(rand.nextInt(8) + 1)+"\n"+String.valueOf(rand.nextInt(8) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d10.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D10:\n"+String.valueOf(rand.nextInt(10) + 1)+"\n"+String.valueOf(rand.nextInt(10) + 1)+"\n"+String.valueOf(rand.nextInt(10) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d1010.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D10/10:\n"+String.valueOf((rand.nextInt(10) + 1)*10)+"\n"+String.valueOf((rand.nextInt(10) + 1)*10)+"\n"+String.valueOf((rand.nextInt(10) + 1)*10),Toast.LENGTH_LONG).show();
            return true;}});
        but_d12.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D12:\n"+String.valueOf(rand.nextInt(12) + 1)+"\n"+String.valueOf(rand.nextInt(12) + 1)+"\n"+String.valueOf(rand.nextInt(12) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d20.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D20:\n"+String.valueOf(rand.nextInt(20) + 1)+"\n"+String.valueOf(rand.nextInt(20) + 1)+"\n"+String.valueOf(rand.nextInt(20) + 1),Toast.LENGTH_LONG).show();
            return true;}});
        but_d100.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View v) {
            Toast.makeText(DiceActivity.this, "Drei Ergebnisse vom D100:\n"+String.valueOf(rand.nextInt(100) + 1)+"\n"+String.valueOf(rand.nextInt(100) + 1)+"\n"+String.valueOf(rand.nextInt(100) + 1),Toast.LENGTH_LONG).show();
            return true;}});

    }

    private void fadeInAndShowImage(final Button object, final int i)
    {
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(250+rand.nextInt(1000));

        fadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {
                if(i == 1010){
                    txt_results.setText(txt_results.getText()+"D20:"+String.valueOf((rand.nextInt(10) + 1)*10)+"\n");
                } else { txt_results.setText(txt_results.getText()+"D20:"+String.valueOf(rand.nextInt(i) + 1)+"\n"); } }
        });

        object.startAnimation(fadeIn);
    }

    private void fadeOutAndHideImage(final Button object, final int i)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(250+rand.nextInt(1000));

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                fadeInAndShowImage(object,i);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        object.startAnimation(fadeOut);
    }
}
