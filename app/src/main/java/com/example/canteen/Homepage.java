package com.example.canteen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {
    String idly_nos,pongal_nos,dosa_nos,cb_nos,cfr_nos;
    EditText idly_count,pongal_count,dosa_count,cb_count,cfr_count;
    Button button_i_inc,button_i_dec;
    DB db;
    TextView tvs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        idly_count = findViewById(R.id.editTextNumber);
        pongal_count = findViewById(R.id.editTextNumber2);
        dosa_count = findViewById(R.id.editTextNumber3);
        cb_count = findViewById(R.id.editTextNumber4);
        cfr_count = findViewById(R.id.editTextNumber5);
        button_i_inc = findViewById(R.id.button4);
        button_i_dec = findViewById(R.id.button5);
        tvs = findViewById(R.id.textView15);
        db=new DB(Homepage.this);
    }

    public void inc_idly_cnt(View view){
        idly_nos = idly_count.getText().toString();
        int i_nos = Integer.parseInt(idly_nos);
        i_nos+=1;
        idly_count.setText(Integer.toString(i_nos));
    }

    public void dec_idly_cnt(View view){
        idly_nos = idly_count.getText().toString();
        int i_nos = Integer.parseInt(idly_nos);
        if(i_nos > 0){
            i_nos-=1;
        }
        idly_count.setText(Integer.toString(i_nos));
    }

    public void inc_pongal_cnt(View view){
        pongal_nos = pongal_count.getText().toString();
        int p_nos = Integer.parseInt(pongal_nos);
        p_nos+=1;
        pongal_count.setText(Integer.toString(p_nos));
    }

    public void dec_pongal_cnt(View view){
        pongal_nos = pongal_count.getText().toString();
        int p_nos = Integer.parseInt(pongal_nos);
        if(p_nos > 0){
            p_nos-=1;
        }
        pongal_count.setText(Integer.toString(p_nos));
    }

    public void inc_dosa_cnt(View view){
        dosa_nos = dosa_count.getText().toString();
        int d_nos = Integer.parseInt(dosa_nos);
        d_nos+=1;
        dosa_count.setText(Integer.toString(d_nos));
    }

    public void dec_dosa_cnt(View view){
        dosa_nos = dosa_count.getText().toString();
        int d_nos = Integer.parseInt(dosa_nos);
        if(d_nos > 0){
            d_nos-=1;
        }
        dosa_count.setText(Integer.toString(d_nos));
    }

    public void inc_cb_cnt(View view){
        cb_nos = cb_count.getText().toString();
        int cbnos = Integer.parseInt(cb_nos);
        cbnos+=1;
        cb_count.setText(Integer.toString(cbnos));
    }

    public void dec_cb_cnt(View view){
        cb_nos = cb_count.getText().toString();
        int cbnos = Integer.parseInt(cb_nos);
        if(cbnos > 0){
            cbnos-=1;
        }
        cb_count.setText(Integer.toString(cbnos));
    }

    public void inc_cfr_cnt(View view){
        cfr_nos = cfr_count.getText().toString();
        int cfrnos = Integer.parseInt(cfr_nos);
        cfrnos+=1;
        cfr_count.setText(Integer.toString(cfrnos));
    }

    public void dec_cfr_cnt(View view){
        cfr_nos = cfr_count.getText().toString();
        int cfrnos = Integer.parseInt(cfr_nos);
        if(cfrnos > 0){
            cfrnos-=1;
        }
        cfr_count.setText(Integer.toString(cfrnos));
    }

    public void order_place(View view){
        int final_idly = Integer.parseInt(idly_count.getText().toString());
        int final_pongal = Integer.parseInt(pongal_count.getText().toString());
        int final_dosa = Integer.parseInt(dosa_count.getText().toString());
        int final_cb = Integer.parseInt(cb_count.getText().toString());
        int final_cfr = Integer.parseInt(cfr_count.getText().toString());
        String loc=db.placeOrder(final_idly,final_pongal,final_dosa,final_cb,final_cfr);
        tvs.setText(loc);
    }

}
