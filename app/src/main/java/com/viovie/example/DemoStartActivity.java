package com.viovie.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class DemoStartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_start);

        startActivity(new Intent(this, com.viovie.example.landingslide.MainActivity.class));

        TextView rotateTest = (TextView) findViewById(R.id.rotate_text);
        rotateTest.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));

        TextView cardListText = (TextView) findViewById(R.id.card_example_text);
        cardListText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_example_text: {
                startActivity(new Intent(this, com.viovie.example.cardlist.ListActivity.class));
                break;
            }
        }
    }
}
