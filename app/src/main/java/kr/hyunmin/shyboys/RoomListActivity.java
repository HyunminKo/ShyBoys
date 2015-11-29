package kr.hyunmin.shyboys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class RoomListActivity extends Actionbar implements AdapterView.OnItemClickListener{
    Context context = this;


    //HOST
    EditText h_roomcode;
    EditText subject;
    EditText h_name;
    Cursor h_cursor;
    SQLiteDatabase host_DB;
    String h_DBname = "In_HOST.db";

    //USER
    EditText u_roomcode;
    EditText u_name;
    Cursor u_cursor;
    SQLiteDatabase user_DB;
    String u_DBname = "In_USER.db";
    String u_subject = null;

    String tablename = "ROOM";
    ArrayList<String> rooms;
    ArrayAdapter<String> adapter;
    ListView room_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("checkUserFLAG", "클래스생성");
        Log.d("checkFLG", "룸클래스 생성");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionbar_Title();
        rooms = new ArrayList<String>();
        room_list = (ListView)findViewById(R.id.listView2);

         /*if(MainActivity.isHost==1){
            h_cursor = host_DB.rawQuery("SELECT * FROM ROOM", null);
            h_cursor.moveToFirst();
            while (!h_cursor.isAfterLast()) {
                rooms.add(h_cursor.getString(0));
                Log.d("rooms배열값 : ", rooms.toString());
                h_cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();
            h_cursor.close();
        }*/

        ImageButton imageButton = (ImageButton) r_Customview.findViewById(R.id.plus_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.isHost == 1) {
                    Create_DB(1);
                    Log.d("checkFLG", "DB생성 완료2");
                    showHostRoomPopup();
                    Log.d("checkFLG", "DB 데이터 삽입");
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font , rooms);
                    adapter.notifyDataSetChanged();
                    room_list.setAdapter(adapter);
                } else {
                    Log.d("checkUserFLAG", "DB준비");
                    Create_DB(0);
                    showUserRoomPopup();
                    Log.d("checkFLG", "DB 데이터 삽입");
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
                    adapter.notifyDataSetChanged();
                    room_list.setAdapter(adapter);
                }
            }
        });

        room_list.setOnItemClickListener(this);
    }

    public void Create_DB(int a){
        Log.d("checkFLG", "DB생성 메소드");
        if(a==1){
            host_DB = context.openOrCreateDatabase(h_DBname, Context.MODE_PRIVATE, null);
            host_DB.execSQL("CREATE TABLE if not exists " + tablename + "("
                    + "h_roomcode"
                    + "," + " subject"
                    + "," + " h_name" + ")");
            Log.d("checkFLG", "DB생성 완료");
        }else{
            Log.d("checkUserFLAG", "DB준비메소드 들어옴");
            user_DB = context.openOrCreateDatabase(u_DBname, Context.MODE_PRIVATE, null);
            user_DB.execSQL("CREATE TABLE if not exists " + tablename + "("
                    + "u_roomcode"
                    + "," + " u_subject"
                    + "," + " u_name"
                    + "," + " QorA"
                    + "," + " in_content"
                    + "," + " in_date" + ")");
            Log.d("checkUserFLAG", "DB생성완료");
        }

    }

    public void showHostRoomPopup(){
        Log.d("checkFLG", "HOST POPUP 창");
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.host_roompopup, (ViewGroup) findViewById(R.id.h_room_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(RoomListActivity.this);

        aDialog.setTitle("Create Room"); //타이틀바 제목
        aDialog.setView(layout); //host_roompopup.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);
        h_roomcode = (EditText) layout.findViewById(R.id.h_Roomcode_edittext);
        subject = (EditText) layout.findViewById(R.id.Subject_edittext);
        h_name = (EditText) layout.findViewById(R.id.h_Name_edittext);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.d("checkValue", h_roomcode.getText().toString());
                Log.d("checkValue",subject.getText().toString());
                Log.d("checkValue", h_name.getText().toString());

                host_DB.execSQL("INSERT INTO " + tablename + "(h_roomcode, subject, h_name) VALUES"
                        + "('" + h_roomcode.getText().toString()
                        + "','" + subject.getText().toString()
                        + "','" + h_name.getText().toString() + "')");

                h_cursor = host_DB.rawQuery("SELECT * FROM ROOM", null);
                h_cursor.moveToFirst();
                while (!h_cursor.isAfterLast()) {
                    rooms.add(h_cursor.getString(0));
                    Log.d("rooms배열값 : ", rooms.toString());
                    h_cursor.moveToNext();
                }
                adapter.notifyDataSetChanged();
                h_cursor.close();
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }
    public void showUserRoomPopup(){
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.user_roompopup, (ViewGroup) findViewById(R.id.u_room_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(RoomListActivity.this);

        aDialog.setTitle("Create Room"); //타이틀바 제목
        aDialog.setView(layout); //inti.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);
        u_roomcode = (EditText) layout.findViewById(R.id.u_Roomcode_edittext);
        u_name = (EditText) layout.findViewById(R.id.u_Name_edittext);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.d("checkValue", u_roomcode.getText().toString());
                Log.d("checkValue", u_name.getText().toString());

                /*
                if(u_roomcode.getText().toString().equals("abc012")) {//룸코드 비교후
                    //해당하는 과목 불러오기
                    u_subject = null;
                }
                */

                user_DB.execSQL("INSERT INTO " + tablename + "(u_roomcode, u_subject, u_name, QorA, in_content, in_date) VALUES"
                        + "('" + u_roomcode.getText().toString()
                        + "','" + null
                        + "','" + u_name.getText().toString()
                        + "','" + null
                        + "','" + null
                        + "','" + null + "')");

                u_cursor = null;
                u_cursor = user_DB.rawQuery("SELECT * FROM ROOM", null);

                while (u_cursor.moveToNext()) {
                    rooms.add(u_cursor.getString(0));
                    Log.d("rooms배열값 : ", rooms.toString());
                }
                u_cursor.close();
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String c_list = rooms.get(i);
        Intent intent = new Intent(RoomListActivity.this,SelectQnAActivity.class);
        intent.putExtra("arr_text",c_list);
        startActivity(intent);
    }
}
