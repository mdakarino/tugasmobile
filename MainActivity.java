package com.example.aplikasifixtugasandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int currentSaldo = 1000000;
    private boolean isSaldoVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvSaldo = findViewById(R.id.nominalsaldo);
        EditText inputNominal = findViewById(R.id.inputNominal);
        Button setorButton = findViewById(R.id.setorButton);

        TextView tvExplore = findViewById(R.id.tvExplore);
        TextView tvHome = findViewById(R.id.tvHome);
        TextView tvProfile = findViewById(R.id.tvProfile);

        tvSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSaldoVisible) {
                    tvSaldo.setText("Rp ********");
                    isSaldoVisible = false;
                } else {
                    updateSaldoDisplay(tvSaldo);
                    isSaldoVisible = true;
                }
            }
        });

        setorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNominal = inputNominal.getText().toString().trim();

                if (!strNominal.isEmpty()) {
                    try {
                        int jumlahSetor = Integer.parseInt(strNominal);
                        currentSaldo += jumlahSetor; // namba saldo dengan nominal dari jumlah setor

                        if (isSaldoVisible) {
                            updateSaldoDisplay(tvSaldo);
                        }

                        // Hpus isi dari sisa inputan di awal agar tidak terjadi multiple input
                        inputNominal.setText("");

                        Toast.makeText(MainActivity.this, "Setor berhasil Rp " + jumlahSetor, Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Nominal tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvHome.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Menu Home Diklik", Toast.LENGTH_SHORT).show());
        tvExplore.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Menu Explore Diklik", Toast.LENGTH_SHORT).show());
        tvProfile.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Menu Profile Diklik", Toast.LENGTH_SHORT).show());
    }

    // function buat ubah nominal saldo ke dalam format ruiah
    private void updateSaldoDisplay(TextView tvSaldo) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("in", "ID"));
        String formattedSaldo = formatter.format(currentSaldo);
        tvSaldo.setText("Rp " + formattedSaldo);
    }
}