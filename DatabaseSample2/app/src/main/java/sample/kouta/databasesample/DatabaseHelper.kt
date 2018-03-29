package sample.kouta.databasesample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

    companion object {
        private val DATABASE_NAME = "cocktailmemo.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sb=StringBuilder()
        sb.append("CREATE TABLE cocktailmemo(")
        sb.append("_id INTEGER PRIMARY KEY, ")
        sb.append("name TEXT,")
        sb.append("note TEXT,")
        sb.append(");")
        var sql =sb.toString()
        db!!.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}