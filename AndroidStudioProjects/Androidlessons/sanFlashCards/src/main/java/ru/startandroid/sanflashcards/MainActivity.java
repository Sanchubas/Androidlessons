/**изменения которые необходимо внести
 *попробуем сделать загрузку базы данных
 *
 */

package ru.startandroid.sanflashcards;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends Activity implements OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd,btnBack;
    Button btnFlip, btnNext,btnFill,btndlt,switchLangBtn;
    TextView txtCard;
    EditText etFront, etBack;
    public boolean switchLang = true;
    public boolean flag;    //флаг со значением true or false, при нажатии на кнопку меняет свое значение на противоположное
    //таким образом на одной кнопке записано два разных кода, которые чередуются
    int pos; //текущая позиция курсора
    String cardSideA;
    String cardSideB;

    DBHelper dbHelper; //создаем объект dbHelper класса DBHelper для управления БД. Сам класс описан ниже.

//    SQLiteDatabase db = dbHelper.getWritableDatabase();
//    Cursor c = db.query("mytable", null, null, null, null, null, null);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //при открытии, кнопки еще не нажаты
        pos = 0;

        //мои кнопки и текст вью начало
        btnFlip = (Button) findViewById(R.id.btnFlip);
        btnFlip.setOnClickListener(this);

        switchLangBtn = (Button) findViewById(R.id.switchLangBtn);

        btnFill = (Button) findViewById(R.id.btnFill);
        btnFill.setOnClickListener(this);

        btndlt = (Button) findViewById(R.id.btndlt);
        btndlt.setOnClickListener(this);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        txtCard = (TextView) findViewById(R.id.txtCard);
        //мои кнопки и текст вью конец


        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        etFront = (EditText) findViewById(R.id.etFront);
        etBack = (EditText) findViewById(R.id.etBack);

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(getApplicationContext());
        Log.d(LOG_TAG," - - - create_db() (on create) - - - ");
        dbHelper.create_db();
//        GetCount();
//            txtCard.setText("нажмите Next");
//            setCardSides();



    }

    class DBHelper extends SQLiteOpenHelper {

        private String DB_PATH; // полный путь к базе данных
        private String DB_NAME = "myDB.db";
        private static final int SCHEME = 1; // версия базы данных
        static final String TABLE = "users"; // название таблицы в бд
        // названия столбцов
        static final String COLUMN_ID = "_id";
        static final String COLUMN_NAME = "front";
        static final String COLUMN_YEAR = "back";
        private Context myContext;



        DBHelper(Context context) {
            super(context, "myDB.db", null, SCHEME);
            this.myContext=context;
//            DB_PATH =context.getFilesDir().getPath() + DB_NAME;
//            DB_PATH="/data/data/"+context.getPackageName()+"/databases" +"/" + DB_NAME;

        }

        void create_db(){
            Log.d(LOG_TAG," - - - create_db() (in method) - - - ");
//            InputStream myInput = null;
//            OutputStream myOutput = null;

            //пробуем начало
            Log.d(LOG_TAG," - - - пробуем начало - - - ");

//            try {
                Log.d(LOG_TAG," - - - try - - - ");
                File file = new File("/data/data/ru.startandroid.sanflashcards/databases/myDB.db");

                    this.getReadableDatabase();
                    //получаем локальную бд как поток
//                    myInput = myContext.getAssets().open(DB_NAME);
                    // Путь к новой бд
//                    String outFileName = DB_PATH;
                    // Открываем пустую бд
//                    myOutput = new FileOutputStream(outFileName);

//                    // побайтово копируем данные
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = myInput.read(buffer)) > 0) {
//                        myOutput.write(buffer, 0, length);
//                    }

//                    myOutput.flush();
//                    myOutput.close();
//                    myInput.close();

//            }
//            catch(IOException ex){
//                Log.d(LOG_TAG," - - - catch(IOException ex) - - - ");
//                Log.d("DatabaseHelper", ex.getMessage());
//            }

            Log.d(LOG_TAG," - - - пробуем конец - - - ");
            //пробуем конец

//
//            try {
//                Log.d(LOG_TAG," - - - try - - - ");
//                File file = new File(DB_PATH);
//                if (!file.exists()) {
//                    Log.d(LOG_TAG," - - - if (!file.exists()) - - - ");
//                    this.getReadableDatabase();
//                    //получаем локальную бд как поток
//                    myInput = myContext.getAssets().open(DB_NAME);
//                    // Путь к новой бд
//                    String outFileName = DB_PATH;
//                    // Открываем пустую бд
//                    myOutput = new FileOutputStream(outFileName);
//
//                    // побайтово копируем данные
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = myInput.read(buffer)) > 0) {
//                        myOutput.write(buffer, 0, length);
//                    }
//
//                    myOutput.flush();
//                    myOutput.close();
//                    myInput.close();
//                }
//            }
//            catch(IOException ex){
//                Log.d(LOG_TAG," - - - catch(IOException ex) - - - ");
//                Log.d("DatabaseHelper", ex.getMessage());
//            }
//

//        }

//        public SQLiteDatabase open()throws SQLException {
//
//            return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            Log.d(LOG_TAG, "--- onCreate database ---");
//            db.execSQL("create table mytable ("
//                    + "_id integer primary key autoincrement,"
//                    + "front text,"
//                    + "back text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /**Если номер версии базы данных в приложении отличается от версии
             * базы данных на устройстве, то запускается этот метод onUpgrade
              */
//            Log.d(LOG_TAG, "--- onUpgrade database ---");
        }
    }


    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        ContentValues cv = new ContentValues(); //создаем объект для даннных

        if (switchLang) {  //переменная swichlang изменяется при помощи переключателя switchLangBtn
            cardSideA = "front";  //в одном положении показывается сначала фронт
            cardSideB = "back";
        } else {
            cardSideA = "back";     //во втором положении показывается сначала бэк
            cardSideB = "front";
        }

        switch (v.getId()) {  //начать чтение когда с else
            case R.id.btnFlip: //при помощи переменной flag эта кнопка исполняет два разных кода
                Log.d(LOG_TAG,"- - - - - - - flip - - - - - - - - - - - - - - -");

                if (flag) {   //чтобы понять что происходит начни чтение когда с "else"
                    //с этого момента код запускается при нажатии на кнопку второй раз
                    Log.d(LOG_TAG,"if . . .");
                    c.moveToPosition(pos);
                    int idColIndex = c.getColumnIndex("_id");
                    int cardSideAColIndex = c.getColumnIndex(cardSideA);
                    txtCard.setText(c.getString(cardSideAColIndex)); //устанавливаем в текст вью полученный текст
                    flag = false;

                } else {  // при первом нажатии на кнопку, действие начинается с этого момента
                    Log.d(LOG_TAG,"else . . .");
                    c.moveToPosition(pos);  //курсор устанавливается в позицию "pos", это общая переменная через которую ведется навигация
                    //первоначально в методе OnCreate установленна на 0
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием из переменной cardSideB
                    txtCard.setText(c.getString(cardSideBColIndex));//устанавливаем в текст вью полученный текст
                    flag = true; //установить flag в положение true
                }
                break;
            case R.id.btnNext:
                Log.d(LOG_TAG,"- - - - - - - next - - - - - - - - - - - - - - -");
                c.moveToPosition(pos); //установить курсор в позицию "pos"
                if(c.moveToNext()) //и перейти на следующую строку
                {                   //если c.moveToNext() выдает значение, т.е. есть строка, то выполняется код ниже, иначе else
                    pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"
                    int idColIndex = c.getColumnIndex("_id"); //получить индекс колонки с названием id
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
                    txtCard.setText(c.getString(cardSideBColIndex));
                    flag = true; //установить flag в положение true
                    Log.d(LOG_TAG,"if . . .");
                }else{ //если c.moveToNext() не выдает строку, т.е. последняя строка
                    c.moveToFirst();//то переходим опять на первую строку
                    pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"
                    int idColIndex = c.getColumnIndex("_id"); //получить индекс колонки с названием id
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
                    txtCard.setText(c.getString(cardSideBColIndex));//устанавливаем в текст вью полученный текст
                    flag = true; //установить flag в положение true
                    Log.d(LOG_TAG,"else . . .");
                }
                break;
            case R.id.btnBack:
                c.moveToPosition(pos); //установить курсор в позицию "pos"
                if(c.moveToPrevious()) //и перейти на предыдущую строку
                {                   //если c.moveToPrevious() выдает значение, т.е. есть строка, то выполняется код ниже, иначе else
                    pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
                    txtCard.setText(c.getString(cardSideBColIndex));
                    flag = true; //установить flag в положение true
                }else{ //если c.moveToPrevious() не выдает строку, т.е. первая строка
                    c.moveToLast();//то переходим опять на первую строку
                    pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"
                    int idColIndex = c.getColumnIndex("_id"); //получить индекс колонки с названием id
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
                    txtCard.setText(c.getString(cardSideBColIndex));//устанавливаем в текст вью полученный текст
                    flag = true; //установить flag в положение true
                }
                break;
            case R.id.btnAdd:
                String front = etFront.getText().toString(); // "переменной front" = "из etFront" . "присвоить значение" . "приобразованное  встроку"
                String back = etBack.getText().toString();

                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put  //поместить в объект cv (put стандартное значение)
                        ("front",    // в столбец с именем "front"
                                front); // значение из переменной front
                cv.put("back", back);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv); //вставляем в базу данных db, в таблицу "mytable", строку со значением из cv
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                etFront.setText("");
                etBack.setText("");
                break;
            case R.id.btnFill:
                FillTable();
                break;
            case R.id.btndlt:
                Log.d(LOG_TAG, "--- delete database ---");
                //удаляем таблицу
            db.execSQL("drop table mytable");
//            db.execSQL("DROP TABLE IF EXISTS " + "mytable");
                // создаем таблицу с полями
                Log.d(LOG_TAG, "--- create database ---");
                db.execSQL("create table mytable ("
                        + "_id integer primary key autoincrement,"
                        + "front text,"
                        + "back text" + ");");
                break;
        }
        db.close();
    }

    public void FillTable(){ //записать в таблицу одну строчку с произвольным номером
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        ContentValues cv = new ContentValues();
        c.moveToLast();
        int number;

        if(c.getPosition()!= -1) {
            int idColIndex = c.getColumnIndex("_id"); //получить индекс колонки с названием id
            number = c.getInt(idColIndex)+1;
        }else{number =1;}

        cv.put("front", "word #"+number);
        cv.put("back", "слово №"+number);
        long rowID = db.insert("mytable", null, cv);
        Log.d(LOG_TAG,"id = " + rowID + ", слово №" + number);
                db.close();
        GetCount();
    }

    public void DellFromTable(){

    }

    public void GetCount() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            if (true) {
                int rowCount = c.getCount();
                Log.d(LOG_TAG, "всего строк: " + rowCount);
                db.close();
            } else {
                Log.d(LOG_TAG, "всего строк: " + 0);
            }
        } else {
            Log.d(LOG_TAG, "нет базы данных");
        }
    }

    public void onSwitchLangClick(View view) {
        switchLangToggle();
        setCardSides();
    }

    public void switchLangToggle (){
        if(switchLang == true){
            switchLang = false;

            switchLangBtn.setText("Eng");
        }
        else{switchLang = true;
            switchLangBtn.setText("Рус");
        }
    }

    public void changeFlag (){
        if(flag == true){
            flag = false;
        }
        else{flag = true;
        }
    }

    public void setCardSides() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        c.moveToPosition(pos);
        pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"

        if (switchLang) {
            cardSideA = "front";
            cardSideB = "back";
        } else {
            cardSideA = "back";
            cardSideB = "front";
        }

        int frontColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
        txtCard.setText(c.getString(frontColIndex));
        flag = true; //установить flag в положение true
    }

}


