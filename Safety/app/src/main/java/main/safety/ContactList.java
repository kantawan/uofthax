package main.safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ContactList extends AppCompatActivity implements Serializable {

    private EditText p1,p2,p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        Button toMain =  (Button) findViewById(R.id.back);
        p1 = findViewById(R.id.editText);
        p2 = (EditText)findViewById(R.id.editText2);
        p3 = (EditText)findViewById(R.id.editText3);

        toMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(ContactList.this, Main.class);
                startActivity(backIntent);

                String[] array = {p1.getText().toString(), p2.getText().toString(), p3.getText().toString()};
                saveContacts(array);
            }
        });
    }

    static private ArrayList<String> contacts; // = new ArrayList<>();
    // private String[] contacts = new String[3];

    public void saveContacts(String[] a) {

        // System.arraycopy( a, 0, contacts, 0, a.length );
        contacts = new ArrayList<>(Arrays.asList(a));
        try {
            for (int i = 0; i <= 2; i++) {
                if (contacts.get(i).length()==0) {
                    contacts.remove(i);
                }
            }
        }
        finally {
            int s = contacts.size();
            System.out.println("Hello");
        }
    }

    public static ArrayList<String> getContacts() {
        return contacts;
    }

}
