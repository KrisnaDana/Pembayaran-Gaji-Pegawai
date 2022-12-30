package id.krisnadana.title.model;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class Tanggal {
    private String tanggal = null;
    private String bulan = null;

    public void setTanggal(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        this.tanggal = dateFormat.format(date);
    }

    public void setBulan(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM", Locale.getDefault());
        Date date = new Date();
        this.bulan = dateFormat.format(date);
    }

    public String getTanggal(){
        return this.tanggal;
    }

    public String getBulan(){
        return this.bulan;
    }
}
