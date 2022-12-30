package id.krisnadana.title;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import id.krisnadana.title.model.DetailPegawai;
import id.krisnadana.title.model.PilihBulan;

public class DetailPegawaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pegawai);

        Intent intentSudahDigaji = new Intent(this, SudahDigajiActivity.class);
        final ImageView imageViewFoto = (ImageView) findViewById(R.id.fotoPegawai);
        final TextView textViewNama = (TextView) findViewById(R.id.TextViewNamaPegawai);
        final TextView textViewAlamat = (TextView) findViewById(R.id.TextViewAlamatPegawai);
        final TextView textViewGaji = (TextView) findViewById(R.id.TextViewGajiPegawai);
        final TextView textViewTanggalGaji = (TextView) findViewById(R.id.TextViewTanggalGaji);
        TextView textViewBulan = (TextView) findViewById(R.id.TextViewDetailPegawaiBulan);
        final Button buttonKembali = (Button) findViewById(R.id.ButtonPegawaiKembali);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSudahDigaji);
                finish();
            }
        });

        PilihBulan pilihBulan = new PilihBulan();
        textViewBulan.setText(pilihBulan.getKataBulan());
        DetailPegawai detailPegawai = new DetailPegawai(getApplicationContext());
        textViewNama.setText(detailPegawai.getNamaPegawai());
        textViewAlamat.setText(detailPegawai.getAlamat());
        textViewGaji.setText("Rp."+Integer.toString(detailPegawai.getGajiPegawai()));
        textViewTanggalGaji.setText(detailPegawai.getTanggalGaji());
        int setDrawable = getResources().getIdentifier(""+detailPegawai.getFoto(), "drawable", getPackageName());
        imageViewFoto.setImageResource(setDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gaji_pegawai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_pilih_bulan:
                optionPilihBulan();
                return true;
            case R.id.option_logout:
                optionLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void optionPilihBulan(){
        Intent intentPilihBulan = new Intent(this, PilihBulanActivity.class);
        startActivity(intentPilihBulan);
        finish();
    }

    private void optionLogout(){
        Intent intentLogout = new Intent(this, LoginManagerActivity.class);
        startActivity(intentLogout);
        finish();
    }
}