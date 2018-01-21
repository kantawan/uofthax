package main.safety;

import android.content.Context;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.String;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.content.SharedPreferences;


import org.json.JSONObject;


public class Main extends AppCompatActivity {
    private EditText countDownText;
    private Button countDownButton;


    private CountDownTimer countDownTimer;
    private long timeLeft = 600000;

    private boolean flag;

    static String[] contacts = new String[3];
    //static ArrayList<String> contacts; // = new ArrayList<>();
    public static final String SHARE_PREF = "com.safety.sharepref.num";
    public static final String num1 = "num1";
    public static final String num2 = "num2";
    public static final String num3 = "num3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // contact button
        Button toContact =  findViewById(R.id.contact_list);
        toContact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent contactIntent = new Intent(Main.this, ContactList.class);
                startActivity(contactIntent);
            }

        });

        Button close_button =  findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent contactIntent = new Intent(Main.this, ContactList.class);
//                startActivity(contactIntent);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });

        //timer button
        countDownText = findViewById(R.id.time_min);
        countDownButton = findViewById(R.id.start_button);

        //String textD = timeView.getText().toString() + ":00";
        //countDownText.setText(textD);

        countDownButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                startStopTimer();

            }

        });

    }

    public void startStopTimer(){
        if (flag){
            stopTime();
        } else {
            countTime();
        }
    }

    public void countTime(){

        EditText timeView = findViewById(R.id.time_min);
        String timeViewString = timeView.getText().toString();
        String timeViewCut;



        if (!timeViewString.contains(":")){
            timeViewCut = timeViewString;
        }
        else {
            timeViewCut = timeViewString.substring(0,timeViewString.indexOf(":"));
        }


        timeLeft =  Integer.parseInt(timeViewCut) * 60000;

        countDownTimer = new CountDownTimer(timeLeft, 1000){
            @Override
            public void onTick(long mLeft){
                timeLeft = mLeft;
                updaterTimer();
            }

            @Override
            public void onFinish(){
                // Call trigger() method!!
                trigger();
            }
        }.start();

        countDownButton.setText("Cancel");
        flag = true;


    }

    public void stopTime(){
        timeLeft = 0;
        countDownText.setText("DONE");
        countDownTimer.cancel();
        countDownButton.setText("Start");
        flag = false;
    }

    public void updaterTimer(){
        int minutes = (int) timeLeft / 60000 ;
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);

    }

    public void trigger(){

        //String[] contacts = ContactList.getContacts();
        SharedPreferences data = getSharedPreferences(SHARE_PREF, MODE_PRIVATE);
        contacts[0] = data.getString(num1, "");
        contacts[1] = data.getString(num2, "");
        contacts[2] = data.getString(num3, "");

        Toast.makeText(this, contacts[0], Toast.LENGTH_SHORT).show();
        countDownText.setText("FUCK");

        for (int i =0; i<3; i++){
            if (contacts[i] != null){
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+1" + contacts[i], null, "HELLOOOO!!", null, null);
                    Toast.makeText(this, "Your emergency contacts have been contacted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    System.out.println("damn");
                }

            }
        }

    }

}
