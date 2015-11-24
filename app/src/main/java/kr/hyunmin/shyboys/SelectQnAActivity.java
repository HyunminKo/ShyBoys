package kr.hyunmin.shyboys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectQnAActivity extends Actionbar {

    Button go_to_q, go_to_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_qna);

        setActionbar("객체지향설계");

        go_to_q = (Button) findViewById(R.id.question_list_Button);
        go_to_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SelectQnAActivity.this,QuestionActivity.class);
                startActivity(intent1);
            }
        });

        go_to_a = (Button) findViewById(R.id.answer_list_Button);
        go_to_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SelectQnAActivity.this,AnswerActivity.class);
                startActivity(intent1);
            }
        });
    }
}
