package main.safety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;


public class Main extends AppCompatActivity {
    private EditText countDownText;
    private Button countDownButton;

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

                EditText timeView = findViewById(R.id.time_min);
                String timeViewString = timeView.getText().toString();
                String timeViewCut = timeViewString.substring(0,2);
                timeLeft =  Integer.parseInt(timeViewCut) * 60000;
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
        countDownTimer.cancel();
        countDownButton.setText("Start");
        flag = false;

    }

    public void updaterTimer(){
        int minutes = (int) timeLeft / 60000;
        int seconds = (int) timeLeft % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);

    }

    public void trigger(){

    }



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
