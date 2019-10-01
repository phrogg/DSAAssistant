package eu.roggstar.luigithehunter.dsaassistent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    SharedPreferences mPrefs;
    SharedPreferences.Editor mEdit;
    TextView tv_s_mu,tv_s_kl,tv_s_in,tv_s_ch,tv_s_ff,tv_s_ge,tv_s_ko,tv_s_kk,tv_s_so,tv_s_mr,tv_s_gs;
    Spinner spin_stats, spin_points;
    EditText txt_points;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView but_dice = findViewById(R.id.but_dice);
        ImageView but_geld = findViewById(R.id.but_geld);
        ImageView but_kampf = findViewById(R.id.but_kampf);
        ImageView but_gm = findViewById(R.id.but_gm);
        Button but_change_stat = findViewById(R.id.but_change_stat);

        Glide.with(this).load(R.drawable.geld).into(but_geld);
        Glide.with(this).load(R.drawable.kampf).into(but_kampf);
        Glide.with(this).load(R.drawable.gm).into(but_gm);
        Glide.with(this).load(R.drawable.w20).into(but_dice);

        tv_s_mu = findViewById(R.id.tv_s_mu);
        tv_s_kl = findViewById(R.id.tv_s_kl);
        tv_s_in = findViewById(R.id.tv_s_in);
        tv_s_ch = findViewById(R.id.tv_s_ch);
        tv_s_ff = findViewById(R.id.tv_s_ff);
        tv_s_ge = findViewById(R.id.tv_s_ge);
        tv_s_ko = findViewById(R.id.tv_s_ko);
        tv_s_kk = findViewById(R.id.tv_s_kk);
        tv_s_so = findViewById(R.id.tv_s_so);
        tv_s_mr = findViewById(R.id.tv_s_mr);
        tv_s_gs = findViewById(R.id.tv_s_gs);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mPrefs = getSharedPreferences("STAT",0);
        mEdit = mPrefs.edit();

        if(mPrefs.getInt("STAT",0) == 1) {
            setstats();
        } else {
            startActivity(new Intent(MainActivity.this,ParametersActivity.class));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.array_stats));

        spin_stats = findViewById(R.id.spin_stats);
        spin_points = findViewById(R.id.spin_points);
        txt_points = findViewById(R.id.txt_points);
        txt_points.setInputType(InputType.TYPE_CLASS_NUMBER);

        spin_stats.setAdapter(adapter);

        final List<String> list=new ArrayList();
        list.add("+");
        list.add("-");

        ArrayAdapter<String> adp= new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_points.setAdapter(adp);


        but_geld.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        startActivity(new Intent(MainActivity.this,MoneyActivity.class));
                    }
                }
        );

        but_kampf.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        startActivity(new Intent(MainActivity.this,CombatActivity.class));
                    }
                }
        );

        but_change_stat.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        if (txt_points.getText().toString().length() == 0){
                            return;
                        }
                        if (TextUtils.isDigitsOnly(txt_points.getText())) {
                            int value = Integer.parseInt(txt_points.getText().toString());
                            if(spin_points.getSelectedItem().toString().equals("-")){value = value*(-1);}
                            switch (spin_stats.getSelectedItem().toString()) {
                                case ("MU"):
                                    value += Integer.parseInt(tv_s_mu.getText().toString().substring(3, tv_s_mu.length()));
                                    tv_s_mu.setText("MU:" + value);
                                    if (value != mPrefs.getInt("MU", 0)) {
                                        tv_s_mu.setTextColor(Color.RED);
                                    } else {
                                        tv_s_mu.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("KL"):
                                    value += Integer.parseInt(tv_s_kl.getText().toString().substring(3, tv_s_kl.length()));
                                    tv_s_kl.setText("KL:" + value);
                                    if (value != mPrefs.getInt("KL", 0)) {
                                        tv_s_kl.setTextColor(Color.RED);
                                    } else {
                                        tv_s_kl.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("IN"):
                                    value += Integer.parseInt(tv_s_in.getText().toString().substring(3, tv_s_in.length()));
                                    tv_s_in.setText("IN:" + value);
                                    if (value != mPrefs.getInt("IN", 0)) {
                                        tv_s_in.setTextColor(Color.RED);
                                    } else {
                                        tv_s_in.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("CH"):
                                    value += Integer.parseInt(tv_s_ch.getText().toString().substring(3, tv_s_ch.length()));
                                    tv_s_ch.setText("CH:" + value);
                                    if (value != mPrefs.getInt("CH", 0)) {
                                        tv_s_ch.setTextColor(Color.RED);
                                    } else {
                                        tv_s_ch.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("FF"):
                                    value += Integer.parseInt(tv_s_ff.getText().toString().substring(3, tv_s_ff.length()));
                                    tv_s_ff.setText("FF:" + value);
                                    if (value != mPrefs.getInt("FF", 0)) {
                                        tv_s_ff.setTextColor(Color.RED);
                                    } else {
                                        tv_s_ff.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("GE"):
                                    value += Integer.parseInt(tv_s_ge.getText().toString().substring(3, tv_s_ge.length()));
                                    tv_s_ge.setText("GE:" + value);
                                    if (value != mPrefs.getInt("GE", 0)) {
                                        tv_s_ge.setTextColor(Color.RED);
                                    } else {
                                        tv_s_ge.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("KO"):
                                    value += Integer.parseInt(tv_s_ko.getText().toString().substring(3, tv_s_ko.length()));
                                    tv_s_ko.setText("KO:" + value);
                                    if (value != mPrefs.getInt("KO", 0)) {
                                        tv_s_ko.setTextColor(Color.RED);
                                    } else {
                                        tv_s_ko.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("KK"):
                                    value += Integer.parseInt(tv_s_kk.getText().toString().substring(3, tv_s_kk.length()));
                                    tv_s_kk.setText("KK:" + value);
                                    if (value != mPrefs.getInt("KK", 0)) {
                                        tv_s_kk.setTextColor(Color.RED);
                                    } else {
                                        tv_s_kk.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("SO"):
                                    value += Integer.parseInt(tv_s_so.getText().toString().substring(3, tv_s_so.length()));
                                    tv_s_so.setText("SO:" + value);
                                    if (value != mPrefs.getInt("SO", 0)) {
                                        tv_s_so.setTextColor(Color.RED);
                                    } else {
                                        tv_s_so.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("MR"):
                                    value += Integer.parseInt(tv_s_mr.getText().toString().substring(3, tv_s_mr.length()));
                                    tv_s_mr.setText("MR:" + value);
                                    if (value != mPrefs.getInt("MR", 0)) {
                                        tv_s_mr.setTextColor(Color.RED);
                                    } else {
                                        tv_s_mr.setTextColor(Color.GRAY);
                                    }
                                    break;
                                case ("GS"):
                                    value += Integer.parseInt(tv_s_gs.getText().toString().substring(3, tv_s_gs.length()));
                                    tv_s_gs.setText("GS:" + value);
                                    if (value != mPrefs.getInt("GS", 0)) {
                                        tv_s_gs.setTextColor(Color.RED);
                                    } else {
                                        tv_s_gs.setTextColor(Color.GRAY);
                                    }
                                    break;
                            }
                            txt_points.setText("");
                            //InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                        } else {
                            Toast.makeText(MainActivity.this, "Huch, das ist aber zu viel des guten!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        but_gm.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        startActivity(new Intent(MainActivity.this,GMActivity.class));
                    }
                }
        );

        but_dice.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        startActivity(new Intent(MainActivity.this,DiceActivity.class));
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent browserIntent;
        switch (item.getItemId()) {
            case R.id.DieMacher:
                startActivity(new Intent(MainActivity.this,VersionActivity.class));
                return true;
            case R.id.resettmp:
                setstats();
                return true;
            case R.id.update:
                startActivity(new Intent(MainActivity.this,ParametersActivity.class));
                finish();
                return true;
            case R.id.aboutMe:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.phrogg.de"));
                startActivity(browserIntent);
                return true;
            case R.id.feedback:
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LuigiTheHunter/DSAAssistant/issues"));
                startActivity(browserIntent);
                return true;
        }
        return false;
    }

    void setstats(){
        tv_s_ge.setText("GE:" + mPrefs.getInt("GE", 0));
        tv_s_mu.setText("MU:" + mPrefs.getInt("MU", 0));
        tv_s_kl.setText("KL:" + mPrefs.getInt("KL", 0));
        tv_s_in.setText("IN:" + mPrefs.getInt("IN", 0));
        tv_s_ch.setText("CH:" + mPrefs.getInt("CH", 0));
        tv_s_ff.setText("FF:" + mPrefs.getInt("FF", 0));
        tv_s_ko.setText("KO:" + mPrefs.getInt("KO", 0));
        tv_s_kk.setText("KK:" + mPrefs.getInt("KK", 0));
        tv_s_so.setText("SO:" + mPrefs.getInt("SO", 0));
        tv_s_mr.setText("MR:" + mPrefs.getInt("MR", 0));
        tv_s_gs.setText("GS:" + mPrefs.getInt("GS", 0));

        tv_s_mu.setTextColor(Color.GRAY);
        tv_s_kl.setTextColor(Color.GRAY);
        tv_s_in.setTextColor(Color.GRAY);
        tv_s_ch.setTextColor(Color.GRAY);
        tv_s_ge.setTextColor(Color.GRAY);
        tv_s_ff.setTextColor(Color.GRAY);
        tv_s_ko.setTextColor(Color.GRAY);
        tv_s_kk.setTextColor(Color.GRAY);
        tv_s_so.setTextColor(Color.GRAY);
        tv_s_mr.setTextColor(Color.GRAY);
        tv_s_gs.setTextColor(Color.GRAY);
    }
}
