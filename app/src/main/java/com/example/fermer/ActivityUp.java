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


    //сеттер для поля power_click
    public void setPower_click(Integer power_click)
    {
        this.power_click = power_click;
        TextView txt = findViewById(R.id.text_power);
        txt.setText("Сила клика: " + power_click);
    }
    // принимаем значение оз мейн активити
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up);

        Bundle arguments = getIntent().getExtras();
        setPower_click(arguments.getInt("power_click"));
        carrot_flag = arguments.getBoolean("carrot");
        cabbage_flag = arguments.getBoolean("cabbage");
        setScore(arguments.getInt("score"));
        TextView text = findViewById(R.id.text_up);
        text.setText("Апгрейд стоит:  " + (int)(power_click * 10 * Math.sqrt(power_click)));
        TextView txt_power = findViewById(R.id.text_power);
        txt_power.setText("Сила клика: " + power_click);
        if(carrot_flag)
        {
            TextView txt_carrot = findViewById(R.id.text_carrot);
            Button but = findViewById(R.id.but_carrot);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt_carrot);
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

    //сеттер для поля score
    public void setScore(Integer score)
    {
        TextView txt = findViewById(R.id.text_score);
        if(score < power_click * 10 * Math.sqrt(power_click))
        {
            Button but = findViewById(R.id.but_up);
            but.setEnabled(false);
        }
        if(score < 50 && !carrot_flag)
        {
            Button but = findViewById(R.id.but_carrot);
            but.setEnabled(false);
        }
        if(score < 100 && !cabbage_flag)
        {
            Button but = findViewById(R.id.but_cabbage);
            but.setEnabled(false);
        }
        this.score = score;
        txt.setText("Ваши очки:  "+score);
    }

    // увеличиваем силу клика
    public  void clickUp(View view)
    {
        if(score >= (int)(power_click * 10 * Math.sqrt(power_click)))
        {
            setScore(score - (int)(power_click * 10 * Math.sqrt(power_click)));
            TextView txt_up = findViewById(R.id.text_up);
            setPower_click(power_click+1);
            txt_up.setText("Апгрейд стоит:  " + (int)(power_click * 10 * Math.sqrt(power_click)));


        }
    }
    // разблокировываем морковь
    public void click_carrot(View view)
    {
        if(score >= 50)
        {
            carrot_flag = true;
            TextView txt = findViewById(R.id.text_carrot);
            Button but = findViewById(R.id.but_carrot);
            LinearLayout mainLayer = (LinearLayout) findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
            setScore(score - 50);
        }
    }
    // разблокировываем капусту
    public void click_cabbage(View view)
    {
        if(score >= 100)
        {
            cabbage_flag = true;
            TextView txt = findViewById(R.id.text_cabbage);
            Button but = findViewById(R.id.but_cabbage);
            LinearLayout mainLayer = (LinearLayout)findViewById(R.id.master);
            mainLayer.removeView(txt);
            mainLayer.removeView(but);
            setScore(score - 100);
        }
    }

    // умичтожаем дочернию активити и передаём данные мейн активити
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
