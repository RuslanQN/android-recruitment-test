package droid.ruslanq.investaz_tst.View.QuotData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ruslanq on 02.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    Context context;

    public DBHelper(Context context) {

        super(context, "QuoteDB", null, 1);

    }

    private String createQuotation() { //get table creation script
        return "create table " + QuotDataTemplate.TABLE_KEY + "( "
                + QuotDataTemplate.ID_KEY + " integer primary key autoincrement, "
                + QuotDataTemplate.DIRECTION_KEY + " text, "
                + QuotDataTemplate.NAME_KEY + " text, "
                + QuotDataTemplate.VAL1_KEY + " text, "
                + QuotDataTemplate.VAL2_KEY + " text, "
                + QuotDataTemplate.VAL3_KEY + " text, "
                + QuotDataTemplate.VAL4_KEY + " text, "
                + QuotDataTemplate.SPEC_KEY + " text, "
                + QuotDataTemplate.DATETIME_KEY + " integer "
                + ");";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = createQuotation();
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
