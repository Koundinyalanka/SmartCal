package com.cal.smart.smartcal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    boolean previousCharacterIsDigit = false;
    TextView displayTextView,resultTextView;
    final StringBuilder numberStringBuilder = new StringBuilder();
    final StringBuilder expressionStringBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        displayTextView = (TextView) findViewById(R.id.text_display);
        resultTextView=(TextView) findViewById(R.id.text_result);
    }

    public void onButtonClick(final View view) {
        if (view instanceof Button) {
            final Button button = (Button) view;
            final int buttonId = button.getId();
            switch (buttonId) {
                case R.id.btn_zero:
                    handleDigitInput('0');
                    break;
                case R.id.btn_one:
                    handleDigitInput('1');
                    break;
                case R.id.btn_two:
                    handleDigitInput('2');
                    break;
                case R.id.btn_three:
                    handleDigitInput('3');
                    break;
                case R.id.btn_four:
                    handleDigitInput('4');
                    break;
                case R.id.btn_five:
                    handleDigitInput('5');
                    break;
                case R.id.btn_six:
                    handleDigitInput('6');
                    break;
                case R.id.btn_seven:
                    handleDigitInput('7');
                    break;
                case R.id.btn_eight:
                    handleDigitInput('8');
                    break;
                case R.id.btn_nine:
                    handleDigitInput('9');
                    break;
                case R.id.btn_dot:
                    handleDigitInput('.');
                    break;
                case R.id.btn_divide:
                    handleOperatorInput('/');
                    break;
                case R.id.btn_minus:
                    handleOperatorInput('-');
                    break;
                case R.id.btn_multiple:
                    handleOperatorInput('*');
                    break;
                case R.id.btn_plus:
                    handleOperatorInput('+');
                    break;
                case R.id.btn_equal:
                    evaluation();
                    break;
                default:
                    // Ignore it.
                    break;
            }
        }
    }

    private void handleDigitInput(final char digit) {
        if (!previousCharacterIsDigit) {
            numberStringBuilder.setLength(0);
        }
        numberStringBuilder.append(digit);
        displayTextView.setText(numberStringBuilder);
        previousCharacterIsDigit = true;
    }

    private void handleOperatorInput(final char operator) {
        expressionStringBuilder.append(operator);
        displayTextView.setText(expressionStringBuilder);
        previousCharacterIsDigit = false;
    }

    private void evaluation() {
        expressionStringBuilder.append(numberStringBuilder);
        final ExpressionParser parser = new ExpressionParser(expressionStringBuilder.toString());
        numberStringBuilder.setLength(0);
        numberStringBuilder.append(parser.parse().eval());
        resultTextView.setText(numberStringBuilder);
        previousCharacterIsDigit = true;
        expressionStringBuilder.setLength(0);
    }

}
