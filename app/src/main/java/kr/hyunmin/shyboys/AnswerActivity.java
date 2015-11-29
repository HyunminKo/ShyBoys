package kr.hyunmin.shyboys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import kr.hyunmin.shyboys.kr.hyunmin.object.DTO;

public class AnswerActivity extends Actionbar {

    Button insert_a_button;
    EditText answer_content;
    ListView answer_listView;
    ArrayAdapter adapter_answer;
    public static boolean endOfThread = false;
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
         answer_listView = (ListView) findViewById(R.id.answer_listView);
         DAO dao = new DAO(AnswerActivity.this);
         DTO[] dto_array;
         dto_array = dao.import_content(subject);

//         String[] content_Answer = new String[dto_array.length];
//         for(int i = 0 ; i < dto_array.length;i++){
//             if(dto_array[i].get_QorA().equals("A"))
//                 content_Answer[i] = dto_array[i].get_content();
//         }
//
//         adapter_answer = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, content_Answer);
//         answer_listView.setAdapter(adapter_answer);
//         adapter_answer.notifyDataSetChanged();

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
        answer_content = (EditText) layout.findViewById(R.id.Answer_edittext);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String content = answer_content.getText().toString();
                DAO dao = new DAO(AnswerActivity.this);
                DTO dto = new DTO();

                dto.set_content(content);
                dto.set_room_code("Room123");
                dto.set_QorA("A");
                dto.set_date("11/29");
                dao.insert_QuestionAndAnswer(dto);

            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }

}
