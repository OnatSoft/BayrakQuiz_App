package per.example.bayrakquiz_appexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {
    private TextView txtDogruCvp, txtYanlisCvp, txtSoru;
    private ImageView imgSoruResim;
    private Button btnSecenek1, btnSecenek2, btnSecenek3, btnSecenek4;

    private ArrayList<Bayraklar> tumSorular;
    private ArrayList<Bayraklar> yanlisSeceneklerListe;
    private Bayraklar dogruSoru;
    private DBConnection sqdb;
    //Sayaçlar ---------------------------------------
    private int soruSayac = 0;
    private int yanlisSayac = 0;
    private int dogruSayac = 0;
    //Seçenekler -----------------------------------------
    private HashSet<Bayraklar> seceneklerKaristirmaListe = new HashSet<>();
    private ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imgSoruResim = findViewById(R.id.imgSoruResim);
        txtSoru = findViewById(R.id.txtSoru);
        txtDogruCvp = findViewById(R.id.txtDogruCvp);
        txtYanlisCvp = findViewById(R.id.txtYanlisCvp);
        btnSecenek1 = findViewById(R.id.btnSecenek1);
        btnSecenek2 = findViewById(R.id.btnSecenek2);
        btnSecenek3 = findViewById(R.id.btnSecenek3);
        btnSecenek4 = findViewById(R.id.btnSecenek4);


        sqdb = new DBConnection(this);
        tumSorular = new BayraklarDao().rastgeleGetir(sqdb); //* Quiz sayfası açıldığında BayraklarDao sınıfından metodu çağırarak tüm sorular karışık şekilde listeleniyor.
        soruYukle();

        btnSecenek1.setOnClickListener(view -> {

            dogruKontrol(btnSecenek1);
            sayacKontrol();
        });

        btnSecenek2.setOnClickListener(view -> {

            dogruKontrol(btnSecenek2);
            sayacKontrol();
        });

        btnSecenek3.setOnClickListener(view -> {

            dogruKontrol(btnSecenek3);
            sayacKontrol();
        });

        btnSecenek4.setOnClickListener(view -> {

            dogruKontrol(btnSecenek4);
            sayacKontrol();
        });
    }

    public void soruYukle() {
        //* Burası her soru değiştiğinde çalışacak olan metod.
        txtSoru.setText((soruSayac + 1) + ". Soru");
        txtYanlisCvp.setText("Yanlış: " + yanlisSayac);
        txtDogruCvp.setText("Doğru: " + dogruSayac);

        dogruSoru = tumSorular.get(soruSayac);

        yanlisSeceneklerListe = new BayraklarDao().rastgele3YanlisGetir(sqdb, dogruSoru.getBayrak_Id());

        imgSoruResim.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_Resim(), "drawable", getPackageName()));

        seceneklerKaristirmaListe.clear();
        seceneklerKaristirmaListe.add(dogruSoru);
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        seceneklerKaristirmaListe.add(yanlisSeceneklerListe.get(2));

        seceneklerListe.clear();
        for (Bayraklar b : seceneklerKaristirmaListe) {
            seceneklerListe.add(b);
        }

        btnSecenek1.setText(seceneklerListe.get(0).getBayrak_Ad());
        btnSecenek2.setText(seceneklerListe.get(1).getBayrak_Ad());
        btnSecenek3.setText(seceneklerListe.get(2).getBayrak_Ad());
        btnSecenek4.setText(seceneklerListe.get(3).getBayrak_Ad());
    }

    public void dogruKontrol(Button button) {
        //* Soruların cevaplarının doğruluğunu kontrol eden metod. Seçilen buttonun text ile doğru seçeneği karşılaştırarak kontrol ediliyor.
        String buttonText = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_Ad();

        if (buttonText.equals(dogruCevap)) {
            dogruSayac++;
        } else {
            yanlisSayac++;
        }

    }

    public void sayacKontrol() {
        //* Sorular bitene kadar bir sonraki soruya geçen ve sorular bittiğinde doğru sayısını Sonuç sayfasına göndererek sayfa geçişi sağlanıyor.
        soruSayac++;
        if (soruSayac != 10) {
            soruYukle();
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("dogruSayac", dogruSayac);
            startActivity(intent);
            finish();
        }
    }
}