package kr.hyunmin.shyboys;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectQnAActivity extends AppCompatActivity {

    Button go_to_q, go_to_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_qna);

        //Actionbar 설정
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater q_Inflater = LayoutInflater.from(this);

        View q_Customview = q_Inflater.inflate(R.layout.custom_actionbar, null);
        TextView q_TitleTextView = (TextView)q_Customview.findViewById(R.id.subject_textview);
        q_TitleTextView.setText("객체지향설계");

        actionBar.setCustomView(q_Customview);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFBDD7EE));

        //여기까지

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
