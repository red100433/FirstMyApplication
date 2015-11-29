package com.example.yeye.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    EditText ed;
    DatePicker dp;
    Button but;
    TextView tv, toasttext;
    View dpview, toastview;
    String filename="", path;
    String viewstr="";
    final int ONE = 1;
    final int TWO = 2;
    final int THREE = 3;
    final int FOUR = 4;
    final int FIVE = 5;
    File sdcard, mydir;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.text1);
        ed = (EditText) findViewById(R.id.edit1);
        but = (Button) findViewById(R.id.but1);

        sdcard = Environment.getExternalStorageDirectory();
        path = sdcard.getAbsolutePath() + String.format("/mydiary");
        mydir = new File(path);
        if(!mydir.exists())
        {
            mydir.mkdirs();
        }

        path += String.format("/");


        cal = Calendar.getInstance();
        int cyear = cal.get(Calendar.YEAR);
        int cmonth = cal.get(Calendar.MONTH);
        int cday = cal.get(Calendar.DAY_OF_MONTH);
        filename = Integer.toString(cyear) + "_" + Integer.toString(cmonth + 1) + "_" + Integer.toString(cday) + ".txt";
        viewstr = Integer.toString(cyear) + " " + Integer.toString(cmonth + 1) + " " + Integer.toString(cday);
        tv.setText(viewstr);
        String fp = path + filename;
        String str = readDiary(fp);
        ed.setText(str);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dpview = (View) View.inflate(MainActivity.this, R.layout.datepicker, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setView(dpview);
                dp = (DatePicker) dpview.findViewById(R.id.datap1);
                int cyear = cal.get(Calendar.YEAR);
                int cmonth = cal.get(Calendar.MONTH);
                int cday = cal.get(Calendar.DAY_OF_MONTH);

                filename = Integer.toString(cyear) + "_" + Integer.toString(cmonth + 1) + "_" + Integer.toString(cday) + ".txt";
                viewstr = Integer.toString(cyear) + " " + Integer.toString(cmonth + 1) + " " + Integer.toString(cday);
                dp.init(cyear, cmonth, cday, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        filename = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt";
                        //filename 초기값 설정해줘야함
                        viewstr = Integer.toString(year) + " " + Integer.toString(monthOfYear + 1) + " " + Integer.toString(dayOfMonth);
                    }
                });
                dlg.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //이벤트처리해야돼
                        tv.setText(viewstr);
                        if(viewstr.equals(""))
                            tv.setText("Change Day");
                        else {
                            String fp = path + filename;

                            String str = readDiary(fp);
                            ed.setText(str);
                            but.setEnabled(true);
                        }
                    }
                });
                dlg.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);
                        toastview = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                        toasttext = (TextView) toastview.findViewById(R.id.toasttext);
                        toasttext.setText("cancel");

                        toast.setView(toastview);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String fp = path + filename;
                    File file = new File(fp);
                    FileOutputStream outfs = new FileOutputStream(file);
                    String str = ed.getText().toString();
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getApplicationContext(), fp + " save file", Toast.LENGTH_SHORT).show();
                } catch (IOException e) { }
            }
        });
    }


    String readDiary(String filename)
    {
        String diarystr = null;
        FileInputStream infs;

        try{
            infs = new FileInputStream(filename);
            byte [] txt = new byte[500];
            infs.read(txt);
            infs.close();
            diarystr = (new String(txt)).trim();
            ed.setHint(null);
            but.setText("changed");
        } catch (IOException e)
        {
            ed.setHint("no diary");
            but.setText("new save");
        }
        return diarystr;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.add(0, ONE, Menu.NONE, "ReRead Diary");
        menu.add(0, TWO, Menu.NONE, "DELETE Diary");

        SubMenu submenu = menu.addSubMenu("Font Setting");
        submenu.add(1, THREE, Menu.NONE, "Large");
        submenu.add(1, FOUR, Menu.NONE, "Medium");
        submenu.add(1, FIVE, Menu.NONE, "Small");
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case ONE:
                String fp = path + filename;
                String str = readDiary(fp);
                ed.setText(str);
                break;
            case TWO:
                new AlertDialog.Builder(this)

                        .setMessage(filename + " Delete?")
                        .setNegativeButton("cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialoginterface, int i) {

                                    }
                                })
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String fp = path + filename;
                                File file = new File(fp);
                                file.delete();
                                ed.setText("");
                                ed.setHint("no diary");
                                tv.setText("Day");
                            }
                        }).show();

                break;
            case THREE:
                ed.setTextSize(25);
                break;
            case FOUR:
                ed.setTextSize(20);
                break;
            case FIVE:
                ed.setTextSize(15);
                break;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
