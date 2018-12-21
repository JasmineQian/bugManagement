package com.jasmine.demo.bean;

public class Empoly {

    private long eid;
    private String ename;

    public Empoly(long eid, String ename) {
        this.eid = eid;
        this.ename = ename;
    }

    public Empoly()  {
        super();
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    @Override
    public String toString() {
        return "Empoly{" +
                "eid=" + eid +
                ", ename='" + ename + '\'' +
                '}';
    }


}
