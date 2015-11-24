package kr.hyunmin.shyboys.kr.hyunmin.object;

import java.sql.*;

/**
 * Created by Administrator on 2015-11-24.
 */
public class DAO {
    Connection _conn=null;
    Statement stmt=null;
    ResultSet _rs=null;
    DTO DTO = null;

    String _URL= "jdbc:mysql://localhost:3306/project"; // 사용하려는 데이터베이스명을 포함한
    String _USER = "root"; // 사용자 계정
    String _PASS = "root";

    public boolean addDB(){
        return false;
    }
    public boolean loadDB(){
        return false;
    }
    public boolean deleteDB(){
        return false;
    }
}
