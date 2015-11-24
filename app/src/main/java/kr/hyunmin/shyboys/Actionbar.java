package kr.hyunmin.shyboys;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HA on 2015-11-24.
 */
public class Actionbar extends AppCompatActivity {

    View r_Customview;

    public void setActionbar(String subject) {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater q_Inflater = LayoutInflater.from(this);

        View q_Customview = q_Inflater.inflate(R.layout.custom_actionbar, null);
        TextView q_TitleTextView = (TextView) q_Customview.findViewById(R.id.subject_textview);
        q_TitleTextView.setText(subject);

        actionBar.setCustomView(q_Customview);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFBDD7EE));
    }

    public void setActionbar_Title(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater r_Inflater = LayoutInflater.from(this);

        r_Customview = r_Inflater.inflate(R.layout.custom2_actionbar, null);
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
    }
}
