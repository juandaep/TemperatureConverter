package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_c;
    TextView tv_f;
    TextView tv_k;

    TextView tv_r;

    EditText et_number;
    Button btn;
    Spinner spinner;
    String spinner_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupSpinner();
        setupBtn();
    }

    private void setupSpinner(){
        spinner.setAdapter(createArrayAdapter());
        spinner_item = "Celcius";
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setupBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // double : for decimal value
                double temp = Double.parseDouble(et_number.getText().toString());
                convert(temp, spinner_item);
            }
        });
    }

    private ArrayAdapter createArrayAdapter(){
        final ArrayAdapter result = ArrayAdapter.createFromResource(this, R.array.temp_symbol, android.R.layout.simple_spinner_item);
        result.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return result;
    }

    private void bindViews(){
        tv_c = findViewById(R.id.tv_c);
        tv_f = findViewById(R.id.tv_f);
        tv_k = findViewById(R.id.tv_k);

        tv_r = findViewById(R.id.tv_r);

        et_number = findViewById(R.id.et_number);
        btn = findViewById(R.id.btn);
        spinner = findViewById(R.id.spinner);
    }


    private void convert(double n, String spinner_item) {
        Unit unit = new Unit();
        unit.convert(spinner_item, n);

        tv_c.setText(getString(R.string.result, unit.getCelsius(), 'C'));
        tv_f.setText(getString(R.string.result, unit.getFahrenheit(), 'F'));
        tv_k.setText(getString(R.string.result, unit.getKelvin(), 'K'));

        tv_r.setText(getString(R.string.result, unit.getReamur(), 'R'));
    }


}