package com.example.fermer;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Boolean carrot_flag;
    static final String CARROT_KEY = "carrot";
    public Boolean cabbage_flag;
    static final String CABBAGE_KEY = "cabbage";
    public Integer power_click;
    static final String POWER_KEY = "power_click";
    Integer position;

    Integer score;
    static final String SCORE_KEY = "score";
    public Integer getScore()
    {
        return score;
    }

    //сеттер для поля score
    public void setScore(Integer score)
    {
        this.score = score;
        TextView txt = findViewById(R.id.text_score);
        txt.setText(score.toString());
    }
    //сеттер для поля power_click
    public void setPower_click(Integer power_click)
    {
        this.power_click = power_click;
        TextView txt = findViewById(R.id.text_power_click);
        txt.setText(power_click.toString());
    }

    // этот код выполняется когда закрывается дочерняя активити

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // принимаем и сохраняем результаты
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        setScore(intent.getIntExtra(SCORE_KEY,0));
                        setPower_click(intent.getIntExtra(POWER_KEY,1));
                        carrot_flag = intent.getBooleanExtra(CARROT_KEY,false);
                        cabbage_flag = intent.getBooleanExtra(CABBAGE_KEY,false);
                    }


                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = findViewById(R.id.imageView);
        img.setBackgroundResource(R.drawable.potato);
        carrot_flag = false;
        cabbage_flag = false;
        setPower_click(1);
        position = 1;
        setScore(0);
    }

    // увеличеие очков при клике на изображение
    public void click_image(View view)
    {
        setScore(score + (power_click * position));
    }

    // изменяет изображение
    public void click_up(View view)
    {

        if(position == 2 & cabbage_flag)
        {
            position ++;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.cabbage);
        }

        if(position == 1 & carrot_flag)
        {
            position ++;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.carrot);
        }

    }

    // изменяет изображение
    public void click_back(View view)
    {
        if(position == 2 )
        {
            position --;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.potato);
        }
        if(position == 3 )
        {
            position --;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.carrot);
        }
    }
    // переходит в дочернию активити и отправляет ей результаты
    public void upgrade(View view)
    {
        Intent intent = new Intent(this,ActivityUp.class);
        intent.putExtra(SCORE_KEY, score);
        intent.putExtra(POWER_KEY , power_click);
        intent.putExtra(CARROT_KEY,carrot_flag);
        intent.putExtra(CABBAGE_KEY, cabbage_flag);
        mStartForResult.launch(intent);
    }
}