package sample.kouta.databasesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CocktailMemoActivity : AppCompatActivity() {

    var _cocktailId=-1
    var _cocktailName:String=""
    var _tvCocktailName:TextView?=null
    var _btnSave: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_memo)

        _tvCocktailName=findViewById(R.id.tvCocktailName)
        _btnSave=findViewById(R.id.btnSave)
        var lvCocktail=findViewById<ListView>(R.id.lvCocktail)
        lvCocktail.setOnItemClickListener(ListItemClickListener())
    }

    fun onSaveButtonClick(view:View){
        var etNote=findViewById<EditText>(R.id.etNote)
        var note=etNote.getText().toString()
        var helper=DatabaseHelper(this@CocktailMemoActivity)
        var db=helper.getWritableDatabase()

        try {
            var sqlDelete="DELETE FROM cocktailmemo WHERE _id = ?"
            var stmt=db.compileStatement(sqlDelete)
            stmt.bindLong(1, _cocktailId as Long)
            stmt.executeUpdateDelete()

            var sqlInsert="INSERT INTO cocktailmemo (_id,name,note) VALUES (?,?,?)"
            stmt=db.compileStatement(sqlInsert)

            stmt.bindLong(1,_cocktailId as Long)
            stmt.bindString(2,_cocktailName)
            stmt.bindString(3,note)

            stmt.executeInsert()
        }
        finally {
            db.close()
        }

        _tvCocktailName!!.setText(getString(R.string.tv_name))
        etNote.setText("")
        _btnSave!!.setEnabled(false)
    }
     inner class ListItemClickListener:AdapterView.OnItemClickListener{
         override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
         _cocktailId=position
         _cocktailName= parent!!.getItemAtPosition(position) as String
         _tvCocktailName!!.setText(_cocktailName)
         _btnSave!!.setEnabled(true)

             var helper=DatabaseHelper(this@CocktailMemoActivity)
             var db=helper.writableDatabase
             try {
                 var sql="SELECT * FROM cocktailmemo WHERE _id = " + _cocktailId

                 var cursor=db.rawQuery(sql,null)
                 var note=""
                 while (cursor.moveToNext()){
                     var idxNote=cursor.getColumnIndex("note")
                     note=cursor.getString(idxNote)
                 }

                 var etNote=findViewById<EditText>(R.id.etNote)
                 etNote.setText(note)
             }
             finally {
                 db.close()
             }
         }
     }

}
