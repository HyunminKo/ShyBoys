package kr.hyunmin.shyboys;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity{

    DAO dao;
    EditText join_id;
    EditText join_pw;
    EditText login_id;
    EditText login_pw;

    Button start_button;//진행하기 버튼
    Button join_Button;//참여하기 버튼
    static int isHost=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_button = (Button)findViewById(R.id.start_Button);
        join_Button=(Button)findViewById(R.id.join_Button);

        join_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//참여하기 버튼 누르면 방목록창으로 엑티비티 넘김
                isHost=0;
                Intent intent1 = new Intent(MainActivity.this,RoomListActivity.class);
                startActivity(intent1);

            }
        });
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHost = 1;
                showLoginPopup();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showLoginPopup(){
        dao = new DAO(MainActivity.this);
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.loginpopup,(ViewGroup) findViewById(R.id.login_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(MainActivity.this);

        aDialog.setTitle("Login"); //타이틀바 제목
        aDialog.setView(layout); //inti.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);
        //그냥 닫기버튼을 위한 부분
        login_id = (EditText) layout.findViewById(R.id.Login_id_edittext);
        login_pw = (EditText) layout.findViewById(R.id.Login_pw_edittext);
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aDialog.setNeutralButton("회원가입", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                showJoinPopup();
            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dao.login(login_id.getText().toString(),login_pw.getText().toString());
            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }
    public void showJoinPopup(){
        dao = new DAO(MainActivity.this);
        Context mContext = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        //R.layout.init는 xml 파일명이고  R.id.popup은 보여줄 레이아웃 아이디
        View layout = inflater.inflate(R.layout.joinpopup,(ViewGroup) findViewById(R.id.login_popup));
        final android.app.AlertDialog.Builder aDialog = new android.app.AlertDialog.Builder(MainActivity.this);

        aDialog.setTitle("Join"); //타이틀바 제목
        aDialog.setView(layout); //inti.xml 파일을 뷰로 셋팅
        aDialog.setCancelable(true);
        join_id = (EditText) layout.findViewById(R.id.Join_id_edittext);
        join_pw = (EditText) layout.findViewById(R.id.Join_pw_edittext);
        //그냥 닫기버튼을 위한 부분
        aDialog.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        aDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dao.insert_join(join_id.getText().toString(), join_pw.getText().toString());

            }
        });
        //팝업창 생성
        android.app.AlertDialog ad = aDialog.create();
        ad.show();//보여줌!
    }


}
