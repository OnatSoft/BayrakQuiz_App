package per.example.bayrakquiz_appexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private Button btnTekrar;
    private TextView txtSonucYanlis, txtSonucDogru, txtBasariPuan;
    private int dogruSayac;
    private int basariPuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnTekrar = findViewById(R.id.btnTekrar);
        txtBasariPuan = findViewById(R.id.txtBasariPuan);
        txtSonucDogru = findViewById(R.id.txtSonucDogru);
        txtSonucYanlis = findViewById(R.id.txtSonucYanlis);


        //* Quiz sayfasından gönderilen doğru cevap sayısı burada karşılanarak textview'e yazdırılıyor. Ve başarı yüzdesi hesaplanıyor.
        dogruSayac = getIntent().getIntExtra("dogruSayac", 0);
        txtSonucDogru.setText("Doğru: " + dogruSayac);
        txtSonucYanlis.setText("Yanlış: " + (10 - dogruSayac));
        txtBasariPuan.setText("%" + (dogruSayac * 100) / 10 + " Başarı");

        btnTekrar.setOnClickListener(view -> {

            startActivity(new Intent(ResultActivity.this, QuizActivity.class));
            finish();
        });
    }
}