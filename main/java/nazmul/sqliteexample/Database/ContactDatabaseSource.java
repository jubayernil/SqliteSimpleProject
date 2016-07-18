package nazmul.sqliteexample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import nazmul.sqliteexample.Model.ContactModel;

/**
 * Created by BITM Trainer - 401 on 7/18/2016.
 */
public class ContactDatabaseSource {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    ContactModel contactModel;

    public ContactDatabaseSource(Context context) {
        databaseHelper=new DatabaseHelper(context);
    }


    public void open(){
        database=databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
    }

    public boolean addContact(ContactModel contactModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contactModel.getName());
        contentValues.put(DatabaseHelper.COL_PHONE,contactModel.getPhoneNumber());


        long inserted=database.insert(DatabaseHelper.TABLE_CONTACT,null,contentValues);

        this.close();
        if(inserted>0){
            return true;
        }else {
            return false;
        }

    }

    public ContactModel getContactModel(int id){
        this.open();

        Cursor cursor=database.query(DatabaseHelper.TABLE_CONTACT,new String[]{DatabaseHelper.COL_ID,DatabaseHelper.COL_NAME,DatabaseHelper.COL_PHONE},DatabaseHelper.COL_ID+" = "+id,null,null,null,null);

        cursor.moveToFirst();

        int mId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
        String mPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));

        cursor.close();
        this.close();
        contactModel=new ContactModel(mId,mName,mPhone);
        return contactModel;
    }

    public ArrayList<ContactModel>getAllContact(){
        ArrayList<ContactModel>contactModels=new ArrayList<>();
        this.open();

        Cursor cursor=database.rawQuery("select * from "+DatabaseHelper.TABLE_CONTACT,null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount();i++){
                int mId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
                String mName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
                String mPhone=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PHONE));
                contactModel=new ContactModel(mId,mName,mPhone);
                cursor.moveToNext();
                contactModels.add(contactModel);
            }
        }
        cursor.close();
        this.close();
        return contactModels;
    }


    public boolean updateContact(int id,ContactModel contactModel){
        this.open();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_NAME,contactModel.getName());
        contentValues.put(DatabaseHelper.COL_PHONE,contactModel.getPhoneNumber());

        int updated=database.update(DatabaseHelper.TABLE_CONTACT,contentValues,DatabaseHelper.COL_ID+" = "+id,null);
        this.close();

        if(updated>0){
            return true;
        }else{
            return false;
        }

    }

    public boolean deleteContact(int id){
        this.open();

        int deleted=database.delete(DatabaseHelper.TABLE_CONTACT,DatabaseHelper.COL_ID+" = "+id,null);

        this.close();
        if(deleted>0){
            return true;
        }
        else {
            return false;
        }
    }


}
