package com.example.yeye.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnewactivity1= (Button) findViewById(R.id.btnewactivity1);
        RadioGroup RG = (RadioGroup) findViewById(R.id.rGroup);
        RadioButton rsum = (RadioButton) findViewById(R.id.rsum);
        RadioButton rsub = (RadioButton) findViewById(R.id.rsub);
        RadioButton rmul = (RadioButton) findViewById(R.id.rmul);
        RadioButton rdlv = (RadioButton) findViewById(R.id.rdlv);

        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button was clicked
                switch(checkedId) {
                    case R.id.rsum:
                        str = "sum";
                        break;
                    case R.id.rsub:
                        str = "sub";
                        break;
                    case R.id.rmul:
                        str = "mul";
                        break;
                    case R.id.rdlv:
                        str = "dlv";
                        break;
                }
            }
        });
        btnewactivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etnum1 = (EditText) findViewById(R.id.edit1);
                EditText etnum2 = (EditText) findViewById(R.id.edit2);

                Intent intent = new Intent(getApplicationContext(), Second.class);
                intent.putExtra("Num1", Integer.parseInt(etnum1.getText().toString()));
                intent.putExtra("Num2", Integer.parseInt(etnum2.getText().toString()));
                intent.putExtra("RB", str);
                startActivityForResult(intent, 0);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK)
        {
            int hap = data.getIntExtra("hap", 0);

            Toast.makeText(getApplicationContext(), "result = " + hap, Toast.LENGTH_SHORT).show();
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
