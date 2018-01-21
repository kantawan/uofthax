package main.safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ContactList extends AppCompatActivity implements Serializable {

    private EditText p1,p2,p3;
    static String[] contacts = new String[3];
    //static ArrayList<String> contacts; // = new ArrayList<>();
    public static final String SHARE_PREF = "com.safety.sharepref.num";
    public static final String num1 = "num1";
    public static final String num2 = "num2";
    public static final String num3 = "num3";

//    private EditText phone1;
//    private EditText phone2;
//    private EditText phone3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        Button toMain =  (Button) findViewById(R.id.back);
        p1 = findViewById(R.id.editText);
        p2 = (EditText)findViewById(R.id.editText2);
        p3 = (EditText)findViewById(R.id.editText3);

        // Set phone #s for user to see
        SharedPreferences data = getSharedPreferences(SHARE_PREF, MODE_PRIVATE);
        p1.setText(data.getString(num1, ""));
        p2.setText(data.getString(num2, ""));
        p3.setText(data.getString(num3, ""));

        toMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ContactList.this, Main.class);
                startActivity(backIntent);

                //String[] array = {, p2.getText().toString(), p3.getText().toString()};
                //saveContacts(array);
            }
        });

        Context context = getApplicationContext();
        CharSequence text1 = "Number saved!";
        CharSequence text2 = "Invalid number!!";
        int duration = Toast.LENGTH_SHORT;

        final Toast toast1 = Toast.makeText(context, text1, duration);
        final Toast toast2 = Toast.makeText(context, text2, duration);

        Button button_one =  findViewById(R.id.button);
        button_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //saveContacts(contactArray);
                if (valid_num(num3)) {
                    contacts[0] = p1.getText().toString();
                    saveData(num1, p1.getText().toString());
                    toast1.show();
                } else {
                    toast2.show();
                }
            }
        });

        Button button_two =  findViewById(R.id.button2);
        button_two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //saveContacts(contactArray);
                if (valid_num(num3)) {
                    contacts[1] = p2.getText().toString();
                    saveData(num2, p2.getText().toString());
                    toast1.show();
                } else {
                    toast2.show();
                }
            }
        });

        Button button_three =  findViewById(R.id.button3);
        button_three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (valid_num(num3)) {
                    contacts[2] = p3.getText().toString();
                    saveData(num3, p3.getText().toString());
                    toast1.show();
                } else {
                    toast2.show();
                }
            }
        });
    }

    public void saveData(String key,String num) {
        SharedPreferences shared = getSharedPreferences(SHARE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

        editor.putString(key, num);
        editor.apply();
    }

    // Will validate for Canadian standard numbers only atm.
    public boolean valid_num(String num) {

        return isNum(num) && num.length()==9;
    }

    //helper for valid_num()
    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    public static String[] getContacts() {
        return contacts;
    }

}
