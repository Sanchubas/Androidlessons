package ru.startandroid.v007sanflashcards;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

/**изменения которые необходимо внести
 * 1. При нажатии на кнопку Add автоматом очищаются строки ввода
 */

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
        dbHelper = new DBHelper(this);


        GetCount();


            txtCard.setText("В базе данных нет слов");
//            setCardSides();

    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            //удаляем таблицу
//            db.execSQL("drop table mytable;");
//            db.execSQL("DROP TABLE IF EXISTS " + "mytable");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "front text,"
                    + "back text" + ");");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /**Если номер версии базы данных в приложении отличается от версии
             * базы данных на устройстве, то запускается этот метод onUpgrade
              */
            Log.d(LOG_TAG, "--- onUpgrade database ---");
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
                    int idColIndex = c.getColumnIndex("id");
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
                    int idColIndex = c.getColumnIndex("id"); //получить индекс колонки с названием id
                    int cardSideBColIndex = c.getColumnIndex(cardSideB); //получить индекс колонки с названием front
                    txtCard.setText(c.getString(cardSideBColIndex));
                    flag = true; //установить flag в положение true
                    Log.d(LOG_TAG,"if . . .");
                }else{ //если c.moveToNext() не выдает строку, т.е. последняя строка
                    c.moveToFirst();//то переходим опять на первую строку
                    pos = c.getPosition(); //заменить значение переменной "pos" на новое, чтобы заработала кнопка "Flip"
                    int idColIndex = c.getColumnIndex("id"); //получить индекс колонки с названием id
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
                    int idColIndex = c.getColumnIndex("id"); //получить индекс колонки с названием id
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
                        + "id integer primary key autoincrement,"
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
            int idColIndex = c.getColumnIndex("id"); //получить индекс колонки с названием id
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


