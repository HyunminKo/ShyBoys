package kr.hyunmin.shyboys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomListActivity extends Actionbar implements AdapterView.OnItemClickListener {
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
    ArrayList<String> roomcode;
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
<<<<<<< HEAD
        roomcode = new ArrayList<String>();
        room_list = (ListView)findViewById(R.id.listView2);
=======
        room_list = (ListView) findViewById(R.id.listView2);
>>>>>>> 19721bfb9d716692a049aaae79b3ab11509e6599

        if (MainActivity.isHost == 1) {
            Create_DB(1); //host db 생성
            host_DB = context.openOrCreateDatabase(h_DBname, Context.MODE_PRIVATE, null);
            h_cursor = host_DB.rawQuery("SELECT * FROM ROOM", null);
            h_cursor.moveToFirst();
            while (!h_cursor.isAfterLast()) {
                roomcode.add(h_cursor.getString(0));
                rooms.add(h_cursor.getString(1));
                h_cursor.moveToNext();
            }
            h_cursor.close();
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
            adapter.notifyDataSetChanged();
            room_list.setAdapter(adapter);

        } else {
            Create_DB(0); //user db 생성
            user_DB = context.openOrCreateDatabase(u_DBname, Context.MODE_PRIVATE, null);
            u_cursor = user_DB.rawQuery("SELECT * FROM ROOM", null);
            u_cursor.moveToFirst();
            while (!u_cursor.isAfterLast()) {
                roomcode.add(u_cursor.getString(0));
                rooms.add(u_cursor.getString(1));
                u_cursor.moveToNext();
            }
            u_cursor.close();
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
            room_list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        ImageButton imageButton = (ImageButton) r_Customview.findViewById(R.id.plus_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.isHost == 1) {
                    showHostRoomPopup();

                    Log.d("checkFLG", "DB 데이터 삽입");
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
                    adapter.notifyDataSetChanged();
                    room_list.setAdapter(adapter);


                } else {
                    showUserRoomPopup();
                }
            }
        });

        room_list.setOnItemClickListener(this);

    }






    public void Create_DB(int a) {
        Log.d("checkFLG", "DB생성 메소드");
        if (a == 1) {
            host_DB = context.openOrCreateDatabase(h_DBname, Context.MODE_PRIVATE, null);
            host_DB.execSQL("CREATE TABLE if not exists " + tablename + "("
                    + "h_roomcode"
                    + "," + " subject"
                    + "," + " h_name" + ")");
            Log.d("checkFLG", "DB생성 완료");
        } else {
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

    public void showHostRoomPopup() {
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
                Log.d("checkValue", subject.getText().toString());
                Log.d("checkValue", h_name.getText().toString());

                host_DB.execSQL("INSERT INTO " + tablename + "(h_roomcode, subject, h_name) VALUES"
                        + "('" + h_roomcode.getText().toString()
                        + "','" + subject.getText().toString()
                        + "','" + h_name.getText().toString() + "')");

                h_cursor = host_DB.rawQuery("SELECT * FROM ROOM", null);
                h_cursor.moveToFirst();
                while (!h_cursor.isAfterLast()) {
<<<<<<< HEAD
                    roomcode.add(h_cursor.getString(0));
                    rooms.add(h_cursor.getString(1));
=======
                    rooms.add(h_cursor.getString(0));
>>>>>>> 19721bfb9d716692a049aaae79b3ab11509e6599
                    h_cursor.moveToNext();
                }
                h_cursor.close();
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
                adapter.notifyDataSetChanged();
                room_list.setAdapter(adapter);
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

    public void showUserRoomPopup() {
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

                user_DB.execSQL("INSERT INTO " + tablename + "(u_roomcode, u_subject, u_name, QorA, in_content, in_date) VALUES"
                        + "('" + u_roomcode.getText().toString()
                        + "','" + null
                        + "','" + u_name.getText().toString()
                        + "','" + null
                        + "','" + null
                        + "','" + null + "')");

                u_cursor = user_DB.rawQuery("SELECT * FROM ROOM", null);
                u_cursor.moveToFirst();
                while (!u_cursor.isAfterLast()) {
<<<<<<< HEAD
                    roomcode.add(u_cursor.getString(0));
                    rooms.add(u_cursor.getString(1));
=======
                    rooms.add(u_cursor.getString(0));
>>>>>>> 19721bfb9d716692a049aaae79b3ab11509e6599
                    u_cursor.moveToNext();
                }
                u_cursor.close();
                adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_font, rooms);
                adapter.notifyDataSetChanged();
                room_list.setAdapter(adapter);
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        String c_list = rooms.get(i);
<<<<<<< HEAD
        String b_list = roomcode.get(i);

        Intent intent = new Intent(RoomListActivity.this,SelectQnAActivity.class);
        intent.putExtra("roomcode",b_list);
        intent.putExtra("subject",c_list);
=======
        Intent intent = new Intent(RoomListActivity.this, SelectQnAActivity.class);
        intent.putExtra("arr_text", c_list);
>>>>>>> 19721bfb9d716692a049aaae79b3ab11509e6599
        startActivity(intent);
    }




}



