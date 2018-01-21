package main.safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ContactList extends AppCompatActivity implements Serializable {

    DatabaseHandler db;
    private Button button_one,button_two,button_three;
    private EditText p1,p2,p3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        db = new DatabaseHandler(this);

        Button toMain =  (Button) findViewById(R.id.back);
        p1 = findViewById(R.id.editText);
        p2 = (EditText)findViewById(R.id.editText2);
        p3 = (EditText)findViewById(R.id.editText3);

        db.addData("0");
        db.addData("0");
        db.addData("0");

        toMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ContactList.this, Main.class);
                startActivity(backIntent);

                //String[] array = {, p2.getText().toString(), p3.getText().toString()};
                //saveContacts(array);
            }
        });

        button_one =  findViewById(R.id.button);
        button_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //contacts[0] = p1.getText().toString();
                //saveContacts(contactArray);
                String num1 = p1.getText().toString();
                db.updateNum(num1, 1);

            }
        });

        Button button_two =  findViewById(R.id.button2);
        button_two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //contacts[1] = p2.getText().toString();
                //saveContacts(contactArray);
                String num2 = p2.getText().toString();
                db.updateNum(num2, 2);
            }
        });

        Button button_three =  findViewById(R.id.button3);
        button_three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //contacts[2] = p3.getText().toString();
                //saveContacts(contactArray);
                String num3 = p3.getText().toString();
                db.updateNum(num3, 3);
            }
        });
    }




}
