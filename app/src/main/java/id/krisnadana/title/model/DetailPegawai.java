package id.krisnadana.title.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;

public class DetailPegawai extends SQLiteOpenHelper{
    private static int id_pegawai;
    private static String nama_pegawai;
    private static String alamat;
    private static String foto;
    private static int gaji_pegawai;
    private static String tanggal_gaji;
    private static int status;

    private static String DB_PATH= "data/data/id.krisnadana.title/databases/";
    private static String DB_NAME = "gaji_pegawai.db";
    private static int DB_VERSION = 3;
    private final Context context;

    private final LinkedList<Integer> idPegawaiList = new LinkedList<>();
    private final LinkedList<String> namaPegawaiList = new LinkedList<>();
    private final LinkedList<String> alamatPegawaiList = new LinkedList<>();
    private final LinkedList<String> fotoPegawaiList = new LinkedList<>();
    private final LinkedList<Integer> gajiPegawaiList = new LinkedList<>(); //tidak jadi diterapkan
    private final LinkedList<String> tanggalGajiList = new LinkedList<>(); //tidak jadi diterapkan

    public DetailPegawai(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public void dataPegawai(int id){
        SQLiteDatabase pegawai = null;
        String path = DB_PATH + DB_NAME;

        try{
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pegawai != null){
                String sql = "SELECT * FROM pegawai where id_pegawai="+id+" LIMIT 1";
                Cursor cursor = pegawai.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    setIdPegawai(cursor.getInt(0));
                    setNamaPegawai(cursor.getString(1));
                    setAlamat(cursor.getString(2));
                    setFoto(cursor.getString(3));
                }

                pegawai.close();
            }
        }catch(SQLiteException e){
            if(pegawai != null){
                pegawai.close();
            }
        }
    }


    public void setGajiPegawai(int id){
        SQLiteDatabase pembayaran = null;
        String path = DB_PATH + DB_NAME;
        String bulan = null;
        try{
            pembayaran = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pembayaran != null){
                PilihBulan pilihBulan = new PilihBulan();
                bulan = pilihBulan.getStrBulan();
                String sql = "SELECT nilai_gaji, tanggal_bayar FROM pembayaran_gaji where tanggal_bayar >= '2022-"+bulan+"-01' AND tanggal_bayar <= '2022-"+bulan+"-31' AND id_pegawai="+id+" LIMIT 1";
                Cursor cursor = pembayaran.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    gaji_pegawai = cursor.getInt(0);
                    tanggal_gaji = cursor.getString(1);
                }
                pembayaran.close();
            }
        }catch(SQLiteException e){
            if(pembayaran != null){
                pembayaran.close();
            }
        }
        Log.i("gaji", String.valueOf(getGajiPegawai()));
    }

    public int getGajiPegawai(){
        return gaji_pegawai;
    }

    public String getTanggalGaji(){
        return tanggal_gaji;
    }

    public void bayarGaji(int gaji){
        SQLiteDatabase pegawai = null;
        String path = DB_PATH + DB_NAME;

        try{
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            if(pegawai != null){
                Tanggal tanggal = new Tanggal();
                tanggal.setTanggal();
                String sql = "INSERT INTO pembayaran_gaji(id_pegawai, nilai_gaji, tanggal_bayar) VALUES("+this.id_pegawai+", "+gaji+", '"+tanggal.getTanggal()+"')";
                pegawai.execSQL(sql);
                pegawai.close();
            }
        }catch(SQLiteException e){

        }
    }

    private void setIdPegawai(int id_pegawai){
        this.id_pegawai = id_pegawai;
    }

    private void setNamaPegawai(String nama_pegawai){
        this.nama_pegawai = nama_pegawai;
    }

    private void setAlamat(String alamat){
        this.alamat = alamat;
    }

    private void setFoto(String foto){
        this.foto = foto;
    }

    public int getIdPegawai(){
        return this.id_pegawai;
    }

    public String getNamaPegawai(){
        return this.nama_pegawai;
    }

    public String getAlamat(){
        return this.alamat;
    }

    public String getFoto(){
        return this.foto;
    }

    public void setPegawaiBelumDigaji(){
        setStatusBelumDigaji();
        SQLiteDatabase pegawai = null;
        String path = DB_PATH + DB_NAME;
        LinkedList<Integer> idPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> namaPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> alamatPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> fotoPegawaiDataPegawai = new LinkedList<>();
        LinkedList<Integer> idPegawaiDataPembayaran = new LinkedList<>();
        String bulan = null;
        try{
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pegawai != null){
                String sql = "SELECT * FROM pegawai";
                Cursor cursor = pegawai.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    do{
                        idPegawaiDataPegawai.addLast(cursor.getInt(0));
                        namaPegawaiDataPegawai.addLast(cursor.getString(1));
                        alamatPegawaiDataPegawai.addLast(cursor.getString(2));
                        fotoPegawaiDataPegawai.addLast(cursor.getString(3));
                    }while(cursor.moveToNext());
                }

                pegawai.close();
                pegawai = null;
            }
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pegawai != null){
                PilihBulan pilihBulan = new PilihBulan();
                bulan = pilihBulan.getStrBulan();
                String sql = "SELECT id_pegawai FROM pembayaran_gaji where tanggal_bayar >= '2022-"+bulan+"-01' AND tanggal_bayar <= '2022-"+bulan+"-31'";
                Cursor cursor = pegawai.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    do{
                        idPegawaiDataPembayaran.addLast(cursor.getInt(0));
                    }while(cursor.moveToNext());
                }
                pegawai.close();
            }

            if(idPegawaiDataPegawai.size() > 0){
                Iterator iteratorIdPegawaiDataPegawai = idPegawaiDataPegawai.iterator();
                Iterator iteratorNamaPegawaiDataPegawai = namaPegawaiDataPegawai.iterator();
                Iterator iteratorAlamatPegawaiDataPegawai = alamatPegawaiDataPegawai.iterator();
                Iterator iteratorFotoPegawaiDataPegawai = fotoPegawaiDataPegawai.iterator();

                int i_idPegawaiDataPegawai;
                String i_namaPegawaiDataPegawai;
                String i_alamatPegawaiDataPegawai;
                String i_fotoPegawaiDataPegawai;
                int cek = 0;

                for(int i = 0; i < idPegawaiDataPegawai.size(); i++){
                    i_idPegawaiDataPegawai = Integer.parseInt(iteratorIdPegawaiDataPegawai.next().toString());
                    i_namaPegawaiDataPegawai = iteratorNamaPegawaiDataPegawai.next().toString();
                    i_alamatPegawaiDataPegawai = iteratorAlamatPegawaiDataPegawai.next().toString();
                    i_fotoPegawaiDataPegawai = iteratorFotoPegawaiDataPegawai.next().toString();
                    //Log.i("i", String.valueOf(x));
                    //Log.i("size data pegawai", String.valueOf(idPegawaiDataPegawai.size()));
                    int i_idPegawaiDataPembayaran;
                    Iterator iteratorIdPegawaiDataPembayaran = idPegawaiDataPembayaran.iterator();
                    for(int j = 0; j < idPegawaiDataPembayaran.size(); j++){
                        i_idPegawaiDataPembayaran = Integer.parseInt(iteratorIdPegawaiDataPembayaran.next().toString());
                        //Log.i("j", String.valueOf(y));
                        if(i_idPegawaiDataPegawai == i_idPegawaiDataPembayaran){
                            cek = 1;

                        }
                    }
                    if(cek == 0){
                        this.idPegawaiList.addLast(i_idPegawaiDataPegawai);
                        this.namaPegawaiList.addLast(i_namaPegawaiDataPegawai);
                        this.alamatPegawaiList.addLast(i_alamatPegawaiDataPegawai);
                        this.fotoPegawaiList.addLast(i_fotoPegawaiDataPegawai);
                        Log.i("j", String.valueOf(i_fotoPegawaiDataPegawai));
                    }else{
                        cek = 0;
                    }

                }
            }

        }catch(SQLiteException e){
            if(pegawai != null){
                pegawai.close();
            }
        }
    }

    public void setPegawaiSudahDigaji(){
        setStatusSudahDigaji();
        SQLiteDatabase pegawai = null;
        String path = DB_PATH + DB_NAME;
        LinkedList<Integer> idPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> namaPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> alamatPegawaiDataPegawai = new LinkedList<>();
        LinkedList<String> fotoPegawaiDataPegawai = new LinkedList<>();
        LinkedList<Integer> idPegawaiDataPembayaran = new LinkedList<>();

        String bulan = null;
        try{
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pegawai != null){
                String sql = "SELECT * FROM pegawai";
                Cursor cursor = pegawai.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    do{
                        idPegawaiDataPegawai.addLast(cursor.getInt(0));
                        namaPegawaiDataPegawai.addLast(cursor.getString(1));
                        alamatPegawaiDataPegawai.addLast(cursor.getString(2));
                        fotoPegawaiDataPegawai.addLast(cursor.getString(3));
                    }while(cursor.moveToNext());
                }

                pegawai.close();
                pegawai = null;
            }
            pegawai = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(pegawai != null){
                PilihBulan pilihBulan = new PilihBulan();
                bulan = pilihBulan.getStrBulan();
                String sql = "SELECT * FROM pembayaran_gaji where tanggal_bayar >= '2022-"+bulan+"-01' AND tanggal_bayar <= '2022-"+bulan+"-31'";
                Cursor cursor = pegawai.rawQuery(sql, null);

                if(cursor.getCount() != 0){
                    cursor.moveToFirst();
                    do{
                        idPegawaiDataPembayaran.addLast(cursor.getInt(1));
                        gajiPegawaiList.addLast(cursor.getInt(2));
                        tanggalGajiList.addLast(cursor.getString(3));
                        Log.i("gaji", cursor.getString(3));
                    }while(cursor.moveToNext());
                }
                pegawai.close();
            }

            if(idPegawaiDataPegawai.size() > 0 && idPegawaiDataPembayaran.size() > 0){
                Iterator iteratorIdPegawaiDataPembayaran = idPegawaiDataPembayaran.iterator();
                int i_idPegawaiDataPembayaran;

                for(int i = 0; i < idPegawaiDataPembayaran.size(); i++){
                    i_idPegawaiDataPembayaran = Integer.parseInt(iteratorIdPegawaiDataPembayaran.next().toString());
                    //Log.i("i", String.valueOf(x));
                    //Log.i("size data pegawai", String.valueOf(idPegawaiDataPegawai.size()));
                    int i_idPegawaiDataPegawai;
                    String i_namaPegawaiDataPegawai;
                    String i_alamatPegawaiDataPegawai;
                    String i_fotoPegawaiDataPegawai;
                    Iterator iteratorIdPegawaiDataPegawai = idPegawaiDataPegawai.iterator();
                    Iterator iteratorNamaPegawaiDataPegawai = namaPegawaiDataPegawai.iterator();
                    Iterator iteratorAlamatPegawaiDataPegawai = alamatPegawaiDataPegawai.iterator();
                    Iterator iteratorFotoPegawaiDataPegawai = fotoPegawaiDataPegawai.iterator();
                    for(int j = 0; j < idPegawaiDataPegawai.size(); j++){
                        i_idPegawaiDataPegawai = Integer.parseInt(iteratorIdPegawaiDataPegawai.next().toString());
                        i_namaPegawaiDataPegawai = iteratorNamaPegawaiDataPegawai.next().toString();
                        i_alamatPegawaiDataPegawai = iteratorAlamatPegawaiDataPegawai.next().toString();
                        i_fotoPegawaiDataPegawai = iteratorFotoPegawaiDataPegawai.next().toString();
                        //Log.i("j", String.valueOf(y));
                        if(i_idPegawaiDataPembayaran == i_idPegawaiDataPegawai){
                            //Log.i("masuk", i_fotoPegawaiDataPegawai);
                            this.idPegawaiList.addLast(i_idPegawaiDataPegawai);
                            this.namaPegawaiList.addLast(i_namaPegawaiDataPegawai);
                            this.alamatPegawaiList.addLast(i_alamatPegawaiDataPegawai);
                            this.fotoPegawaiList.addLast(i_fotoPegawaiDataPegawai);
                        }
                    }

                }
            }

        }catch(SQLiteException e){
            if(pegawai != null){
                pegawai.close();
            }
        }
    }

    public LinkedList<Integer> getIdPegawaiList(){
        return this.idPegawaiList;
    }

    public LinkedList<String> getNamaPegawaiList(){
        return this.namaPegawaiList;
    }

    public LinkedList<String> getAlamatPegawaiList(){
        return this.alamatPegawaiList;
    }

    public LinkedList<String> getFotoPegawaiList(){
        return this.fotoPegawaiList;
    }

    public LinkedList<Integer> getGajiPegawaiList(){
        return this.gajiPegawaiList;
    }

    public LinkedList<String> getTanggalGajiList(){
        return this.tanggalGajiList;
    }

    private void setStatusBelumDigaji(){
        this.status = 0;
    }

    private void setStatusSudahDigaji(){
        this.status = 1;
    }

    public int getStatus(){
        return this.status;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
