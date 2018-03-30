package sample.kouta.databasesample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;

public class fse extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="cocktailmemo.db";
    private static final int DATABASE_VERSION=1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        StringBuilder sb= new StringBuilder();
        sb.append("CREATE TABLE cocktailmemo(");
        sb.append("_id INTEGER PRIMARY KEY, ");
        sb.append("name TEXT,");
        sb.append("note TEXT,");
        sb.append(");");
        String sql =sb.toString();
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

    public void onSaveButton(View view){
        EditText etNote = findViewById(R.id.etNote);
        String note=etNote.getText().toString();

        DatabaseHelper helper=new DatabaseHelper(CocktailMemoActivity.this);
        SQLiteDatabase db =helper.getWritableDatabase();

        try{
            String sqlDelete ="DELETE FROM cocktailmemo WHERE _id + ?";
            SQLiteStatement stmt=db.compileStatement(sqlDelete);
            stmt.bindLong(1,_cocktailId);
            stmt.executeUpdateDelete();

            String sqlInsert = "INSERT INTO cocktailmemo (_id,name,note) VALUES (?,?,?)";
            stmt=db.compileStatement(sqlInsert);

            stmt.bindLong(1,_cocktailId);
            stmt.bindString(2,_cocktailName);
            stmt.bindString(3,note);

            stmt.executeInsert();
        }
        finally {
            db.close();
        }
    }

    public void onItemClick(AdapterView<?> parent,View view,int position,long id){
        DatabaseHelper helper=new DatabaseHelper(CocktailMemoActivity.this);
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            String sql = "SELECT * FROM cocktailmemo WHERE _id = " + _cocktailId;

            Cursor cursor=db.rawQuery(sql,null);
            String note ="";
            while(cursor.moveToNext()){
                int idxNote = cursor.getColumnIndex("note");
                note = cursor.getString(idxNote);
            }
            EditText etNote=findViewById(R.id.etNote);
            etNote.setText(note);
        }
        finally {
            db.close();
        }
    }
}
