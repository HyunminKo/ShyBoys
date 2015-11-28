package kr.hyunmin.shyboys.kr.hyunmin.object;

import java.sql.*;

/**
 * Created by Ko on 2015-11-24.
 */
public class DAO {
    Connection _conn=null;
    Statement _stmt=null;
    ResultSet _rs=null;
    DTO dto = null;

    String _URL= "jdbc:mysql://localhost:3306/shyboys";
    String _USER = "root"; // 사용자 계정
    String _PASS = "root";

    public boolean addDB(DTO dto){
        try {
            // 사용자 계정의 패스워드

            Class.forName("com.mysql.jdbc.Driver"); // 데이터베이스와 연동하기 위해
            // DriverManager에 등록한다.
            this.dto = dto;
            _conn = DriverManager.getConnection(_URL, _USER, _PASS); // DriverManager
            // 객체로부터
            // Connection
            // 객체를 얻어온다.
            // out.println("제대로 연결되었습니다."); // 커넥션이 제대로 연결되면 수행된다.

            _stmt = _conn.createStatement();
            // rs = stmt.executeQuery("select * from inform_user where
            // u_id='"+dto.getJoin_id()+"'");

            if (_rs.next() == true) {
                // boardDTO.setJoin_id("exist!");
            } else {
                _stmt.executeUpdate("INSERT INTO main_information (_room_code, _QorA, _content, _date) VALUES ('" + dto.get_room_code() + "','" + dto.get_QorA() + "','" + dto.get_content() + "','" + dto.get_date() + "');");
            }
            _conn.close();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
    public boolean loadDB(){
        return false;
    }
    public boolean deleteDB(){
        return false;
    }
}
