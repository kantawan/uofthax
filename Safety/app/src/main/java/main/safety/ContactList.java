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
    }
}
