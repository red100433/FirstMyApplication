package com.example.yeye.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by yeye on 2015-11-19.
 */
public class Second extends Activity {
    int hapvalue;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent inintent = getIntent();

        final int val1 = inintent.getIntExtra("Num1", 0);
        final int val2 = inintent.getIntExtra("Num2", 0);

        String str = inintent.getStringExtra("RB");
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();


        if(str.equals("sum"))
        {
            hapvalue = val1 + val2;
        }
        else if(str.equals("sub"))
        {
            hapvalue = val1 - val2;
        }
        else if(str.equals("mul"))
        {
            hapvalue = val1 * val2;
        }
        else if(str.equals("dlv"))
        {
            hapvalue = val1 / val2;
        }
        else
        {
            hapvalue = 0;
        }

        Button btback = (Button) findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outintent = new Intent(getApplicationContext(), MainActivity.class);
                outintent.putExtra("hap", hapvalue);
                setResult(RESULT_OK, outintent);
                finish();
            }
        });
    }
}
