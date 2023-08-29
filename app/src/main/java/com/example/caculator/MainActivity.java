package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StringBuilder inputBuilder = new StringBuilder();
    private TextView inputTV, outputTV;
    private double result = 0;
    private String operator = "";
    private String fullExpression = "";

    Button button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,buttonAdd,
            buttonMultiply,buttonDivision,buttonSub,buttonPower,buttonPercent,buttonEqual,
            buttonClear,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTV = findViewById(R.id.inputTV);
        outputTV = findViewById(R.id.outputTV);

        // Initialize your buttons here
        button0 = findViewById(R.id.zeroBTN);
        button1 = findViewById(R.id.oneBTN);
        button2 = findViewById(R.id.twoBTN);
        button3 = findViewById(R.id.threeBTN);
        button4 = findViewById(R.id.fourBTN);
        button5 = findViewById(R.id.fiveBTN);
        button6 = findViewById(R.id.sixBTN);
        button7 = findViewById(R.id.sevenBTN);
        button8 = findViewById(R.id.eightBTN);
        button9 = findViewById(R.id.nineBTN);

        buttonAdd = findViewById(R.id.sumBTN);
        buttonMultiply = findViewById(R.id.multpBTN);
        buttonDivision = findViewById(R.id.divisionBTN);
        buttonSub = findViewById(R.id.subBTN);
        buttonPower = findViewById(R.id.powerBTN);
        buttonPercent = findViewById(R.id.persentBTN);
        buttonEqual = findViewById(R.id.equalBTN);
        buttonClear = findViewById(R.id.acBTN);
        buttonDot = findViewById(R.id.dotBTN);

        // Set click listeners for number buttons
        button0.setOnClickListener(v -> appendInput("0"));
        button1.setOnClickListener(v -> appendInput("1"));
        button2.setOnClickListener(v -> appendInput("2"));
        button3.setOnClickListener(v -> appendInput("3"));
        button4.setOnClickListener(v -> appendInput("4"));
        button5.setOnClickListener(v -> appendInput("5"));
        button6.setOnClickListener(v -> appendInput("6"));
        button7.setOnClickListener(v -> appendInput("7"));
        button8.setOnClickListener(v -> appendInput("8"));
        button9.setOnClickListener(v -> appendInput("9"));

        // Set click listeners for operator buttons
        buttonAdd.setOnClickListener(v -> handleOperator("+"));
        buttonSub.setOnClickListener(v -> handleOperator("-"));
        buttonMultiply.setOnClickListener(v -> handleOperator("*"));
        buttonDivision.setOnClickListener(v -> handleOperator("/"));
        buttonPower.setOnClickListener(v -> handleOperator("^"));
        buttonPercent.setOnClickListener(v -> handleOperator("%"));

        // Handle equal button click
        buttonEqual.setOnClickListener(v -> performCalculation());

        // Handle clear button click
        buttonClear.setOnClickListener(v -> clearInput());

        // Handle dot button click
        buttonDot.setOnClickListener(v -> appendInput("."));
    }

    private void appendInput(String value) {
        inputBuilder.append(value);
        fullExpression += value;
        inputTV.setText(fullExpression);
    }
    private void handleOperator(String newOperator) {
        if (inputBuilder.length() > 0) {
            if (!operator.isEmpty()) {
                performCalculation();
            }
            result = Double.parseDouble(inputBuilder.toString());
            inputBuilder.setLength(0);
            operator = newOperator;
            fullExpression += " " + newOperator + " ";
            updateInputTextView();
        }
    }

    private void performCalculation() {
        if (inputBuilder.length() > 0) {
            double currentInput = Double.parseDouble(inputBuilder.toString());
            switch (operator) {
                case "+":
                    result += currentInput;
                    break;
                case "-":
                    result -= currentInput;
                    break;
                case "*":
                    result *= currentInput;
                    break;
                case "/":
                    if (currentInput != 0) {
                        result /= currentInput;
                    } else {
                        outputTV.setText("Cannot divide by zero");
                        return;
                    }
                    break;
                case "^":
                    result = Math.pow(result, currentInput);
                    break;
                case "%":
                    result = result * (currentInput / 100);
                    break;
            }

            boolean isInteger = result == (int) result;

            String formattedResult;
            if (isInteger) {
                formattedResult = String.valueOf((int) result);
            } else {
                formattedResult = String.format("%.4f", result);
            }

            fullExpression = formattedResult;
            inputBuilder.setLength(0);
            operator = "";
            updateInputTextView();
            outputTV.setText(formattedResult);
        }

    }

    private void clearInput() {
        inputBuilder.setLength(0);
        result = 0;
        operator = "";
        fullExpression = "";
        updateInputTextView();
        outputTV.setText("0");
    }

    private void updateInputTextView() {
        inputTV.setText(fullExpression);
    }
}
