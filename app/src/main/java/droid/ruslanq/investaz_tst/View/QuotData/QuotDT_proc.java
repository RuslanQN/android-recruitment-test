package droid.ruslanq.investaz_tst.View.QuotData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslanq on 02.10.2017.
 */

public class QuotDT_proc {
    SQLiteDatabase sqlDB;
    Context context;

    public QuotDT_proc(Context context) {
        sqlDB = new DBHelper(context).getWritableDatabase();
        this.context = context;

    }

    public void replace(@NonNull QuotDataTemplate qDT) {
        //insert new value to DB
        ContentValues cv = new ContentValues();

        cv.put(qDT.DIRECTION_KEY, qDT.direction);
        cv.put(qDT.NAME_KEY, qDT.name);
        cv.put(qDT.VAL1_KEY, qDT.val_1);
        cv.put(qDT.VAL2_KEY, qDT.val_2);
        cv.put(qDT.VAL3_KEY, qDT.val_3);
        cv.put(qDT.VAL4_KEY, qDT.val_4);
        cv.put(qDT.SPEC_KEY, qDT.spec);
        cv.put(qDT.DATETIME_KEY, qDT.datetime);
        sqlDB.replace(qDT.TABLE_KEY, null, cv);
    }


    public void insert(@NonNull List<QuotDataTemplate> qDTList) {
        //insert list of value to DB
        for (QuotDataTemplate qDT : qDTList
                ) {
            replace(qDT);
        }

    }


    public List<QuotDataTemplate> getAll() {
        //get all values from DB
        List<QuotDataTemplate> qdtList = new ArrayList<>();
        QuotDataTemplate qDT;

        Cursor c = sqlDB.query(QuotDataTemplate.TABLE_KEY, null, null, null, null, null, QuotDataTemplate.NAME_KEY);
        if (c.moveToFirst()) {
            do {
                qDT = new QuotDataTemplate();

                qDT.name = c.getString(c.getColumnIndex(QuotDataTemplate.NAME_KEY));
                qDT.direction = c.getString(c.getColumnIndex(QuotDataTemplate.DIRECTION_KEY));
                qDT.val_1 = c.getString(c.getColumnIndex(QuotDataTemplate.VAL1_KEY));
                qDT.val_2 = c.getString(c.getColumnIndex(QuotDataTemplate.VAL2_KEY));
                qDT.val_3 = c.getString(c.getColumnIndex(QuotDataTemplate.VAL3_KEY));
                qDT.val_4 = c.getString(c.getColumnIndex(QuotDataTemplate.VAL4_KEY));
                qDT.spec = c.getString(c.getColumnIndex(QuotDataTemplate.SPEC_KEY));
                qDT.datetime = c.getString(c.getColumnIndex(QuotDataTemplate.DATETIME_KEY));
                qdtList.add(qDT);
            }
            while (c.moveToNext());
        }
        return qdtList;
    }

}
