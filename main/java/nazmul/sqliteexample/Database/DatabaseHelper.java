package nazmul.sqliteexample.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BITM Trainer - 401 on 7/18/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME="contact_manager";
    static final int DATABASE_VERSION=1;

    static final String TABLE_CONTACT="contact_info";
    static final String COL_ID="id";
    static final String COL_NAME="name";
    static final String COL_PHONE="phone";

    static final String CREATE_TABLE_CONTACT="create table "+TABLE_CONTACT+"( "+COL_ID+" integer primary key, "+COL_NAME+" text, "+COL_PHONE+" text);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exist"+TABLE_CONTACT);
        onCreate(sqLiteDatabase);
    }
}
