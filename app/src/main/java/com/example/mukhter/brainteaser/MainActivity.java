package com.example.mukhter.brainteaser;

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
    Button buttongo,button1,button2,button3,button4,playAgainbutton;
    ArrayList<Integer>Answers = new ArrayList<Integer>();
    int locationofcorrectanswer;
    int scoreboard=0;
    TextView text;
    TextView resultview;
    TextView pointsTextview;
    TextView textViewtimer;
    RelativeLayout gameinterface;
    int numberofquestion =0;



    public void playAgain(final View view){
        scoreboard=0;
        numberofquestion=0;
        textViewtimer.setText("30s");
        pointsTextview.setText("0/0");
        resultview.setText("");
        playAgainbutton.setVisibility(View.INVISIBLE);
      CountDownTimer count =  new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textViewtimer.setText(String.valueOf(millisUntilFinished/1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainbutton.setVisibility(View.VISIBLE);
               textViewtimer.setText("0s");

                resultview.setText("Your score "+Integer.toString(scoreboard)+"/"+Integer.toString(numberofquestion));
            }
        }.start();
        generatequestion();

    }


    public void generatequestion(){

        Random rand = new Random();
        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        text.setText(Integer.toString(a)+" + "+Integer.toString(b)); //this sets each number to the given textview
        locationofcorrectanswer=rand.nextInt(4);
        Answers.clear();
        int incorrect;
        for(int i=0;i<4;i++){
            if(i==locationofcorrectanswer){
                Answers.add(a+b);
            }else {
                incorrect=rand.nextInt(51);
                while(incorrect==a+b){
                    incorrect=rand.nextInt(51);
                }
                Answers.add(incorrect);
            }
        }
        button1.setText(Integer.toString(Answers.get(0)));
        button2.setText(Integer.toString(Answers.get(1)));      //* parses the answers into each button based on the logic in the loop
        button3.setText(Integer.toString(Answers.get(2)));
        button4.setText(Integer.toString(Answers.get(3)));


    }
    public void chosenAnswer(View view){

        Log.i("Tag",(String) view.getTag()); //to check if tags work

        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswer))){
           scoreboard++;
            resultview.setText("Correct!");

        }else{
            resultview.setText("Wrong!");

        }
        numberofquestion++;
        pointsTextview.setText(Integer.toString(scoreboard)+"/"+Integer.toString(numberofquestion));
        generatequestion();

    }
    public void Start(View view){
        buttongo.setVisibility(View.INVISIBLE);
        gameinterface.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttongo = (Button)findViewById(R.id.buttongo);
        text =(TextView) findViewById(R.id.textView3);
        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        playAgainbutton=(Button)findViewById(R.id.playAgain);
        resultview = (TextView)findViewById(R.id.resultTextview);
        pointsTextview =(TextView)findViewById(R.id.pointsTextview);
        textViewtimer =(TextView)findViewById(R.id.timerTextview);
        gameinterface =(RelativeLayout)findViewById(R.id.gameInterface);




    }
}
