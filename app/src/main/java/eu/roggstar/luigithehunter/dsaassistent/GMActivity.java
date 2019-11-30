package eu.roggstar.luigithehunter.dsaassistent;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GMActivity extends AppCompatActivity {

    private ListView lv_inititive;
    private ArrayList<HashMap<String,String>> list = new ArrayList<>();
    private SimpleAdapter adapter;
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEdit;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gm);


        this.setTitle(R.string.TitleGM);


        lv_inititive = findViewById(R.id.lv_inititive);


        adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[] {"name","ini"},
                new int[] {android.R.id.text1,android.R.id.text2}

        );

        lv_inititive.setAdapter(adapter);

        mPrefs = getSharedPreferences("GM",0);
        mEdit = mPrefs.edit();
        loadMap();

        lv_inititive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movedialog(position); //TODO
            }
        });
        lv_inititive.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                miscdialog(pos);
                return true;
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddialog();
            }
        });
    }

    private void sortAllItemsInList(){
        HashMap<String, String> currItem;
        HashMap<String, String> nextItem;
        for(int i = 0;i<list.size();i++){
            if(i < list.size()-1) {
                currItem = list.get(i);
                nextItem = list.get(i + 1);

                if(nextItem.get("ini") != null || currItem.get("ini") != null && Integer.parseInt(nextItem.get("ini")) > Integer.parseInt(currItem.get("ini"))) {
                    //list.get(i);
                    list.set(i + 1, currItem); //TODO
                    list.set(i,nextItem);
                    i = -1;
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void loadMap(){
        SharedPreferences oldPrefs;
        SharedPreferences.Editor oldEdit;
        oldPrefs = getSharedPreferences("STAT",0);
        oldEdit = oldPrefs.edit();
        HashMap<String,String> tmpHash = new HashMap(); //HashMap<String,String> tmpHash = new HashMap();

        Set<String> fetch = oldPrefs.getStringSet("List", null);
        if(fetch != null) { //TODO delete in the future this is too old (thats why it is also not translated)
            Toast.makeText(this, "Deine Charaktere werden von der alten zur neuen Liste konvertiert.", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Deswegen haben alle die INI 0. Einfach antippen um dies zu ändern.", Toast.LENGTH_LONG).show();
            for (String str : fetch) {
                tmpHash.put("name", str);
                tmpHash.put("ini", "0");
                list.add(tmpHash);
            }
            oldEdit.remove("List").apply();
            saveMap();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<HashMap<String, String>>>() {
            }.getType();
            ArrayList<HashMap<String, String>> tmp = gson.fromJson(mPrefs.getString("IniList", null), type);
            if (tmp != null) {

                for (int i = 0; i < tmp.size(); i++) {
                    list.add(tmp.get(i));
                }

                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, getString(R.string.GM_EmptyList), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void saveMap(){
        Gson gson = new Gson();
        String arrayList1 = gson.toJson(list);

        mEdit.putString("IniList", arrayList1);
        mEdit.commit();
    }


    private void adddialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");
        builder.setCancelable(false);

        final EditText one = new EditText(this);
        one.setHint("Name");//optional
        final EditText two = new EditText(this);
        two.setHint("INI");//optional

        one.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        two.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.VERTICAL);
        lay.addView(one);
        lay.addView(two);

        builder.setView(lay);

        two.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                return (event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER);


                /*
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
                 */
            }
        });

        builder.setNeutralButton(getString(R.string.InIHinzufugenJa), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean wantToCloseDialog = (one.getText().toString().trim().isEmpty()); //Bolean
                boolean wantToCloseDialog2 = (two.getText().toString().trim().isEmpty());

                if (!wantToCloseDialog && !wantToCloseDialog2) {
                    HashMap<String,String> temp = new HashMap<>();
                    temp.put("name", one.getText().toString());
                    temp.put("ini", two.getText().toString());

                    list.add(temp);
                    sortAllItemsInList();
                    saveMap();
                }
            }
        });

        builder.setNegativeButton(getString(R.string.InIHinzufugenNein), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }



    private void rendialog(final int pos){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.GM_NewName)+" "+list.get(pos).get("name"));
        builder.setCancelable(false);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        builder.setView(input);

        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER);
            }
        });

        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean wantToCloseDialog = (input.getText().toString().trim().isEmpty());
                if (!wantToCloseDialog) {
                    renameItemInList(pos,input.getText().toString(),false);
                }
            }
        });

        builder.show();
    }

    private void miscdialog(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.GM_Bearbeiten));
        builder.setMessage(list.get(pos).get("name"));
        builder.setPositiveButton(getString(R.string.GM_Umbenennen), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                rendialog(pos);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(getString(R.string.GM_Loschen), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeItemFromList(pos);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void removeItemFromList(final int pos){
        list.remove(list.get(pos));
        adapter.notifyDataSetChanged();
        saveMap();
    }

    private void renameItemInList(final int pos,final String name,boolean ini){
        HashMap<String,String> tmp = new HashMap<>();
        if(!ini){tmp.put("name",name);tmp.put("ini",list.get(pos).get("ini"));}else{tmp.put("name",list.get(pos).get("name"));tmp.put("ini",name);}
        list.set(pos,tmp);
        adapter.notifyDataSetChanged();
        if(ini){sortAllItemsInList();}
        saveMap();
    }


   private  void movedialog(final int pos) {
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle(getString(R.string.GM_ChangeINI1)+" "+list.get(pos).get("name")+" "+getString(R.string.GM_ChangeINI2));
       builder.setCancelable(false);

       final EditText input = new EditText(this);
       input.setInputType(InputType.TYPE_CLASS_NUMBER);
       builder.setView(input);

       input.setOnKeyListener(new View.OnKeyListener() {
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               return (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER);
           }
       });

       builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               boolean wantToCloseDialog = (input.getText().toString().trim().isEmpty());
               if (!wantToCloseDialog) {
                   if(TextUtils.isDigitsOnly(input.getText())) {
                       renameItemInList(pos, input.getText().toString(), true);
                   } else {
                       Toast.makeText(GMActivity.this, getString(R.string.error_toomuch), Toast.LENGTH_LONG).show();
                   }
               }
           }
       });

       builder.show();
   }
}