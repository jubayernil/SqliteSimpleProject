package nazmul.sqliteexample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import nazmul.sqliteexample.Database.ContactDatabaseSource;
import nazmul.sqliteexample.Model.ContactModel;
import nazmul.sqliteexample.R;

public class SingleDetailsActivity extends AppCompatActivity {
    int id;
    TextView nameText;
    TextView phoneText;
    ContactDatabaseSource contactDatabaseSource;
    ContactModel contactModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_details);
        nameText= (TextView) findViewById(R.id.nameText);
        phoneText= (TextView) findViewById(R.id.phoneText);
        id=getIntent().getIntExtra("id",0);
        contactDatabaseSource=new ContactDatabaseSource(this);
        contactModel=contactDatabaseSource.getContactModel(id);
        nameText.setText(contactModel.getName());
        phoneText.setText(contactModel.getPhoneNumber());

    }

    public void update(View view) {
        Intent updateIntent=new Intent(this,ContactInsertActivity.class);
        updateIntent.putExtra("id",id);
        startActivity(updateIntent);
    }

    public void delete(View view) {
        contactDatabaseSource.deleteContact(id);
        Intent homeIntent=new Intent(this,AllContactActivity.class);
        startActivity(homeIntent);
    }
}
