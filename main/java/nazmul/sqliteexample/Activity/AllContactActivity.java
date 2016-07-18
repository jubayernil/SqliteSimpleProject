package nazmul.sqliteexample.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import nazmul.sqliteexample.Database.ContactDatabaseSource;
import nazmul.sqliteexample.Model.ContactModel;
import nazmul.sqliteexample.R;

public class AllContactActivity extends AppCompatActivity {
    ContactDatabaseSource contactDatabaseSource;
    ListView contactListView;
    ArrayList<ContactModel>contactModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);
        contactDatabaseSource=new ContactDatabaseSource(this);
        contactListView= (ListView) findViewById(R.id.contactList);
        contactModels=contactDatabaseSource.getAllContact();
        ArrayAdapter<ContactModel>contactModelArrayAdapter=new ArrayAdapter<ContactModel>(this,android.R.layout.simple_list_item_1,contactModels);
        contactListView.setAdapter(contactModelArrayAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent singleIntent=new Intent(getApplicationContext(),SingleDetailsActivity.class);
                singleIntent.putExtra("id",contactModels.get(i).getId());
                startActivity(singleIntent);
            }
        });

    }

    public void insert(View view) {
        Intent entryIntent=new Intent(this,ContactInsertActivity.class);
        startActivity(entryIntent);
    }
}
