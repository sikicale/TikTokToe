package com.Hypersoft.tiktoktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] dugme = new Button[9]; // def. dugmad
    private Button button_reset;    // reset dugme
    private TextView ispis;
    private Boolean player1_turn;   // ko sad igra 1 ili 2
    private Boolean reset = false;  // da li treba resetovati igru
    private Random random = new Random();

    //za TOST
    private CharSequence text_winX = "Winner player X";
    private CharSequence text_winO = "Winner player O";
    private int duration = Toast.LENGTH_LONG;

    Resetovanje res = new Resetovanje();    //klasa (NIT, Thread) koja proverava
    Thread gameThread;                      // da li treba resetovati igru na kraju


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_button();
        firstTurn();
        if (savedInstanceState != null){
            player1_turn = savedInstanceState.getBoolean("player_turn");
            dugme[0].setText(savedInstanceState.getCharSequence("dugme1"));
            dugme[1].setText(savedInstanceState.getCharSequence("dugme2"));
            dugme[2].setText(savedInstanceState.getCharSequence("dugme3"));
            dugme[3].setText(savedInstanceState.getCharSequence("dugme4"));
            dugme[4].setText(savedInstanceState.getCharSequence("dugme5"));
            dugme[5].setText(savedInstanceState.getCharSequence("dugme6"));
            dugme[6].setText(savedInstanceState.getCharSequence("dugme7"));
            dugme[7].setText(savedInstanceState.getCharSequence("dugme8"));
            dugme[8].setText(savedInstanceState.getCharSequence("dugme9"));
            dugme[0].setTextColor(savedInstanceState.getInt("Color1"));
            dugme[1].setTextColor(savedInstanceState.getInt("Color2"));
            dugme[2].setTextColor(savedInstanceState.getInt("Color3"));
            dugme[3].setTextColor(savedInstanceState.getInt("Color4"));
            dugme[4].setTextColor(savedInstanceState.getInt("Color5"));
            dugme[5].setTextColor(savedInstanceState.getInt("Color6"));
            dugme[6].setTextColor(savedInstanceState.getInt("Color7"));
            dugme[7].setTextColor(savedInstanceState.getInt("Color8"));
            dugme[8].setTextColor(savedInstanceState.getInt("Color9"));
        }
        gameThread = new Thread(res);
        gameThread.start();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("payer_turn",player1_turn);
        savedInstanceState.putCharSequence("dugme1",dugme[0].getText());
        savedInstanceState.putCharSequence("dugme2",dugme[1].getText());
        savedInstanceState.putCharSequence("dugme3",dugme[2].getText());
        savedInstanceState.putCharSequence("dugme4",dugme[3].getText());
        savedInstanceState.putCharSequence("dugme5",dugme[4].getText());
        savedInstanceState.putCharSequence("dugme6",dugme[5].getText());
        savedInstanceState.putCharSequence("dugme7",dugme[6].getText());
        savedInstanceState.putCharSequence("dugme8",dugme[7].getText());
        savedInstanceState.putCharSequence("dugme9",dugme[8].getText());
        savedInstanceState.putInt("Color1",dugme[0].getCurrentTextColor());
        savedInstanceState.putInt("Color2",dugme[1].getCurrentTextColor());
        savedInstanceState.putInt("Color3",dugme[2].getCurrentTextColor());
        savedInstanceState.putInt("Color4",dugme[3].getCurrentTextColor());
        savedInstanceState.putInt("Color5",dugme[4].getCurrentTextColor());
        savedInstanceState.putInt("Color6",dugme[5].getCurrentTextColor());
        savedInstanceState.putInt("Color7",dugme[6].getCurrentTextColor());
        savedInstanceState.putInt("Color8",dugme[7].getCurrentTextColor());
        savedInstanceState.putInt("Color9",dugme[8].getCurrentTextColor());
    }
    public void onButtonReset(View view){
        reset = true;
    }
    public void onButtonClicked(View view){
       for(int i=0;i<9;i++){
            if (view.getId() == dugme[i].getId()){
                if(player1_turn){
                    if (dugme[i].getText() == ""){
                        dugme[i].setTextColor(Color.RED);
                        dugme[i].setText("X");
                        player1_turn = false;
                        ispis.setText("0 turn");
                        check();
                    }
                }else {
                    if (dugme[i].getText() == ""){
                        dugme[i].setTextColor(Color.BLUE);
                        dugme[i].setText("O");
                        player1_turn = true;
                        ispis.setText("X turn");
                        check();
                    }
                }
            }
        }
    }
    public void firstTurn(){
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        if (random.nextInt(2) == 0){
            player1_turn=true;
            ispis.setText("X turn");
        }else{
            player1_turn=false;
            ispis.setText("0 turn");
        }
    }

    public void check(){
        //check X win conditions
        if((dugme[0].getText()=="X") && (dugme[1].getText()=="X") && (dugme[2].getText()=="X")) {
            xWins(0,1,2);
        }
        if((dugme[3].getText()=="X") && (dugme[4].getText()=="X") && (dugme[5].getText()=="X")) {
            xWins(3,4,5);
        }
        if((dugme[6].getText()=="X") && (dugme[7].getText()=="X") && (dugme[8].getText()=="X")) {
            xWins(6,7,8);
        }
        if((dugme[0].getText()=="X") && (dugme[3].getText()=="X") && (dugme[6].getText()=="X")) {
            xWins(0,3,6);
        }
        if((dugme[1].getText()=="X") && (dugme[4].getText()=="X") && (dugme[7].getText()=="X")) {
            xWins(1,4,7);
        }
        if((dugme[2].getText()=="X") && (dugme[5].getText()=="X") && (dugme[8].getText()=="X")) {
            xWins(2,5,8);
        }
        if((dugme[0].getText()=="X") && (dugme[4].getText()=="X") && (dugme[8].getText()=="X")) {
            xWins(0,4,8);
        }
        if((dugme[2].getText()=="X") && (dugme[4].getText()=="X") && (dugme[6].getText()=="X")) {
            xWins(2,4,6);
        }
        //check O win conditions
        if((dugme[0].getText()=="O") && (dugme[1].getText()=="O") && (dugme[2].getText()=="O")) {
            oWins(0,1,2);
        }
        if((dugme[3].getText()=="O") && (dugme[4].getText()=="O") && (dugme[5].getText()=="O")) {
            oWins(3,4,5);
        }
        if((dugme[6].getText()=="O") && (dugme[7].getText()=="O") && (dugme[8].getText()=="O")) {
            oWins(6,7,8);
        }
        if((dugme[0].getText()=="O") && (dugme[3].getText()=="O") && (dugme[6].getText()=="O")) {
            oWins(0,3,6);
        }
        if((dugme[1].getText()=="O") && (dugme[4].getText()=="O") && (dugme[7].getText()=="O")) {
            oWins(1,4,7);
        }
        if((dugme[2].getText()=="O") && (dugme[5].getText()=="O") && (dugme[8].getText()=="O")) {
            oWins(2,5,8);
        }
        if((dugme[0].getText()=="O") && (dugme[4].getText()=="O") && (dugme[8].getText()=="O")) {
            oWins(0,4,8);
        }
        if((dugme[2].getText()=="O") && (dugme[4].getText()=="O") && (dugme[6].getText()=="O")) {
            oWins(2,4,6);
        }
    }
    public void xWins(int a,int b,int c){
        dugme[a].setBackgroundColor(Color.GREEN);
        dugme[b].setBackgroundColor(Color.GREEN);
        dugme[c].setBackgroundColor(Color.GREEN);
        for (int i = 0;i<9;i++){
            dugme[i].setClickable(false);
        }
        ispis.setText("X Wins");
        Toast toast = Toast.makeText(this,text_winX,duration);
        toast.show();
       reset = true;

    }
    public void oWins(int a,int b,int c){
        dugme[a].setBackgroundColor(Color.GREEN);
        dugme[b].setBackgroundColor(Color.GREEN);
        dugme[c].setBackgroundColor(Color.GREEN);
        for (int i = 0;i<9;i++){
            dugme[i].setClickable(false);
        }
        ispis.setText("0 Wins");
        Toast toast = Toast.makeText(this,text_winO,duration);
        toast.show();
        reset = true;
    }
    public class Resetovanje implements Runnable{
        public Resetovanje(){   }

        public void run(){
            while(true) {
                if (reset) {
                    reset = false;
                    try {
                        Thread.sleep(2000);
                        for(int i=0;i<9;i++){
                            dugme[i].setText("");
                            dugme[i].setBackgroundColor(0xA9F6EFEF);
                            dugme[i].setClickable(true);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void init_button(){
        dugme[0] = (Button) findViewById(R.id.button1);
        dugme[1] = (Button) findViewById(R.id.button2);
        dugme[2] = (Button) findViewById(R.id.button3);
        dugme[3] = (Button) findViewById(R.id.button4);
        dugme[4] = (Button) findViewById(R.id.button5);
        dugme[5] = (Button) findViewById(R.id.button6);
        dugme[6] = (Button) findViewById(R.id.button7);
        dugme[7] = (Button) findViewById(R.id.button8);
        dugme[8] = (Button) findViewById(R.id.button9);
        button_reset = (Button) findViewById(R.id.reset);
        ispis = (TextView) findViewById(R.id.textView);
    }
}