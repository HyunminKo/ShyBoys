package kr.hyunmin.shyboys;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RoomListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Actionbar 설정
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater r_Inflater = LayoutInflater.from(this);

        View r_Customview = r_Inflater.inflate(R.layout.custom2_actionbar, null);
        TextView r_TitleTextView = (TextView)r_Customview.findViewById(R.id.ShyBoys_textview);
        r_TitleTextView.setText("ShyBoys");

        ImageButton imageButton = (ImageButton) r_Customview.findViewById(R.id.plus_button);
        imageButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Clicked!",
                            Toast.LENGTH_LONG).show();
                }
        });

        actionBar.setCustomView(r_Customview);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFBDD7EE));

        //여기까지

    }

}
