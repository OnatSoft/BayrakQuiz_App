package per.example.bayrakquiz_appexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnBasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasla = findViewById(R.id.btnBasla);


        DatabaseCopy();

        btnBasla.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, QuizActivity.class));
        });
    }


    public void DatabaseCopy() {
        //* Veritabanı kopyalama işlemi yapılıyor.
        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);

        try {
            helper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        helper.openDataBase();
    }
}