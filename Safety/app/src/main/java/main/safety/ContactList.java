package main.safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class ContactList extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);



        Button toMain =  (Button) findViewById(R.id.back);
        toMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ContactList.this, Main.class);
                startActivity(backIntent);
            }
        });


        // Button countText = (Button) findViewById(R.id.start);
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


}
