package com.example.temperatureconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText temperatureInput;
    private TextView convertedOutput;
    private Spinner scaleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureInput = findViewById(R.id.temperatureInput);
        convertedOutput = findViewById(R.id.convertedOutput);
        scaleSpinner = findViewById(R.id.scaleSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temperature_scales, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(adapter);

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double inputTemperature = Double.parseDouble(temperatureInput.getText().toString());
            int selectedScale = scaleSpinner.getSelectedItemPosition();

            double convertedCelsius = 0;
            double convertedFahrenheit = 0;
            double convertedKelvin = 0;

            switch (selectedScale) {
                case 0: // Celsius
                    convertedCelsius = inputTemperature;
                    convertedFahrenheit = (inputTemperature * 9/5) + 32;
                    convertedKelvin = inputTemperature + 273.15;
                    break;
                case 1: // Fahrenheit
                    convertedCelsius = (inputTemperature - 32) * 5/9;
                    convertedFahrenheit = inputTemperature;
                    convertedKelvin = (inputTemperature + 459.67) * 5/9;
                    break;
                case 2: // Kelvin
                    convertedCelsius = inputTemperature - 273.15;
                    convertedFahrenheit = (inputTemperature * 9/5) - 459.67;
                    convertedKelvin = inputTemperature;
                    break;
            }

            String result = String.format("Celsius: %.2f\nFahrenheit: %.2f\nKelvin: %.2f", convertedCelsius, convertedFahrenheit, convertedKelvin);
            convertedOutput.setText(result);
        } catch (NumberFormatException e) {
            convertedOutput.setText("Invalid Input");
        }
    }
}
