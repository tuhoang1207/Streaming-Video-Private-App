package com.example.streamingvideoprivateapp4;

public class DataSeries {
    private String Scast;
    private String Scover;
    private String Sdesc;
    private String Slink;
    private String Sthumb;
    private String Stitle;
    private String Tlink;

    //Constructor mặc định
    public DataSeries() {
    }

    //Hàm tạo biến


    public DataSeries(String scast, String scover, String sdesc, String slink, String sthumb, String stitle, String tlink) {
        Scast = scast;
        Scover = scover;
        Sdesc = sdesc;
        Slink = slink;
        Sthumb = sthumb;
        Stitle = stitle;
        Tlink = tlink;
    }

    //Get và Set giá trị cho biến
    public String getScast() {
        return Scast;
    }

    public void setScast(String scast) {
        Scast = scast;
    }

    public String getScover() {
        return Scover;
    }

    public void setScover(String scover) {
        Scover = scover;
    }

    public String getSdesc() {
        return Sdesc;
    }

    public void setSdesc(String sdesc) {
        Sdesc = sdesc;
    }

    public String getSlink() {
        return Slink;
    }

    public void setSlink(String slink) {
        Slink = slink;
    }

    public String getSthumb() {
        return Sthumb;
    }

    public void setSthumb(String sthumb) {
        Sthumb = sthumb;
    }

    public String getStitle() {
        return Stitle;
    }

    public void setStitle(String stitle) {
        Stitle = stitle;
    }

    public String getTlink() {
        return Tlink;
    }

    public void setTlink(String tlink) {
        Tlink = tlink;
    }
}
