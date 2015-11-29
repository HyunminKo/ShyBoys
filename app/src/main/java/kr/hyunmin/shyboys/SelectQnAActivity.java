package kr.hyunmin.shyboys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.hyunmin.shyboys.kr.hyunmin.object.DTO;

public class SelectQnAActivity extends Actionbar {

    Button go_to_q, go_to_a;
    String subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_qna);

        Intent intent = getIntent();
        subject = intent.getExtras().getString("subject");
        setActionbar(subject);

        go_to_q = (Button) findViewById(R.id.question_list_Button);
        go_to_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAO dao = new DAO(SelectQnAActivity.this);
                DTO[] dto_array = null;
                dto_array = dao.import_content(subject);
            }
        });

        go_to_a = (Button) findViewById(R.id.answer_list_Button);
        go_to_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SelectQnAActivity.this,AnswerActivity.class);
                intent1.putExtra("subject",subject);
                startActivity(intent1);
            }
        });
    }
}
