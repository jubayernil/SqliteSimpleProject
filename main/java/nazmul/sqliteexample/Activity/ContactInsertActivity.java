package nazmul.sqliteexample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nazmul.sqliteexample.Database.ContactDatabaseSource;
import nazmul.sqliteexample.Model.ContactModel;
import nazmul.sqliteexample.R;

public class ContactInsertActivity extends AppCompatActivity {
    EditText nameEt;
    EditText phoneEt;
    String name;
    String phone;
    ContactDatabaseSource contactDatabaseSource;
    ContactModel contactModel;
    boolean status;
    int id;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactDatabaseSource=new ContactDatabaseSource(this);
        setContentView(R.layout.activity_contact_insert);
        saveBtn= (Button) findViewById(R.id.saveBtn);
        nameEt= (EditText) findViewById(R.id.nameEntry);
        phoneEt= (EditText) findViewById(R.id.phoneEntry);
        id=getIntent().getIntExtra("id",0);
        if(id>0){
            contactModel=contactDatabaseSource.getContactModel(id);
            nameEt.setText(contactModel.getName());
            phoneEt.setText(contactModel.getPhoneNumber());
            saveBtn.setText("update");
        }
    }

    public void save(View view) {
        name=nameEt.getText().toString().trim();
        if(name.length()<=0){
            nameEt.setError("data not found");
        }
        phone=phoneEt.getText().toString();
        contactModel=new ContactModel(name,phone);
        if(id>0){
            status=contactDatabaseSource.updateContact(id,contactModel);
        }else {
            status=contactDatabaseSource.addContact(contactModel);
        }
        Toast.makeText(ContactInsertActivity.this,String.valueOf(status), Toast.LENGTH_SHORT).show();
        Intent homeIntent=new Intent(this,AllContactActivity.class);
        startActivity(homeIntent);

    }
}
