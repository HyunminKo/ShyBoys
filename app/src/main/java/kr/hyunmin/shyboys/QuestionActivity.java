package kr.hyunmin.shyboys;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    Button insert_q_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        insert_q_button = (Button) findViewById(R.id.insert_q_button);
        //insert_q_button.setVisibility(View.INVISIBLE);
        

    }

}
