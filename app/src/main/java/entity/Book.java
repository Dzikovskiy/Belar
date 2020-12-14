package entity;

public class Book {
    private String _id;
    private String book_flag;
    private String bel;
    private String bel_lat;
    private String eng;

    public Book(String _id, String bel, String bel_lat, String eng, String book_flag) {
        this.set_id(_id);
        this.setBel(bel);
        this.setBel_lat(bel_lat);
        this.setEng(eng);
        this.setBook_flag(book_flag);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBook_flag() {
        return book_flag;
    }

    public void setBook_flag(String book_flag) {
        this.book_flag = book_flag;
    }

    public String getBel() {
        return bel;
    }

    public void setBel(String bel) {
        this.bel = bel;
    }

    public String getBel_lat() {
        return bel_lat;
    }

    public void setBel_lat(String bel_lat) {
        this.bel_lat = bel_lat;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }
}
