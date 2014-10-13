package com.sitech.basd.dbmanager.domain.dbexampleuser;

import java.util.List;

public class DBPowerObj {

    private String ID;
    private String POWER_NAME;
    private List<String> pidlist;
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getPOWER_NAME() {
        return POWER_NAME;
    }
    public void setPOWER_NAME(String pOWER_NAME) {
        POWER_NAME = pOWER_NAME;
    }
    public List<String> getPidlist() {
        return pidlist;
    }
    public void setPidlist(List<String> pidlist) {
        this.pidlist = pidlist;
    }
}
