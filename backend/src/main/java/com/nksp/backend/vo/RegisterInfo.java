package com.nksp.backend.vo;
import lombok.Data;

import java.time.Year;
import java.util.Date;

@Data
public class RegisterInfo {
    String uname;
    String password;
    int usex;
    Date ubirth;
    String utel;
    String umail;
    int udoctype;
    String udocno;
    String suniversity;
    String scollege;
    String smajority;
    int sno;
    String sclass;
    Year sgrade;

    public String getUname() {
        return uname;
    }

    public String getSuniversity() {
        return suniversity;
    }

    public String getScollege() {
        return scollege;
    }

    public String getSmajority() {
        return smajority;
    }

    public int getSno() {
        return sno;
    }

    public String getSclass() {
        return sclass;
    }

    public Year getSgrade() {
        return sgrade;
    }
}
