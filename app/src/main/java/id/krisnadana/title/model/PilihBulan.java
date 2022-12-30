package id.krisnadana.title.model;

public class PilihBulan {
    private static int bulan = 0;
    private static String strBulan = null;
    private static String kataBulan = null;

    public void setBulan(int bulan){
        this.bulan = bulan;
    }

    public int getBulan(){
        return this.bulan;
    }

    public void setStrBulan(){
        if(bulan >= 1 && bulan <=9){
            this.strBulan = "0"+Integer.toString(this.bulan);
        }else{
            this.strBulan = Integer.toString(this.bulan);
        }
    }

    public void setKataBulan(String kataBulan){
        this.kataBulan = kataBulan;
    }

    public String getKataBulan(){
        return this.kataBulan;
    }

    public String getStrBulan(){
        return this.strBulan;
    }
}
