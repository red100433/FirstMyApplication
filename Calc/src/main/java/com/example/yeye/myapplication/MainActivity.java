package com.example.yeye.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText edit1, edit2;
    Button btadd, btsub, btmul, btdiv, btrdm;
    TextView textresult;
    String num1, num2;
    Double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("simple calculate");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        btadd = (Button) findViewById(R.id.btadd);
        btsub = (Button) findViewById(R.id.btsub);
        btmul = (Button) findViewById(R.id.btmul);
        btdiv = (Button) findViewById(R.id.btdiv);
        btrdm = (Button) findViewById(R.id.btrmd);
        textresult = (TextView) findViewById(R.id.textresult);

        btadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) throws NumberFormatException{
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if((edit1.getText().toString() == null) || (edit2.getText().toString() == null)) {
                     Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        result = Double.parseDouble(num1) + Double.parseDouble(num2);
                        textresult.setText("result : " + result.toString());
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btsub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if((edit1.getText().toString() == null) || (edit2.getText().toString() == null)) {
                     Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        result = Double.parseDouble(num1) - Double.parseDouble(num2);
                        textresult.setText("result : " + result.toString());
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btmul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if((edit1.getText().toString() == null) || (edit2.getText().toString() == null)) {
                    Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        result = Double.parseDouble(num1) * Double.parseDouble(num2);
                        String num = String.format("%.1f", result);
                        textresult.setText("result : " + num);
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btdiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                if((edit1.getText().toString() == null) || (edit2.getText().toString() == null)) {
                     Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        if(Double.parseDouble(num2) == 0) {
                            Toast.makeText(getApplicationContext(), "num2 value = 0 error", Toast.LENGTH_LONG).show();
                            textresult.setText("result : infinity");
                        }
                        else {
                            result = Double.parseDouble(num1) / Double.parseDouble(num2);
                            String num = String.format("%.1f", result);
                            textresult.setText("result : " + num);
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btrdm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if((edit1.getText().toString() == null) || (edit2.getText().toString() == null)) {
                     Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    try {
                        if(Double.parseDouble(num2) == 0) {
                            Toast.makeText(getApplicationContext(), "num2 value = 0 error", Toast.LENGTH_LONG).show();
                            textresult.setText("result : infinity");
                        }
                        else
                        {
                            result = Double.parseDouble(num1) % Double.parseDouble(num2);
                            textresult.setText("result : " + result.toString());
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        Toast.makeText(getApplicationContext(), "input value", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
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
