package id.krisnadana.title;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.krisnadana.title.model.DetailPegawai;
import id.krisnadana.title.model.PilihBulan;

public class SudahDigajiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PegawaiListAdapter pegawaiListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudah_digaji);

        Intent intentBelumDigaji = new Intent(this, BelumDigajiActivity.class);
        Intent intentSudahDigaji = new Intent(this, SudahDigajiActivity.class);

        PilihBulan pilihBulan = new PilihBulan();
        TextView textViewBulan = (TextView) findViewById(R.id.TextViewSudahDigajiBulan);
        textViewBulan.setText(pilihBulan.getKataBulan());

        final Button ButtonBelumDigaji = (Button) findViewById(R.id.ButtonBelumDigajiSudah);
        ButtonBelumDigaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentBelumDigaji);
                finish();
            }
        });

        final Button ButtonSudahDigaji = (Button) findViewById(R.id.ButtonSudahDigajiSudah);
        ButtonSudahDigaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSudahDigaji);
                finish();
            }
        });

        DetailPegawai detailPegawai = new DetailPegawai(getApplicationContext());
        detailPegawai.setPegawaiSudahDigaji();
        //Toast.makeText(getApplicationContext(), ""+detailPegawai.getIdPegawai(), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.RecyclerViewSudahDigaji);
        pegawaiListAdapter = new PegawaiListAdapter(this, detailPegawai.getIdPegawaiList(), detailPegawai.getNamaPegawaiList(), detailPegawai.getAlamatPegawaiList(), detailPegawai.getFotoPegawaiList(), new ClickListener() {
            @Override public void onPositionClicked(int position) {
                // callback performed on click
                finish();
            }
        });
        recyclerView.setAdapter(pegawaiListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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