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

import org.json.JSONObject;


public class Main extends AppCompatActivity implements Serializable {
    private EditText countDownText;
    private Button countDownButton;
    private SmsManager smsManager = SmsManager.getDefault();

    private CountDownTimer countDownTimer;
    private long timeLeft = 600000;

    private boolean flag;

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

                // deserialize
                try {
                    FileInputStream fis = openFileInput("contacts.txt");
                    ObjectInputStream is = new ObjectInputStream(fis);
                    AppCompatActivity simpleClass = (AppCompatActivity) is.readObject();
                    is.close();
                    fis.close();
                } catch (IOException e) {
                    System.out.println("a turn up ting");
                } catch (ClassNotFoundException e) {
                    System.out.println("Boo hoo");
                }

//                JSONObject json = new JSONObject();
//                try {
//                    BufferedReader br = new BufferedReader(new FileReader("storage.json"));
//                    DropBoxManager.Entry e = json.fromJson(br, DropBoxManager.Entry.class);
//                    Log.d("reading", e.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
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

        countDownText.setText("FUCK");
        smsManager.sendTextMessage("+12893888447", null, "HELP!!", null, null);
        Toast.makeText(this, "Your emergency contacts have been contacted.", Toast.LENGTH_SHORT).show();



    }

//    public void close() {
//        // serialize here
//        try {
//            FileOutputStream fos = context.openFileOutput("contacts.txt", Context.MODE_PRIVATE);
//            ObjectOutputStream os = new ObjectOutputStream(fos);
//            os.writeObject(this);
//            os.close();
//            fos.close();
//        } catch (IOException e) {
//            System.out.println("shit");
//        }
//    }



//    //erwin
//    private CountDownTimer timer;
//    private long  millisLeft;
//    private TextView countText;
//    private int counter = 0;
//    private EditText input;
//    private SmsManager smsManager = SmsManager.getDefault();
//
//    private void trigger() {
//        smsManager.sendTextMessage("+12893888447", null, input.getText().toString(), null, null);
//        Toast.makeText(this, "Your emergency contacts have been contacted.", Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void onStart(View view) {
//        timer = new CountDownTimer(60 * 1000, 1000){
//            @Override
//            public void onTick(long millisLeft){
//                updateTimer();
//            }
//            @Override
//            public void onFinish(){
//                // Call trigger() method!!
//                trigger();
//            }
//        }.start();
//    }
//    // TODO: call onStart in body of onClick.
//
//    public void updateTimer(){
//        int minutes = (int) millisLeft / 60000;
//        int seconds = (int) millisLeft % 60000 / 1000;
//        String timeLeft = "" + minutes + ":";
//        if (seconds < 10) timeLeft += "0";
//        timeLeft += seconds + "";
//
//        countText.setText(timeLeft);
//    }
//


}
