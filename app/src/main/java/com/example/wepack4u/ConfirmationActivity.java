package com.example.wepack4u;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        ArrayList<TableRow> rows = intent.getExtras().getParcelable(PaymentActivity.TABLE_KEY);
        String totalPrice = intent.getStringExtra(PaymentActivity.TOTAL_KEY);

        int order = 69; // nice dummy var
        TextView orderNo = findViewById(R.id.order_num);
        String orderNum = "Order No. " + order;
        orderNo.setText(orderNum);

        Calendar c = Calendar.getInstance();
        TextView timestamp = findViewById(R.id.timestamp);
        String datetime =
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + " " +
                c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        timestamp.setText(datetime);

        TableLayout cartDisplay = findViewById(R.id.cart2);
        for (TableRow row : rows) {
            cartDisplay.addView(row);
        }

        TextView total = findViewById(R.id.total2);
        total.setText(totalPrice);

        TextView paymentText = findViewById(R.id.payment_text);
        String text1 = "A payment of $" + totalPrice + " has been made.";
        paymentText.setText(text1);

        int time = 15; // dummy var
        TextView waitingTime = findViewById(R.id.waiting_time);
        String text2 = "Your order will be done in " + time + " minutes.";
        waitingTime.setText(text2);

        Button done = findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return to home page, LoginActivity is used as a dummy
//                Intent intent = new Intent(ConfirmationActivity.this, LoginActivity.class);
//                startActivity(intent);
            }
        });
    }
}
