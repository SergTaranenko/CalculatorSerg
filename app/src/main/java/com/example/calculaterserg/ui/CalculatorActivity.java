package com.example.calculaterserg.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculaterserg.R;
import com.example.calculaterserg.domain.CalculatorImpl;
import com.example.calculaterserg.domain.Constants;
import com.example.calculaterserg.domain.Operation;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class CalculatorActivity extends AppCompatActivity implements ICalculatorView {

    private final CalculatorPresenter presenter = new CalculatorPresenter(this, this, new CalculatorImpl());
    private TextView result;

    private SharedPreferences sharedPreferences;
    private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.MY_PREFERENCES, MODE_PRIVATE);
        settings = findViewById(R.id.settings);

        result = findViewById(R.id.result);

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, SettingsActivity.class);
            startActivityForResult(intent, RESULT_OK);
        });

        checkNightModeActivated();

        Map<Integer, Integer> digitsMap = new HashMap<>();

        digitsMap.put(R.id.button_1, 1);
        digitsMap.put(R.id.button_2, 2);
        digitsMap.put(R.id.button_3, 3);
        digitsMap.put(R.id.button_4, 4);
        digitsMap.put(R.id.button_5, 5);
        digitsMap.put(R.id.button_6, 6);
        digitsMap.put(R.id.button_7, 7);
        digitsMap.put(R.id.button_8, 8);
        digitsMap.put(R.id.button_9, 9);
        digitsMap.put(R.id.button_0, 0);

        View.OnClickListener digitCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.digitalKeyPressed(digitsMap.get(view.getId()));
            }
        };

        findViewById(R.id.button_1).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_2).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_3).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_4).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_5).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_6).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_7).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_8).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_9).setOnClickListener(digitCLickListener);
        findViewById(R.id.button_0).setOnClickListener(digitCLickListener);

        Map<Integer, Operation> operatorsMap = new HashMap<>();

        operatorsMap.put(R.id.button_share, Operation.DIV);
        operatorsMap.put(R.id.button_multi, Operation.MULT);
        operatorsMap.put(R.id.button_plus, Operation.ADD);
        operatorsMap.put(R.id.button_minus, Operation.SUB);

        View.OnClickListener operatorCLickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.operatorKeyPressed(operatorsMap.get(view.getId()));
            }
        };

        findViewById(R.id.button_share).setOnClickListener(operatorCLickListener);
        findViewById(R.id.button_multi).setOnClickListener(operatorCLickListener);
        findViewById(R.id.button_plus).setOnClickListener(operatorCLickListener);
        findViewById(R.id.button_minus).setOnClickListener(operatorCLickListener);

        findViewById(R.id.button_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.dotPressed();
            }
        });

        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.equalsPressed();
            }
        });
    }

    @Override
    public void displayResult(String s) {
        result.setText(s);
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(Constants.KEY_NIGHT_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != RESULT_CANCELED) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == RESULT_OK) {
            saveNightModeState(data.getExtras().getBoolean(Constants.KEY_NIGHT_MODE));
            recreate();
        }
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_NIGHT_MODE, nightMode).apply();
    }
}
