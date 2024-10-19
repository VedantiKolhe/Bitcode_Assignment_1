package com.example.bitcode_assignment_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etdAmount;
    private Button btnconvertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etdAmount = findViewById(R.id.etdAmount);
        btnconvertButton = findViewById(R.id.btnconvertButton);

       btnconvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrencyDialog();
            }
        });
    }

    private void showCurrencyDialog() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_currency, null);

        // Set up the dialog builder
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        // Access the RadioGroup in the dialog
        final RadioGroup currencyRadioGroup = dialogView.findViewById(R.id.currencyRadioGroup);

        // Create and show the dialog
        dialogBuilder.setPositiveButton("Convert", (dialogInterface, i) -> {
            // Get the amount entered by the user
            String amountStr = etdAmount.getText().toString();
            if (!amountStr.isEmpty()) {
                double amount = Double.parseDouble(amountStr);

                // Get the selected radio button ID
                int selectedId = currencyRadioGroup.getCheckedRadioButtonId();

                // Perform the conversion based on selected currency
                double convertedValue = 0.0;
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = dialogView.findViewById(selectedId);
                    String selectedCurrency = selectedRadioButton.getText().toString();

                    switch (selectedCurrency) {
                        case "Canadian Dollar":
                            convertedValue = amount * 1.2; // Example conversion rate
                            break;
                        case "US Dollar":
                            convertedValue = amount * 1.1;
                            break;
                        case "British Pound":
                            convertedValue = amount * 0.8;
                            break;
                        case "Chinese Yuan":
                            convertedValue = amount * 6.5;
                            break;
                    }

                    // Display the converted value in the EditText
                    etdAmount.setText(String.valueOf(convertedValue));
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        // Show the dialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
