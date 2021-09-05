package eu.roggstar.luigithehunter.dsaassistent;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class VersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        final TextView tv_version =  findViewById(R.id.tv_version);
        final TextView tv_copyright =  findViewById(R.id.tv_copyright);
        final ImageView img_logo = findViewById(R.id.img_logo);
        tv_version.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv_version.setText(BuildConfig.VERSION_NAME + ", V:" + BuildConfig.VERSION_CODE);
        tv_copyright.setMovementMethod(LinkMovementMethod.getInstance());
        this.setTitle("\uD83C\uDFD7️ with ✨ in \uD83C\uDDE9\uD83C\uDDEA");

        img_logo.setOnLongClickListener(
                V -> {
                    fadeOutAndHideImage(img_logo);
                    return false;
                }
        );
    }

    private void fadeInAndShowImage(final ImageView img)
    {
        Animation fadeIn = new AlphaAnimation(0,1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(1000);

        fadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {
                img.setVisibility(View.VISIBLE);
            }
        });

        img.startAnimation(fadeIn);
    }

    private void fadeOutAndHideImage(final ImageView img)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                img.setVisibility(View.INVISIBLE);

                if(img.getContentDescription().toString().equals("DSA Assistant Logo")){
                    img.setImageResource(R.drawable.rogg);
                    img.setContentDescription("Roggstar Logo");
                } else {
                    img.setImageResource(R.drawable.logo);
                    img.setContentDescription("DSA Assistant Logo");
                }
                fadeInAndShowImage(img);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        img.startAnimation(fadeOut);
    }
}
