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
import android.widget.Toast;

import id.krisnadana.title.model.DetailPegawai;
import id.krisnadana.title.model.PilihBulan;

public class DetailPegawaiBayarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pegawai_bayar);

        Intent intentBelumDigaji = new Intent(this, BelumDigajiActivity.class);
        final ImageView imageViewFoto = (ImageView) findViewById(R.id.fotoPegawaiBayar);
        final TextView textViewNama = (TextView) findViewById(R.id.TextViewNamaPegawaiBayar);
        final TextView textViewAlamat = (TextView) findViewById(R.id.TextViewAlamatPegawaiBayar);
        final EditText editTextGaji = (EditText) findViewById(R.id.EditTextNilaiGaji);
        TextView textViewBulan = (TextView) findViewById(R.id.TextViewDetailPegawaiBayarBulan);
        final Button buttonKembali = (Button) findViewById(R.id.ButtonPegawaiBayarKembali);
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentBelumDigaji);
                finish();
            }
        });
        final Button buttonBayar = (Button) findViewById(R.id.ButtonPegawaiBayar);
        buttonBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nilaiGaji = Integer.parseInt(editTextGaji.getText().toString());
                DetailPegawai detailPegawai = new DetailPegawai(getApplicationContext());
                detailPegawai.bayarGaji(nilaiGaji);
                Toast.makeText(getApplicationContext(), "Data Pembayaran Gaji Berhasil Ditambah", Toast.LENGTH_SHORT).show();
                startActivity(intentBelumDigaji);
                finish();
            }
        });

        PilihBulan pilihBulan = new PilihBulan();
        textViewBulan.setText(pilihBulan.getKataBulan());
        DetailPegawai detailPegawai = new DetailPegawai(getApplicationContext());
        textViewNama.setText(detailPegawai.getNamaPegawai());
        textViewAlamat.setText(detailPegawai.getAlamat());
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