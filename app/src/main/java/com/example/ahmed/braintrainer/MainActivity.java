package com.example.ahmed.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView ;
    TextView pointsTextView;
    TextView endTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    int locationOfCorrectAnswer;
    int score = 0;
    RelativeLayout gameRelativeLayout;
    RelativeLayout endRelativeLayout;

    public void playAgain(final View view){
        gameRelativeLayout.setVisibility(view.VISIBLE);
        endRelativeLayout.setVisibility(view.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30S");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
       // playAgainButton.setVisibility(view.INVISIBLE);


        generateQuestion();
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) +"S" );
            }

            @Override
            public void onFinish() {
                gameRelativeLayout.setVisibility(view.INVISIBLE);
                endRelativeLayout.setVisibility(View.VISIBLE);
                //playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0S");
                endTextView.setText("Your score: "+ Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion(){
        Random random = new Random();

        int a = random.nextInt(51);
        int b = random.nextInt(51);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{
                incorrectAnswer = random.nextInt(11) + (a + b - 5);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(11) + (a + b - 5);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
int numberOfQuestions = 0;

    public void chooseAnswer(View view){
        if(view.getTag().toString() .equals(Integer.toString(locationOfCorrectAnswer))){
            //Log.i("text","CorrectQ");
            score ++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!!");
        }

        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public  void start(View view){
        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.goButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
         button0 = (Button) findViewById(R.id.button0);
         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        endTextView = (TextView) findViewById(R.id.endTextView);
        endRelativeLayout = (RelativeLayout) findViewById(R.id.scoreRelativeLayout);

    }
}
