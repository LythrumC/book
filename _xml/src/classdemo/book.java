package classdemo;

/**
 * @author 陈宽
 * @create 2021-02-02 13:59
 * @describe book类
 */
public class book {
    private String bookSno;
    private String bookName;
    private String bookAuthor;
    private double bookPrice;

    public book(String bookSno, String bookName, String bookAuthor, double bookPrice) {
        this.bookSno = bookSno;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }

    public String getBookSno() {
        return bookSno;
    }

    public void setBookSno(String bookSno) {
        this.bookSno = bookSno;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "book{" +
                "bookSno='" + bookSno + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
