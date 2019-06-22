package eu.roggstar.luigithehunter.dsaassistent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ParametersActivity extends AppCompatActivity {

    SharedPreferences mPrefs,mP_lep;
    SharedPreferences.Editor mEdit,mEditor;
    TextView tv_mu, tv_kl, tv_in, tv_ch, tv_ff, tv_ge, tv_ko, tv_kk, tv_so, tv_mr, tv_gs, tv_lep, tv_ast;
    Button but_save;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        //Connectors
        tv_mu = findViewById(R.id.tv_mu);
        tv_kl = findViewById(R.id.tv_kl);
        tv_in = findViewById(R.id.tv_in);
        tv_ch = findViewById(R.id.tv_ch);
        tv_ff = findViewById(R.id.tv_ff);
        tv_ge = findViewById(R.id.tv_ge);
        tv_ko = findViewById(R.id.tv_ko);
        tv_kk = findViewById(R.id.tv_kk);
        tv_so = findViewById(R.id.tv_so);
        tv_mr = findViewById(R.id.tv_mr);
        tv_gs = findViewById(R.id.tv_gs);
        tv_lep = findViewById(R.id.tv_lep);
        tv_ast = findViewById(R.id.tv_ast);

        but_save = findViewById(R.id.but_save);

        tv_mu.setTextColor(Color.GRAY);
        tv_kl.setTextColor(Color.GRAY);
        tv_in.setTextColor(Color.GRAY);
        tv_ch.setTextColor(Color.GRAY);
        tv_ge.setTextColor(Color.GRAY);
        tv_ff.setTextColor(Color.GRAY);
        tv_ko.setTextColor(Color.GRAY);
        tv_lep.setTextColor(Color.GRAY);
        tv_ast.setTextColor(Color.GRAY);
        tv_kk.setTextColor(Color.GRAY);
        tv_so.setTextColor(Color.GRAY);
        tv_mr.setTextColor(Color.GRAY);
        tv_gs.setTextColor(Color.GRAY);

        this.setTitle(R.string.TitleParameter);

        mPrefs = getSharedPreferences("STAT", 0);
        mEdit = mPrefs.edit();

        mP_lep = getSharedPreferences("MAX", 0);
        mEditor = mP_lep.edit();

        but_save.setOnClickListener(new Button.OnClickListener() {public void onClick(View V) { closeAndSave(); }});


        if(mPrefs.getInt("STAT",0) == 1) {
            setstats();
        } else {
            //Body Parts
            mEditor.putInt("HEAD",0).apply();
            mEditor.putInt("LL",0).apply();
            mEditor.putInt("RL",0).apply();
            mEditor.putInt("LA",0).apply();
            mEditor.putInt("RA",0).apply();
            mEditor.putInt("STOM",0).apply();
            mEditor.putInt("AST",0).apply();
        }
    }

    void setstats(){
        tv_mu.setText(String.valueOf(mPrefs.getInt("MU", 0)));
        tv_kl.setText(String.valueOf(mPrefs.getInt("KL", 0)));
        tv_in.setText(String.valueOf(mPrefs.getInt("IN", 0)));
        tv_ch.setText(String.valueOf(mPrefs.getInt("CH", 0)));
        tv_ff.setText(String.valueOf(mPrefs.getInt("FF", 0)));
        tv_ko.setText(String.valueOf(mPrefs.getInt("KO", 0)));
        tv_kk.setText(String.valueOf(mPrefs.getInt("KK", 0)));
        tv_so.setText(String.valueOf(mPrefs.getInt("SO", 0)));
        tv_mr.setText(String.valueOf(mPrefs.getInt("MR", 0)));
        tv_gs.setText(String.valueOf(mPrefs.getInt("GS", 0)));
        tv_ge.setText(String.valueOf(mPrefs.getInt("GE", 0)));
        tv_lep.setText(String.valueOf(mP_lep.getInt("MAX", 0)));
        if(mP_lep.getInt("AMAX", 0) == -2) {
            tv_ast.setText("0");
        } else {
            tv_ast.setText(String.valueOf(mP_lep.getInt("AMAX", 0)));
        }
    }
    
    private void saver() {
        mEdit.putInt("STAT", 1).commit();
        mEdit.putInt("MU", Integer.parseInt(tv_mu.getText().toString())).commit();
        mEdit.putInt("KL", Integer.parseInt(tv_kl.getText().toString())).commit();
        mEdit.putInt("IN", Integer.parseInt(tv_in.getText().toString())).commit();
        mEdit.putInt("CH", Integer.parseInt(tv_ch.getText().toString())).commit();
        mEdit.putInt("FF", Integer.parseInt(tv_ff.getText().toString())).commit();
        mEdit.putInt("GE", Integer.parseInt(tv_ge.getText().toString())).commit();
        mEdit.putInt("KO", Integer.parseInt(tv_ko.getText().toString())).commit();
        mEdit.putInt("KK", Integer.parseInt(tv_kk.getText().toString())).commit();
        mEdit.putInt("SO", Integer.parseInt(tv_so.getText().toString())).commit();
        mEdit.putInt("MR", Integer.parseInt(tv_mr.getText().toString())).commit();
        mEdit.putInt("GS", Integer.parseInt(tv_gs.getText().toString())).commit();

        //Health
        mEditor.putInt("MAX",Integer.parseInt(tv_lep.getText().toString())).commit();
        if(Integer.parseInt(tv_ast.getText().toString()) == 0) {
            mEditor.putInt("AMAX", -2).commit();
        } else {
            mEditor.putInt("AMAX", Integer.parseInt(tv_ast.getText().toString())).commit();
        }


        startActivity(new Intent(ParametersActivity.this,MainActivity.class));
        Toast.makeText(this, "Gespeichert!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void closeAndSave(){
        if(tv_ast.getText().length() < 1){
            tv_ast.setText("0");
        }

        int[] textViewIDs = new int[] {R.id.tv_mu, R.id.tv_kl, R.id.tv_in, R.id.tv_ch, R.id.tv_ff, R.id.tv_ge, R.id.tv_ko, R.id.tv_kk, R.id.tv_so, R.id.tv_mr, R.id.tv_gs, R.id.tv_lep };
        for(int i=0; i < textViewIDs.length; i++) {
            TextView tv = findViewById(textViewIDs[i]);
            if(tv.getText().toString().equals("0") || tv.getText().toString().isEmpty()){
                Toast.makeText(this, "Erst alles ausfüllen!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        saver();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Nicht gespeichert!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ParametersActivity.this,MainActivity.class));
        finish();
    }
}
