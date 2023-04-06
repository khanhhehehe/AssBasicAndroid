package com.example.assignment.LopDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assignment.DTO.Lophoc;
import com.example.assignment.Helper.MyDbSinhVien;

import java.util.ArrayList;

public class ClassDao {
    SQLiteDatabase database;
    MyDbSinhVien myDbSinhVien;
    public ClassDao(Context context){
        myDbSinhVien = new MyDbSinhVien(context);
    }
    public void open(){
        database = myDbSinhVien.getWritableDatabase();
    }
    public void close(){
        myDbSinhVien.close();
    }
    public long addNew(Lophoc lophoc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Lophoc.CLM_MALOP,lophoc.getMalop());
        contentValues.put(Lophoc.CLM_TENLOP,lophoc.getTenlop());
        long res = database.insert(Lophoc.TB_NAME,null,contentValues);
        return res;
    }
    public ArrayList<Lophoc> selectAll(){
        ArrayList<Lophoc> dslop = new ArrayList<>();
        String []sql_lop = new String[]{Lophoc.CLM_ID,Lophoc.CLM_MALOP,Lophoc.CLM_TENLOP};
        Cursor cursor = database.query(Lophoc.TB_NAME,sql_lop,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int idlop = cursor.getInt(0);
            String malop = cursor.getString(1);
            String tenlop = cursor.getString(2);

            Lophoc lophoc = new Lophoc();
            lophoc.setIdlop(idlop);
            lophoc.setMalop(malop);
            lophoc.setTenlop(tenlop);

            dslop.add(lophoc);
            cursor.moveToNext();
        }
        return dslop;
    }
    public int UpdateRow(Lophoc lophoc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Lophoc.CLM_MALOP,lophoc.getMalop());
        contentValues.put(Lophoc.CLM_TENLOP,lophoc.getTenlop());
        int res = database.update(Lophoc.TB_NAME,contentValues,"idlop = "+lophoc.getIdlop(),null);
        return res;
    }

    public int DeleteRow(Lophoc lophoc){
        return database.delete(Lophoc.TB_NAME,"idlop = "+lophoc.getIdlop(),null);
    }
}
