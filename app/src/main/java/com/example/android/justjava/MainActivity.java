/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */
package com.example.android.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 2;
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox_meat);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkbox_cheese);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        RadioButton cappucinoRadioButton = (RadioButton) findViewById(R.id.radiobox1);
        boolean hasCappucino = cappucinoRadioButton.isChecked();
        RadioButton expressoRadioButton = (RadioButton) findViewById(R.id.radiobox2);
        boolean hasExpresso = expressoRadioButton.isChecked();

        EditText textName = (EditText)findViewById(R.id.album_description_view);
        String name1      =  textName.getText().toString();
        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate, hasCappucino, hasExpresso);
        String priceMessage = createOrderSummary(price, name1, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
        }

    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate, boolean hasCappucino, boolean hasExpresso ) {
        int price = 0;
        if (hasCappucino == true) {
            price = quantity * 4;
        }
        if (hasExpresso == true) {
            price = quantity * 5;
        }
        if (hasWhippedCream == true) {
            price = price + (quantity * 1);
        }
        if (hasChocolate == true) {
            price = price + (quantity * 2);
        }
        return price;
    }

    public String createOrderSummary(int price, String name, boolean hasWhipped1, boolean hasWhipped2) {
        String orderSummary = getString(R.string.order_summary_name, name);
        orderSummary = orderSummary + "\nAdd Whipped Cream?" + hasWhipped1;
        orderSummary = orderSummary + "\nAdd Chocolate?" + hasWhipped2;
        orderSummary = orderSummary + "\nQuantity:" + quantity;
        orderSummary = orderSummary + "\nTotal: $" + price;
        orderSummary = orderSummary + "\nThank you!";
        return orderSummary;
    }

    public void increment(View view) {
        quantity = quantity +1;
        if (quantity > 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity>0) {
        quantity = quantity - 1;
        }
        else {
            Toast.makeText(this, "You cannot have less than 0 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}