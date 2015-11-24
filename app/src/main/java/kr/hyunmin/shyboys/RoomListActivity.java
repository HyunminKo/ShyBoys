package kr.hyunmin.shyboys;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class RoomListActivity extends Actionbar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setActionbar_Title();

        ImageButton imageButton = (ImageButton) r_Customview.findViewById(R.id.plus_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(MainActivity.isHost == 1){
                        showHostRoomPopup();
                    }else
                    showUserRoomPopup();
                }
        });

    }
    public void showHostRoomPopup(){
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.host_roompopup, (ViewGroup) findViewById(R.id.h_room_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(RoomListActivity.this);

        aDialog.setTitle("Create Room"); //타이틀바 제목
        aDialog.setView(layout); //inti.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

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
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

}
