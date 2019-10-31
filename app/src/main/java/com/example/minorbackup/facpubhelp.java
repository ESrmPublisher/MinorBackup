package com.example.minorbackup;

public class facpubhelp {
    String scopus,journ,scoppub,pappub,total;

    public facpubhelp() {
    }

    public facpubhelp(String scopus, String journ, String scoppub, String pappub, String total) {
        this.scopus = scopus;
        this.journ = journ;
        this.scoppub = scoppub;
        this.pappub = pappub;
        this.total = total;
    }

    public String getScopus() {
        return scopus;
    }

    public void setScopus(String scopus) {
        this.scopus = scopus;
    }

    public String getJourn() {
        return journ;
    }

    public void setJourn(String journ) {
        this.journ = journ;
    }

    public String getScoppub() {
        return scoppub;
    }

    public void setScoppub(String scoppub) {
        this.scoppub = scoppub;
    }

    public String getPappub() {
        return pappub;
    }

    public void setPappub(String pappub) {
        this.pappub = pappub;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
