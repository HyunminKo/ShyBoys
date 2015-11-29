package kr.hyunmin.shyboys.kr.hyunmin.object;

/**
 * Created by Ko on 2015-11-27.
 */
public class InnerDTO {
    /****host 부분****/
    String ID;
    String PW;
    String h_Roomcode;
    String Subject;
    String host_name;

    /****user 부분****/
    String u_Roomcode;
    String u_name;
    String QorA;
    String in_content;
    String in_date;
    String a;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getH_Roomcode() {
        return h_Roomcode;
    }

    public void setH_Roomcode(String h_Roomcode) {
        this.h_Roomcode = h_Roomcode;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getU_Roomcode() {
        return u_Roomcode;
    }

    public void setU_Roomcode(String u_Roomcode) {
        this.u_Roomcode = u_Roomcode;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getQorA() {
        return QorA;
    }

    public void setQorA(String qorA) {
        QorA = qorA;
    }

    public String getIn_content() {
        return in_content;
    }

    public void setIn_content(String in_content) {
        this.in_content = in_content;
    }

    public String getIn_date() {
        return in_date;
    }

    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }

    public InnerDTO(String ID, String PW, String h_Roomcode, String subject, String host_name) {
        this.ID = ID;
        this.PW = PW;
        this.h_Roomcode = h_Roomcode;
        Subject = subject;
        this.host_name = host_name;
    }
    public InnerDTO(String u_Roomcode, String u_name, String QorA, String in_content, String in_date,String a) {
        this.u_Roomcode = u_Roomcode;
        this.u_name=u_name;
        this.QorA=QorA;
        this.in_content=in_content;
        this.in_date=in_date;
    }

}
