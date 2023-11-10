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

    Integer score;
    static final String SCORE_KEY = "score";
    public Integer getScore()
    {
        return score;
    }
    public void setScore(Integer score)
    {
        this.score = score;
        TextView txt = findViewById(R.id.textView);
        txt.setText(score.toString());
    }


    Integer position;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        setScore(intent.getIntExtra(SCORE_KEY,0));
                        power_click = intent.getIntExtra(POWER_KEY,1);
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
        power_click = 1;
        position = 1;
        setScore(0);
    }
    public void click_image(View view)
    {
        setScore(score + (power_click * position));
    }

    public void click_up(View view)
    {
        if(position == 1 & carrot_flag)
        {
            position ++;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.carrot);
        }
        if(position == 2 & cabbage_flag)
        {
            position ++;
            ImageView img = findViewById(R.id.imageView);
            img.setBackgroundResource(R.drawable.cabbage);
        }
    }

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