package POJO;

public class Book{
    public String _id;
    public String book_flag;
    public String bel;
    public String bel_lat;
    public String eng;

   public Book(String _id,String bel,String bel_lat, String eng,String book_flag){
        this._id = _id;
        this.bel = bel;
        this.bel_lat = bel_lat;
        this.eng = eng;
        this.book_flag = book_flag;

        
    }
}
