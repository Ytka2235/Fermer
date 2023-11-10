package com.example.fermer;

import static com.example.fermer.MainActivity.CARROT_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityUp extends AppCompatActivity
{
    Integer score;
    Integer power_click;
    public Boolean carrot_flag;
    public Boolean cabbage_flag;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up);
        Bundle arguments = getIntent().getExtras();
        setScore(arguments.getInt("score"));
        power_click = arguments.getInt("power_click");
        carrot_flag = arguments.getBoolean("carrot");
        cabbage_flag = arguments.getBoolean("cabbage");
        TextView text = findViewById(R.id.text_up);
        text.setText("Апгрейд стоит:  " + (int)(power_click * 10 * Math.sqrt(power_click)));
        if(carrot_flag)
        {
            TextView txt = findViewById(R.id.text_carrot);
            Button but = findViewById(R.id.but_carrot);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
        }
        if(cabbage_flag)
        {
            TextView txt = findViewById(R.id.text_cabbage);
            Button but = findViewById(R.id.but_cabbage);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
        }
    }

    public void setScore(Integer score)
    {
        TextView txt = findViewById(R.id.text_score);
        this.score = score;
        txt.setText("Ваши очки:  "+score);
    }

    public  void clickUp(View view)
    {
        if(score >= (int)(power_click * 10 * Math.sqrt(power_click)))
        {
            setScore(score - (int)(power_click * 10 * Math.sqrt(power_click)));
            power_click++;
        }
    }
    public void click_carrot(View view)
    {
        if(score >= 100)
        {
            carrot_flag = true;
            TextView txt = findViewById(R.id.text_carrot);
            Button but = findViewById(R.id.but_carrot);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
            setScore(score - 100);
        }
    }

    public void click_cabbage(View view)
    {
        if(score >= 1000)
        {
            cabbage_flag = true;
            TextView txt = findViewById(R.id.text_cabbage);
            Button but = findViewById(R.id.but_cabbage);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
            setScore(score - 1000);
        }
    }

    public void clickBack(View view)
    {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.SCORE_KEY, score);
        intent.putExtra(MainActivity.POWER_KEY , power_click);
        intent.putExtra(MainActivity.CARROT_KEY,carrot_flag);
        intent.putExtra(MainActivity.CABBAGE_KEY, cabbage_flag);
        setResult(RESULT_OK,intent);
        finish();
    }







}
