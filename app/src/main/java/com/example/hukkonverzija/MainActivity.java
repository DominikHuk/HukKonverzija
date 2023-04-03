package com.example.hukkonverzija;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hukkonverzija.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    // Exchange rates as of April 3, 2023
    private static final double USD_EXCHANGE_RATE = 0.144234;
    private static final double EUR_EXCHANGE_RATE = 0.132829;
    private static final double CHF_EXCHANGE_RATE = 0.132167;
    private EditText hrkEditText;
    private TextView usdTextView, eurTextView, chfTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hrkEditText = findViewById(R.id.hrk_edit_text);
        usdTextView = findViewById(R.id.usd_text_view);
        eurTextView = findViewById(R.id.eur_text_view);
        chfTextView = findViewById(R.id.chf_text_view);
        hrkEditText.addTextChangedListener(hrkTextWatcher);
    }

    private final TextWatcher hrkTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                double hrkAmount = Double.parseDouble(s.toString());
                updateExchangeRates(hrkAmount);
            } catch (NumberFormatException e) {
                usdTextView.setText("");
                eurTextView.setText("");
                chfTextView.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void updateExchangeRates(double hrkAmount) {
        double usdAmount = hrkAmount * USD_EXCHANGE_RATE;
        double eurAmount = hrkAmount * EUR_EXCHANGE_RATE;
        double chfAmount = hrkAmount * CHF_EXCHANGE_RATE;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        usdTextView.setText(currencyFormat.format(usdAmount));
        eurTextView.setText(currencyFormat.format(eurAmount));
        chfTextView.setText(currencyFormat.format(chfAmount));
    }
}
