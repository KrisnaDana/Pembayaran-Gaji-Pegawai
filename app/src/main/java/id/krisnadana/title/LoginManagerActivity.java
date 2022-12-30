package id.krisnadana.title;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import id.krisnadana.title.model.LoginManager;
import id.krisnadana.title.model.Tanggal;

public class LoginManagerActivity extends AppCompatActivity {
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manager);
        Intent intentPilihBulan = new Intent(this, PilihBulanActivity.class);

        final EditText EditTextUsername = (EditText) findViewById(R.id.EditTextUsernameManager);
        final EditText EditTextPassword = (EditText) findViewById(R.id.EditTextPasswordManager);
        final Button ButtonLogin = (Button) findViewById(R.id.ButtonLoginManager);
        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = EditTextUsername.getText().toString();
                String passwordInput = EditTextPassword.getText().toString();
                LoginManager loginManager = new LoginManager(getApplicationContext());
                boolean cek = loginManager.loginAuth(usernameInput, passwordInput);
                if(cek == true){
                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(intentPilihBulan);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        PACKAGE_NAME = getApplicationContext().getPackageName();
    }
}