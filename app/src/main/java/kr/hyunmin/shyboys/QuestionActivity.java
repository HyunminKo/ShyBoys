package kr.hyunmin.shyboys;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kr.hyunmin.shyboys.kr.hyunmin.object.DAO;
import kr.hyunmin.shyboys.kr.hyunmin.object.DTO;

public class QuestionActivity extends Actionbar {
    EditText question_content;
    Button insert_q_button;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setActionbar("객체지향설계");

        insert_q_button = (Button) findViewById(R.id.insert_q_button);
        if(MainActivity.isHost == 1)
        insert_q_button.setVisibility(View.INVISIBLE);
        insert_q_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuestion_writePopup();
            }
        });

    }

    public void showQuestion_writePopup(){
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.questionpopup, (ViewGroup) findViewById(R.id.question_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(QuestionActivity.this);

        aDialog.setTitle("Write Question"); //타이틀바 제목
        aDialog.setView(layout); //inti.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);

        question_content = (EditText) layout.findViewById(R.id.Question_edittext);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String content = question_content.getText().toString();
                DAO dao = new DAO();
                DTO dto = new DTO();

                dto.set_content(content);
                dto.set_room_code("Room123");
                dto.set_QorA("Q");
                dto.set_date("2015/11/27");
                if(dao.addDB(dto)){
                    Log.d("checkValue","성공");
                }else
                    Log.d("checkValue","실패");

            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

}
