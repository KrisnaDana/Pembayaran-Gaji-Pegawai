package id.krisnadana.title;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import id.krisnadana.title.model.PilihBulan;

public class PilihBulanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bulan);

        Intent intentBelumDigaji = new Intent(this, BelumDigajiActivity.class);
        final Spinner SpinnerPilihBulan = (Spinner) findViewById(R.id.SpinnerPilihBulan);
        final Button ButtonSelanjutnya = (Button) findViewById(R.id.ButtonSelanjutnya);
        ButtonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bulan = SpinnerPilihBulan.getSelectedItemPosition();
                bulan++;

                PilihBulan pilihBulan = new PilihBulan();
                pilihBulan.setBulan(bulan);
                pilihBulan.setStrBulan();
                pilihBulan.setKataBulan(SpinnerPilihBulan.getSelectedItem().toString());

                //Toast.makeText(getApplicationContext(), ""+pilihBulan.getStrBulan(), Toast.LENGTH_SHORT).show();
                startActivity(intentBelumDigaji);
                finish();
            }
        });
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