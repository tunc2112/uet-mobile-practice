package ie.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import ie.app.R;

public class MainActivity extends AppCompatActivity {
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
        donateButton = findViewById(R.id.donateButton);
        if (donateButton != null) {
            Log.v("Donate", "Really got the donate button");
        }
        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(10000);
        amountPicker = findViewById(R.id.amountPicker);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuReport:
                startActivity(new Intent(this, Report.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void donateButtonPressed(View view) {
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = radioId == R.id.PayPal ? "PayPal" : "Direct";
        int amount = amountPicker.getValue();
        totalDonated += amount;
        progressBar.setProgress(totalDonated);
        Log.v("Donate", "Donate Pressed! with amount " + amount + ", method: " + method);
        Log.v("Donate", "Current total " + totalDonated);
    }
}