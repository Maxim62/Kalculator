package com.example.kalculatorbp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private int num = 0;
    String textPoluch;
    String textOstatok;
    String textObuchaysh;
    String textPatron_uprazh;
    private TextView textViewPervaya;
    private TextView textViewVtoraya;
    private TextView textViewVsego;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.id_vydano);
        editText2 = findViewById(R.id.id_ostatok);
        editText3 = findViewById(R.id.id_obuchaemyh);
        editText4 = findViewById(R.id.id_uprazhneniye);

        textViewVsego = findViewById(R.id.id_vsego);
        textViewPervaya = findViewById(R.id.id_pervaya);
        textViewVtoraya = findViewById(R.id.id_vtoraya);

    }

    public void onMyButtonClick(View view) {
        num = 0;
        textPoluch = editText1.getText().toString();
        textOstatok = editText2.getText().toString();
        textObuchaysh = editText3.getText().toString();
        textPatron_uprazh = editText4.getText().toString();

        if (textPoluch.isEmpty() || textOstatok.isEmpty() || textObuchaysh.isEmpty() || textPatron_uprazh.isEmpty()){
            textViewPervaya.setText("Введите все данные!");
            textViewVtoraya.setText("Введите все данные!");
            textViewVsego.setText("Введите все данные!");
            return;
        }

        int poluch = Integer.parseInt(textPoluch);
        int ostatok = Integer.parseInt(textOstatok);
        int obuchaysh = Integer.parseInt(textObuchaysh);
        int patron_uprazh = Integer.parseInt(textPatron_uprazh);

        if (poluch == 0 || obuchaysh == 0 || patron_uprazh == 0){
            textViewPervaya.setText("Введите число больше 0!");
            textViewVtoraya.setText("Введите число больше 0!");
            textViewVsego.setText("Введите число больше 0!");
            return;
        }

        int izrashodovano = poluch - ostatok;
        int kol_vypolnen_uprazh = izrashodovano / patron_uprazh;
        if(izrashodovano % patron_uprazh !=0){
            textViewPervaya.setText("Введены неверные данные!");
            textViewVtoraya.setText("Введены неверные данные! ");
            textViewVsego.setText("Введены неверные данные! ");
        }

        int poln_vypoln = kol_vypolnen_uprazh / obuchaysh;
        int chelovek_pervaya_kategoriya = kol_vypolnen_uprazh % obuchaysh;
        int chelovek_vtoraya_kategoriya = obuchaysh - chelovek_pervaya_kategoriya;
        int izrash_pervoy_kategoriey = (poln_vypoln + 1) * patron_uprazh;
        int izrash_vtoroy_kategoriey = poln_vypoln * patron_uprazh;

        if (chelovek_pervaya_kategoriya * izrash_pervoy_kategoriey + chelovek_vtoraya_kategoriya * izrash_vtoroy_kategoriey == izrashodovano){
            if (chelovek_pervaya_kategoriya == 0){
                textViewVsego.setText("Всего израсходовано: " + String.valueOf(izrashodovano));
                textViewPervaya.setText(" ");
                textViewVtoraya.setText(String.valueOf(chelovek_vtoraya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_vtoroy_kategoriey));

                } else if (izrash_pervoy_kategoriey == 0){
                textViewVsego.setText("Всего израсходовано: " + String.valueOf(izrashodovano));
                textViewPervaya.setText(String.valueOf(chelovek_pervaya_kategoriya) + " человек не стреляли ");
                textViewVtoraya.setText(String.valueOf(chelovek_vtoraya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_vtoroy_kategoriey));

                } else if (chelovek_vtoraya_kategoriya == 0) {
                textViewVsego.setText("Всего израсходовано: " + String.valueOf(izrashodovano));
                textViewPervaya.setText(String.valueOf(chelovek_pervaya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_pervoy_kategoriey));
                textViewVtoraya.setText(" ");

                } else if (izrash_vtoroy_kategoriey == 0) {
                textViewVsego.setText("Всего израсходовано: " + String.valueOf(izrashodovano));
                textViewPervaya.setText(String.valueOf(chelovek_pervaya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_pervoy_kategoriey));
                textViewVtoraya.setText(String.valueOf(chelovek_vtoraya_kategoriya) + " человек не стреляли ");

                } else {
                textViewVsego.setText("Всего израсходовано: " + String.valueOf(izrashodovano));
                textViewPervaya.setText(String.valueOf(chelovek_pervaya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_pervoy_kategoriey));
                textViewVtoraya.setText(String.valueOf(chelovek_vtoraya_kategoriya) + " человек израсходовали по " + String.valueOf(izrash_vtoroy_kategoriey));
                }

            } else {
            textViewPervaya.setText("Ошибка!");
            textViewVtoraya.setText("Ошибка!");
            textViewVsego.setText("Ошибка!");
        }
        
        if (ostatok == 0){
            Toast toast = Toast.makeText(this, "Славная была охота!",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onClick(View view) {
        num += 1;
        if (num == 15){
            Toast toast = Toast.makeText(this, "Да сброшено уже всё!",Toast.LENGTH_LONG);
            toast.show();
        }
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        editText4.getText().clear();
        textViewVsego.setText("");
        textViewPervaya.setText("");
        textViewVtoraya.setText("");
    }
}