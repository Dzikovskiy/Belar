package entity;

public class  ButtonNumber {
    private String engText;
    private String belText;
    private int engButtonNumber;
    private int belButtonNumber;

    public ButtonNumber(String engText, String belText, int engButtonNumber, int belButtonNumber) {
        this.engText = engText;
        this.belText = belText;
        this.engButtonNumber = engButtonNumber;
        this.belButtonNumber = belButtonNumber;
    }

    public String getEngText() {
        return engText;
    }

    public String getBelText() {
        return belText;
    }

    public int getEngButtonNumber() {
        return engButtonNumber;
    }

    public int getBelButtonNumber() {
        return belButtonNumber;
    }

    public void setEngText(String engText) {
        this.engText = engText;
    }

    public void setBelText(String belText) {
        this.belText = belText;
    }

    public void setEngButtonNumber(int engButtonNumber) {
        this.engButtonNumber = engButtonNumber;
    }

    public void setBelButtonNumber(int belButtonNumber) {
        this.belButtonNumber = belButtonNumber;
    }
}
