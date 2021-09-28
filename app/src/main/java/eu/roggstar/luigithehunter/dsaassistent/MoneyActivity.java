package eu.roggstar.luigithehunter.dsaassistent;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MoneyActivity extends AppCompatActivity {

    SeekBar sb_duk,sb_sil,sb_hel,sb_kre,sb_sch;
    TextView tv_duk,tv_sil,tv_hel,tv_kre,tv_sch;
    Button but_duk_m,but_duk_p,but_sil_m,but_sil_p,but_hel_m,but_hel_p,but_kre_m,but_kre_p,but_sch_p,but_sch_m;
    SharedPreferences mPrefs;
    Boolean activ = true;

    enum Money {
        Dukaten,
        Silber,
        Heller,
        Kreuzer
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        //Ask for Devices
        mPrefs = getSharedPreferences("DUK", 0);

        this.setTitle("⚖️ " + getResources().getString(R.string.TitleMoney));

        //Define Start Values

        but_duk_m = findViewById(R.id.but_duk_m);
        but_duk_p = findViewById(R.id.but_duk_p);
        but_sil_m = findViewById(R.id.but_sil_m);
        but_sil_p = findViewById(R.id.but_sil_p);
        but_hel_m = findViewById(R.id.but_hel_m);
        but_hel_p = findViewById(R.id.but_hel_p);
        but_kre_m = findViewById(R.id.but_kre_m);
        but_kre_p = findViewById(R.id.but_kre_p);
        but_sch_m = findViewById(R.id.but_sch_m);
        but_sch_p = findViewById(R.id.but_sch_p);

        sb_duk = findViewById(R.id.sb_duk);
        sb_hel = findViewById(R.id.sb_hel);
        sb_kre = findViewById(R.id.sb_kre);
        sb_sil = findViewById(R.id.sb_sil);
        sb_sch = findViewById(R.id.sb_sch);

        tv_duk = findViewById(R.id.tv_duk);
        tv_hel = findViewById(R.id.tv_hel);
        tv_kre = findViewById(R.id.tv_kre);
        tv_sil = findViewById(R.id.tv_sil);
        tv_sch = findViewById(R.id.tv_sch);

        activ = true;

        //Parameter
        sb_duk.setMax(mPrefs.getInt("DUK",0)+20);
        sb_duk.setProgress(mPrefs.getInt("DUK",0));
        tv_duk.setText("Dukaten " + "(" + sb_duk.getProgress() + ")");
        sb_sil.setMax(mPrefs.getInt("SIL",0)+20);
        sb_sil.setProgress(mPrefs.getInt("SIL",0));
        tv_sil.setText("Silbertaler " + "(" + sb_sil.getProgress() + ")");
        sb_hel.setMax(mPrefs.getInt("HEL",0)+20);
        sb_hel.setProgress(mPrefs.getInt("HEL",0));
        tv_hel.setText("Heller " + "(" + sb_hel.getProgress() + ")");
        sb_kre.setMax(mPrefs.getInt("KRE",0)+20);
        sb_kre.setProgress(mPrefs.getInt("KRE",0));
        tv_kre.setText("Kreuzer " + "(" + sb_kre.getProgress() + ")");
        sb_sch.setMax(10);
        sb_sch.setProgress(mPrefs.getInt("SCH",0));
        tv_sch.setText("Schicksals P. " + "(" + sb_sch.getProgress() + ")");

        // binding
        but_duk_m.setOnClickListener(V -> { changeMoneyValue(Money.Dukaten,-1); });
        but_duk_p.setOnClickListener(V -> { changeMoneyValue(Money.Dukaten,1); });

        but_sil_m.setOnClickListener(V -> { changeMoneyValue(Money.Silber,-1); });
        but_sil_p.setOnClickListener(V -> { changeMoneyValue(Money.Silber,1); });

        but_hel_m.setOnClickListener(V -> { changeMoneyValue(Money.Heller,-1); });
        but_hel_p.setOnClickListener(V -> { changeMoneyValue(Money.Heller,1); });

        but_kre_m.setOnClickListener(V -> { changeMoneyValue(Money.Kreuzer,-1); });
        but_kre_p.setOnClickListener(V -> { changeMoneyValue(Money.Kreuzer,1); });

        but_sch_m.setOnClickListener(V -> { sb_sch.setProgress(sb_sch.getProgress()-1);saveVals(); });
        but_sch_p.setOnClickListener(V -> { sb_sch.setProgress(sb_sch.getProgress()+1);saveVals(); });


        but_duk_m.setOnLongClickListener(v -> { ldialog(1,false); return true; });
        but_duk_p.setOnLongClickListener(v -> { ldialog(1,true); return true; });
        but_hel_m.setOnLongClickListener(v -> { ldialog(3,false); return true; });
        but_hel_p.setOnLongClickListener(v -> { ldialog(3,true); return true; });
        but_kre_m.setOnLongClickListener(v -> { ldialog(4,false); return true; });
        but_kre_p.setOnLongClickListener(v -> { ldialog(4,true); return true; });
        but_sil_p.setOnLongClickListener(v -> { ldialog(2,true); return true; });
        but_sil_m.setOnLongClickListener(v -> { ldialog(2,false); return true; });


        sb_duk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                saveVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_duk.setText("Dukaten (" + sb_duk.getProgress() + ")");
                if(sb_duk.getMax()-sb_duk.getProgress() < 20 && activ){
                    sb_duk.setMax(sb_duk.getMax()+20);
                } else if(sb_duk.getMax()-sb_duk.getProgress() > 80 && activ){
                    sb_duk.setMax(sb_duk.getMax()-20);
                }
            }
        });
        sb_sil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                if(sb_sil.getProgress() > 10) dialog(2,1,sb_sil.getProgress()/10);
                saveVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_sil.setText("Silbertaler (" + sb_sil.getProgress() + ")");
            }
        });
        sb_hel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                if(sb_hel.getProgress() > 10) dialog(3,2,sb_hel.getProgress()/10);
                saveVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_hel.setText("Heller (" + sb_hel.getProgress() + ")");
            }
        });
        sb_kre.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                if(sb_kre.getProgress() > 10) dialog(4,3,sb_kre.getProgress()/10);
                saveVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_kre.setText("Kreuzer (" + sb_kre.getProgress() + ")");
            }
        });
        sb_sch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                saveVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                tv_sch.setText("Schicksals P. (" + sb_sch.getProgress() + ")");
            }
        });

    }

    private void changeMoneyValue(Money money, int amount) {
        switch(money){
            case Dukaten:
                if(sb_duk.getProgress() + amount > sb_duk.getMax()){
                    sb_duk.setMax(sb_duk.getMax()+amount);
                }
                sb_duk.setProgress(sb_duk.getProgress()+amount);saveVals();
                break;
            case Silber:
                if(sb_sil.getProgress() + amount > sb_sil.getMax()){
                    sb_sil.setMax(sb_sil.getProgress()+amount);
                }
                sb_sil.setProgress(sb_sil.getProgress()+amount);saveVals();
                break;
            case Kreuzer:
                if(sb_kre.getProgress() + amount > sb_kre.getMax()){
                    sb_kre.setMax(sb_kre.getProgress()+amount);
                }
                sb_kre.setProgress(sb_kre.getProgress()+amount);saveVals();
                break;
            case Heller:
                if(sb_hel.getProgress() + amount > sb_hel.getMax()){
                    sb_hel.setMax(sb_hel.getProgress()+amount);
                }
                sb_hel.setProgress(sb_hel.getProgress()+amount);saveVals();
                break;
        }
        saveVals();
    }

    void dialog(final int in, final int out, final int mul) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String one="",two="";
        switch(in){
            case(1):one = "Dukaten";break;
            case(2):one = "Silbertaler";break;
            case(3):one = "Heller";break;
            case(4):one = "Kreuzer";break;
        }
        switch(out){
            case(1):two = "Dukaten";break;
            case(2):two = "Silbertaler";break;
            case(3):two = "Heller";break;
            case(4):two = "Kreuzer";break;
        }

        builder.setTitle(getResources().getString(R.string.change_money));
        builder.setMessage("("+mul*10+" "+one+" - > "+mul+" "+two+")");
        builder.setPositiveButton(getResources().getString(R.string.Ja), (dialog, which) -> {
        //TODO Fix catch result
            if(in==2 && out==1 && sb_sil.getProgress() >= mul*10){sb_duk.setProgress(sb_duk.getProgress()+mul);sb_sil.setProgress(sb_sil.getProgress()-mul*10);}
            if(in==3 && out==2 && sb_hel.getProgress() >= mul*10){sb_sil.setProgress(sb_sil.getProgress()+mul);sb_hel.setProgress(sb_hel.getProgress()-mul*10);}
            if(in==4 && out==3 && sb_kre.getProgress() >= mul*10){sb_hel.setProgress(sb_hel.getProgress()+mul);sb_kre.setProgress(sb_kre.getProgress()-mul*10);}
            saveVals();
            dialog.dismiss();
        });

        builder.setNegativeButton(getResources().getString(R.string.Nein), (dialog, which) -> {
            // Do nothing
            dialog.dismiss();
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private int addDuk(int amount){
        while(amount != 0) {
            if (sb_duk.getProgress() != sb_duk.getMax()) {
                 sb_duk.setProgress(sb_duk.getProgress() + 1);
                 amount--;
            } else {
                 sb_duk.setMax(sb_duk.getProgress() + 10);
            }
    }
    return amount;
    }

    private boolean addM(final int in, int amount){
        while(amount != 0){
            switch(in){
                case(1):
                    amount = addDuk(amount);
                    break;
                case(2):
                    if(amount >= 10) {
                        addDuk(1);
                        amount -= 10;
                    } else if (sb_sil.getProgress() != sb_sil.getMax()){
                        sb_sil.setProgress(sb_sil.getProgress()+1);
                        amount--;
                    } else {
                        sb_sil.setMax(sb_sil.getProgress()+10);
                    }
                    break;
                case(3):
                    if(amount >= 100){
                       addDuk(1);
                        amount -= 100;
                    } else if(amount >= 10 && sb_sil.getProgress() != sb_sil.getMax()){
                        sb_sil.setProgress(sb_sil.getProgress()+1);
                        amount -= 10;
                    } else if (sb_hel.getProgress() != sb_hel.getMax()){
                        sb_hel.setProgress(sb_hel.getProgress()+1);
                        amount--;
                    } else {
                        sb_hel.setMax(sb_hel.getProgress()+10);
                    }
                    break;
                case(4):
                    if(amount >= 1000){
                        addDuk(1);
                        amount -= 1000;
                    } else if(amount >= 100 && sb_sil.getProgress() != sb_sil.getMax()){
                        sb_sil.setProgress(sb_sil.getProgress()+1);
                        amount -= 100;
                    } else if(amount >= 10 && sb_hel.getProgress() != sb_hel.getMax()) {
                        sb_hel.setProgress(sb_hel.getProgress() + 1);
                        amount -= 10;
                    } else if (sb_kre.getProgress() != sb_kre.getMax()){
                    sb_kre.setProgress(sb_kre.getProgress()+1);
                    amount--;
                    } else {
                        sb_kre.setMax(sb_kre.getProgress()+10);
                    }
                    break;
            }
        }
        saveVals();
        return true;
    }

    private boolean remM(final int in, int amount){
        int d = 0;
        for(int i=0;i<sb_duk.getProgress();i++){
            d+=1000;
        } for(int i=0;i<sb_sil.getProgress();i++){
            d+=100;
        } for(int i=0;i<sb_hel.getProgress();i++){
            d+=10;
        } for(int i=0;i<sb_kre.getProgress();i++){
            d++;
        }

        switch(in){
            case(1):
                if(d < amount*1000){
                    return false;
                } else {
                    amount *= 1000;
                }
                break;
            case(2):
                if(d < amount*100){
                    return false;
                } else {
                    amount *= 100;
                }
                break;
            case(3):
                if(d < amount*10){
                    return false;
                } else {
                    amount *= 10;
                }
                break;
            case(4):
                if(d < amount){
                    return false;
                }
                break;
        }

        while(amount != 0){
            if(amount - 1000 >= 0 && sb_duk.getProgress() > 0){
                sb_duk.setProgress(sb_duk.getProgress()-1);
                amount -= 1000;
            } else if(amount - 100 >= 0 && sb_sil.getProgress() > 0){
                sb_sil.setProgress(sb_sil.getProgress()-1);
                amount -= 100;
            } else if(amount - 10 >= 0 && sb_hel.getProgress() > 0){
                sb_hel.setProgress(sb_hel.getProgress()-1);
                amount -= 10;
            } else if(amount - 1 >= 0 && sb_kre.getProgress() > 0) {
                sb_kre.setProgress(sb_kre.getProgress() - 1);
                amount -= 1;
            }
        }
        saveVals();
        return true;
    }

    private void ldialog(final int in, final boolean pom){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String one="",two="";

        switch(in){
            case(1):one = " Dukaten";break;
            case(2):one = " Silbertaler";break;
            case(3):one = " Heller";break;
            case(4):one = " Kreuzer";break;
        }

        if(pom == true){
            two=" Hinzufügen";
        } else {
            two=" Abziehen";
        }

        final String fone = one;

        builder.setTitle(one+two);
        builder.setCancelable(false);

        final EditText edtxt = new EditText(this);
        //edtxt.setPadding(100,0,100,0);
        edtxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        edtxt.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(edtxt);

        builder.setCancelable(true);

        builder.setNeutralButton("OK", (dialog, which) -> {
             activ = false;
                if(edtxt.length() != 0) {
                    Integer inpu = Integer.parseInt(edtxt.getText().toString());
                    if (pom == true) {
                        if(!addM(in,inpu)){
                            Toast.makeText(MoneyActivity.this, getResources().getString(R.string.Fehler), Toast.LENGTH_SHORT).show();
                        } else {Toast.makeText(MoneyActivity.this,  inpu.toString() + " " + fone + " " + getResources().getString(R.string.Geld_Hinzu2)+".", Toast.LENGTH_SHORT).show();}
                    } else {
                        if(!remM(in,inpu)){
                            Toast.makeText(MoneyActivity.this, getResources().getString(R.string.Nicht_genug_Geld), Toast.LENGTH_SHORT).show();
                        } else {Toast.makeText(MoneyActivity.this, inpu.toString()+" "+fone+" "+getResources().getString(R.string.Geld_Abgezo2)+".", Toast.LENGTH_SHORT).show();}
                    }
                }
        });
        activ = true;
        builder.show();
    }


    private void saveVals(){
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("DUK",sb_duk.getProgress()).apply();
        mEditor.putInt("SIL",sb_sil.getProgress()).apply();
        mEditor.putInt("HEL",sb_hel.getProgress()).apply();
        mEditor.putInt("KRE",sb_kre.getProgress()).apply();
        mEditor.putInt("SCH",sb_sch.getProgress()).apply();
    }

    @Override
    public void onBackPressed() {
        saveVals();
        finish();
    }

}
