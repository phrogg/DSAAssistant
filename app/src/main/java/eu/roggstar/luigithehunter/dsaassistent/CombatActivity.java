package eu.roggstar.luigithehunter.dsaassistent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CombatActivity extends AppCompatActivity {

    Button but_lep_m,but_lep_p,but_ast_m,but_ast_p;
    SharedPreferences mP_lep;
    SharedPreferences.Editor mEditor;
    SeekBar sb_lep,sb_ast;
    TextView tv_lep, tv_ast;
    ImageView iv_head,iv_la,iv_ra,iv_ll,iv_rl,iv_stomach;
    LinearLayout linLayAst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        but_lep_m = findViewById(R.id.but_lep_m);
        but_lep_p = findViewById(R.id.but_lep_p);
        but_ast_m = findViewById(R.id.but_ast_m);
        but_ast_p = findViewById(R.id.but_ast_p);
        sb_lep = findViewById(R.id.sb_lep);
        sb_ast = findViewById(R.id.sb_ast);
        tv_lep = findViewById(R.id.tv_lep);
        tv_ast = findViewById(R.id.tv_ast);
        iv_head = findViewById(R.id.iv_head);
        iv_la = findViewById(R.id.iv_la);
        iv_ra = findViewById(R.id.iv_ra);
        iv_ll = findViewById(R.id.iv_ll);
        iv_rl = findViewById(R.id.iv_rl);
        iv_stomach = findViewById(R.id.iv_stomach);
        linLayAst = findViewById(R.id.linLayAst);

        {
            String[] attackArr = new String[]{"⚔️", "\uD83D\uDD2E", "\uD83D\uDDE1️", "\uD83D\uDEE1️", "\uD83C\uDFF9"};
            Random rand = new Random();

            this.setTitle(attackArr[rand.nextInt(attackArr.length)]+ " " + getResources().getString(R.string.TitleCombat));
        }

        //Ask for Devices
        mP_lep = getSharedPreferences("MAX", 0);
        mEditor = mP_lep.edit();


        seclr();

        if(mP_lep.getInt("AMAX",-1) != -2){  //define Astralpoints //TODO Maybe

            if(mP_lep.getInt("AMAX",0) != 0 && mP_lep.getInt("AMAX",0) >= 0){
                sb_ast.setMax(mP_lep.getInt("AMAX",0));
                sb_ast.setProgress(mP_lep.getInt("AST",0));
                tv_ast.setText(getString(R.string.Astralpunkte_Karma) + " (" + sb_ast.getProgress() + ")");
                linLayAst.setVisibility(View.VISIBLE);tv_ast.setVisibility(View.VISIBLE);
            }
        }

        if(mP_lep.getInt("MAX",0) != 0){ //define Lifepoints
            sb_lep.setMax(mP_lep.getInt("MAX",0));
            sb_lep.setProgress(mP_lep.getInt("LEP",0));
            sb_lep.setProgress(mP_lep.getInt("LEP",0));
            tv_lep.setText(getString(R.string.Lebenspunkte) + " (" + sb_lep.getProgress() + ")");
        }


        but_lep_m.setOnClickListener(
                V -> {
                    sb_lep.setProgress(sb_lep.getProgress()-1);
                    saver();
                }
        );

        but_lep_p.setOnClickListener(
                V -> {
                    sb_lep.setProgress(sb_lep.getProgress()+1);
                    saver();
                }
        );

        but_ast_m.setOnClickListener(
                V -> {
                    sb_ast.setProgress(sb_ast.getProgress()-1);
                    saver();
                }
        );

        but_ast_p.setOnClickListener(
                V -> {
                    sb_ast.setProgress(sb_ast.getProgress()+1);
                    saver();
                }
        );

        but_lep_m.setOnLongClickListener(v -> {
            Toast.makeText(CombatActivity.this, "This was a developer function and it has been disabled now. Use the menu to reset the stats.", Toast.LENGTH_LONG).show();
            /*
            mEditor.putInt("MAX",0).apply();
            mEditor.putInt("AMAX",-1).apply();
            Toast.makeText(CombatActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            finish();
            */
            return true;
        });

        sb_lep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                saver();

            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_lep.setText(getString(R.string.Lebenspunkte) + " (" + sb_lep.getProgress() + ")");
            }
        });

        sb_ast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                saver();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_ast.setText(getString(R.string.Astralpunkte_Karma) + " (" + sb_ast.getProgress() + ")");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.combat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.resetman:
                mEditor.putInt("HEAD",0).commit();
                mEditor.putInt("LL",0).commit();
                mEditor.putInt("RL",0).commit();
                mEditor.putInt("LA",0).commit();
                mEditor.putInt("RA",0).commit();
                mEditor.putInt("STOM",0).commit();
                saver();
                finish();
                startActivity(new Intent(CombatActivity.this,CombatActivity.class));
                return true;
        }
        return false;
    }

    void saver(){
        mEditor.putInt("LEP",sb_lep.getProgress()).commit();
        mEditor.putInt("AST",sb_ast.getProgress()).commit();
    }

    @Override
    public void onBackPressed() {
        saver();
        finish();
    }

    public void dialog(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.Wunden_Manager);

        builder.setMessage(R.string.Wunde_NeuAlt);

        builder.setPositiveButton(R.string.Wunde_Neu, (dialog, which) -> {
            dialog.dismiss();
            exec(v,1);
        });

        builder.setNegativeButton(R.string.Wunde_Alt, (dialog, which) -> {
            dialog.dismiss();
            exec(v,-1);
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    void seclr() {
        switch (mP_lep.getInt("LA",0)){
            case 0:
                iv_la.clearColorFilter();
                break;
            case 1:
                iv_la.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_la.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_la.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_la.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_la.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
        switch (mP_lep.getInt("RA",0)){
            case 0:
                iv_ra.clearColorFilter();
                break;
            case 1:
                iv_ra.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_ra.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_ra.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_ra.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_ra.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
        switch (mP_lep.getInt("STOM",0)){
            case 0:
                iv_stomach.clearColorFilter();
                break;
            case 1:
                iv_stomach.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_stomach.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_stomach.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_stomach.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_stomach.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
        switch (mP_lep.getInt("HEAD",0)){
            case 0:
                iv_head.clearColorFilter();
                break;
            case 1:
                iv_head.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_head.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_head.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_head.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_head.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
        switch (mP_lep.getInt("RL",0)){
            case 0:
                iv_rl.clearColorFilter();
                break;
            case 1:
                iv_rl.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_rl.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_rl.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_rl.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_rl.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }
        switch (mP_lep.getInt("LL",0)){
            case 0:
                iv_ll.clearColorFilter();
                break;
            case 1:
                iv_ll.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_1), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
                iv_ll.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_2), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 3:
                iv_ll.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_3), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 4:
                iv_ll.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_4), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
            case 5:
                iv_ll.setColorFilter(ContextCompat.getColor(CombatActivity.this, R.color.HP_5), android.graphics.PorterDuff.Mode.MULTIPLY);
                break;
        }

        //if(mP_lep.getInt("AMAX",0) == -2){sb_ast.setVisibility(View.GONE);but_ast_m.setVisibility(View.GONE);but_ast_p.setVisibility(View.GONE);tv_ast.setVisibility(View.GONE);}
        if(mP_lep.getInt("AMAX",0) == -2){linLayAst.setVisibility(View.GONE);tv_ast.setVisibility(View.GONE);}
    }

    void exec(View v, Integer i){
        switch (v.getId()) {
            case R.id.iv_head:
                if(mP_lep.getInt("HEAD",0) + i >-1 && mP_lep.getInt("HEAD",0) + i < 6) mEditor.putInt("HEAD",mP_lep.getInt("HEAD",0)+i).commit();
                break;
            case R.id.iv_la:
                if(mP_lep.getInt("LA",0) + i >-1 && mP_lep.getInt("LA",0) + i < 6) mEditor.putInt("LA",mP_lep.getInt("LA",0)+i).commit();
            break;
            case R.id.iv_ra:
                if(mP_lep.getInt("RA",0) + i >-1 && mP_lep.getInt("RA",0) + i < 6) mEditor.putInt("RA",mP_lep.getInt("RA",0)+i).commit();
                break;
            case R.id.iv_stomach:
                if(mP_lep.getInt("STOM",0) + i >-1 && mP_lep.getInt("STOM",0) + i < 6) mEditor.putInt("STOM",mP_lep.getInt("STOM",0)+i).commit();
                break;
            case R.id.iv_ll:
                if(mP_lep.getInt("LL",0) + i >-1 && mP_lep.getInt("LL",0) + i < 6) mEditor.putInt("LL",mP_lep.getInt("LL",0)+i).commit();
                break;
            case R.id.iv_rl:
                if(mP_lep.getInt("RL",0) + i >-1 && mP_lep.getInt("RL",0) + i < 6) mEditor.putInt("RL",mP_lep.getInt("RL",0)+i).commit();
                break;
        }
        seclr();
    }

    public void onClick(View v) {
        dialog(v);
    }

}