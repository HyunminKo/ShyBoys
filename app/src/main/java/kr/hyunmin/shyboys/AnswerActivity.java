package kr.hyunmin.shyboys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AnswerActivity extends Actionbar {

    Button insert_a_button;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        subject = intent.getExtras().getString("subject");
        setActionbar(subject);

        insert_a_button = (Button) findViewById(R.id.insert_a_button);
        if(MainActivity.isHost == 0)
        insert_a_button.setVisibility(View.INVISIBLE);
        insert_a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer_writePopup();
                Intent intent1 = new Intent(AnswerActivity.this,RoomListActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void showAnswer_writePopup(){
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.answerpopup, (ViewGroup) findViewById(R.id.answer_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(AnswerActivity.this);

        aDialog.setTitle("Write Answer"); //타이틀바 제목
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
