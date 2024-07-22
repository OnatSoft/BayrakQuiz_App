package per.example.bayrakquiz_appexp;

public class Bayraklar {
    private int bayrak_Id;
    private String bayrak_Ad;
    private String bayrak_Resim;

    public Bayraklar() {
    }

    public Bayraklar(int bayrak_Id, String bayrak_Ad, String bayrak_Resim) {
        this.bayrak_Id = bayrak_Id;
        this.bayrak_Ad = bayrak_Ad;
        this.bayrak_Resim = bayrak_Resim;
    }

    public int getBayrak_Id() {
        return bayrak_Id;
    }

    public void setBayrak_Id(int bayrak_Id) {
        this.bayrak_Id = bayrak_Id;
    }

    public String getBayrak_Ad() {
        return bayrak_Ad;
    }

    public void setBayrak_Ad(String bayrak_Ad) {
        this.bayrak_Ad = bayrak_Ad;
    }

    public String getBayrak_Resim() {
        return bayrak_Resim;
    }

    public void setBayrak_Resim(String bayrak_Resim) {
        this.bayrak_Resim = bayrak_Resim;
    }
}
