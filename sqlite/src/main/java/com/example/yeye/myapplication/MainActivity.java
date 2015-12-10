package com.example.yeye.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    myDBhelper myhelper;
    EditText edit1, edit2, editname, editnum;
    Button btinit, btinput, btselect, btchange, btdelete;
    SQLiteDatabase sqldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        editname = (EditText) findViewById(R.id.editname);
        editnum = (EditText) findViewById(R.id.editnum);

        btinit = (Button) findViewById(R.id.btinit);
        btinput = (Button) findViewById(R.id.btinput);
        btselect = (Button) findViewById(R.id.btselect);
        btchange = (Button) findViewById(R.id.btchange);
        btdelete = (Button) findViewById(R.id.btdelete);
        myhelper = new myDBhelper(this);

        btinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = myhelper.getWritableDatabase();
                myhelper.onUpgrade(sqldb, 1, 2);
                sqldb.close();
            }
        });

        btinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = myhelper.getWritableDatabase();
                sqldb.execSQL("INSERT INTO groupTBL VALUES ('" + edit1.getText().toString() + "'," +edit2.getText().toString()+");" );
                btselect.callOnClick();
                sqldb.close();
                Toast.makeText(getApplicationContext(), "input", Toast.LENGTH_SHORT).show();
            }
        });

        btselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = myhelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqldb.rawQuery("SELECT * FROM groupTBL;", null);

                String strname = "gruopName" + "\r\n" + "--------" + "\r\n";
                String strnum = "gruopNum" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext())
                {
                    strname += cursor.getString(0) + "\r\n";
                    strnum += cursor.getString(1) + "\r\n";
                }

                editname.setText(strname);;
                editnum.setText(strnum);

                cursor.close();
                sqldb.close();
            }
        });

        btchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = myhelper.getWritableDatabase();
                sqldb.execSQL("UPDATE groupTBL SET gNumber = '" + edit2.getText().toString() + "' WHERE gName = '" + edit1.getText().toString() + "';");
                btselect.callOnClick();
                sqldb.close();
            }
        });

        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqldb = myhelper.getWritableDatabase();
                sqldb.execSQL("DELETE FROM groupTBL WHERE gName = '" + edit1.getText().toString() + "';");
                btselect.callOnClick();
                sqldb.close();
            }
        });
    }
    public class myDBhelper extends SQLiteOpenHelper
    {
        public myDBhelper(Context context)
        {
            super(context, "groupDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

