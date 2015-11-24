package kr.hyunmin.shyboys.kr.hyunmin.object;

/**
 * Created by Administrator on 2015-11-24.
 */
public class DTO {
    private String _ID;
    private String _PW;
    private String _room_code;
    private int _QorA;
    private int _QnA;
    private String _content;
    private String _date;

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_PW() {
        return _PW;
    }

    public void set_PW(String _PW) {
        this._PW = _PW;
    }

    public String get_room_code() {
        return _room_code;
    }

    public void set_room_code(String _room_code) {
        this._room_code = _room_code;
    }

    public int get_QorA() {
        return _QorA;
    }

    public void set_QorA(int _QorA) {
        this._QorA = _QorA;
    }

    public int get_QnA() {
        return _QnA;
    }

    public void set_QnA(int _QnA) {
        this._QnA = _QnA;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}
