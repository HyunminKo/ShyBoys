package kr.hyunmin.shyboys.kr.hyunmin.object;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import kr.hyunmin.shyboys.RoomListActivity;

/**
 * Created by Ko on 2015-11-24.
 */
public class DAO {
    Context context;
    public static int isCorrect = 0; // 로그인 관련 / 에러 : 0 성공 : 1 , 실패 : 아이디 없음(2), 비밀번호 틀림(3)
    public DAO(Context context) {
        this.context = context;
    }
    /**로그인 부분**/
    public void login(String id , String pw){
        importToDatabase(id, pw);

    }
    private void importToDatabase(final String id, final String pw){

        class ImportData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                String data_id;
                String data_pw;

                try{
                    // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                    JSONObject jObject = new JSONObject(s);
                    // results라는 key는 JSON배열로 되어있다.
                    JSONArray results = jObject.getJSONArray("results");
                    String zz = "";
                    zz += "Status : " + jObject.get("status");
                    zz += "\n";
                    zz += "Number of results : " + jObject.get("num_result");
                    zz += "\n";
                    zz += "Results : \n";
                    Log.d("login",zz);

                    for ( int i = 0; i < results.length(); ++i ) {
                        JSONObject temp = results.getJSONObject(i);
                        data_id = String.valueOf(temp.get("ID"));
                        data_pw = String.valueOf(temp.get("PW"));
                        if(id.equals(data_id)){
                            if(pw.equals(data_pw)){
                                isCorrect = 1; //로그인 성공
                                Log.d("login","성공");
                                loading.dismiss();
                                Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(context,RoomListActivity.class);
                                context.startActivity(intent1);
                                return;
                            }else{
                                isCorrect = 3; //비밀번호 틀림
                                Log.d("login", "비밀번호틀림");
                                loading.dismiss();
                                Toast.makeText(context, "비밀번호가 틀렸습니다!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                            isCorrect = 2; //아이디 존재 x
                            loading.dismiss();
                            Log.d("login", "아이디 존재 x");

                    }
                    if(isCorrect==2){
                        Toast.makeText(context, "아이디가 없습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(String... params) {
                StringBuilder jsonHtml = new StringBuilder();
                try {
                    URL phpUrl = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();

                    if ( conn != null ) {
                        conn.setConnectTimeout(10000);
                        conn.setUseCaches(false);

                        if ( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) {
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                            while ( true ) {
                                String line = br.readLine();
                                if ( line == null )
                                    break;
                                jsonHtml.append(line + "\n");
                            }
                            br.close();
                        }
                        conn.disconnect();
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
                return jsonHtml.toString();
            }
        }

        ImportData task = new ImportData();
        task.execute("http://52.68.201.69/login.php");
    }
    public void insert_join(String id, String pw){
        insertToDatabase(id, pw);
    }
    private void insertToDatabase(String id, String pw){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    Toast.makeText(context.getApplicationContext(), "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String id = (String)params[0];
                    String pw = (String)params[1];

                    String link="http://52.68.201.69/join.php";
                    String data  = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                    data += "&" + URLEncoder.encode("PW", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8");
                    Log.d("test",data);

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(id,pw);
    }
    public void insert_Question(String roomcode, String content){
        insert_Question_ToDatabase(roomcode, content);
    }
    private void insert_Question_ToDatabase(String roomcode, String content){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
                Toast.makeText(context.getApplicationContext(), "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String id = (String)params[0];
                    String pw = (String)params[1];

                    String link="http://52.68.201.69/join.php";
                    String data  = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
                    data += "&" + URLEncoder.encode("PW", "UTF-8") + "=" + URLEncoder.encode(pw, "UTF-8");
                    Log.d("test",data);

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(roomcode,content);
    }
}
