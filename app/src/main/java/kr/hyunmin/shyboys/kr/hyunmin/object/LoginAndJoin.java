package kr.hyunmin.shyboys.kr.hyunmin.object;

/**
 * Created by Administrator on 2015-11-24.
 */
public class LoginAndJoin {
    private String _ID;
    private String _PW;
    private DAO _dao;

    public boolean confirm_PW(){
        return true;
    }

    public boolean join(){
        return false;
    }

    public boolean exist_ID(){
        return true;
    }
}
