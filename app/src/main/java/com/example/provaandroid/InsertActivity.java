package com.example.provaandroid;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private EditText etLeucocitos;
    private EditText etGlicemia;
    private EditText etAsttgo;
    private EditText etLDH;
    private CheckBox checkBox;
    private Button buttonAdd;
    private TextView tvPontuacao1;
    private TextView tvMortalidade1;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        etLeucocitos = findViewById(R.id.etLeucocitos);
        etGlicemia = findViewById(R.id.etGlicemia);
        etAsttgo = findViewById(R.id.etAsttgo);
        etLDH = findViewById(R.id.etLDH);

        final CheckBox litiaseCheckbox = findViewById(R.id.litiaseCheckbox);
        Button buttonAdd = findViewById(R.id.btAddPaciente);

        tvPontuacao1 = findViewById(R.id.tvPontuacao1);
        tvMortalidade1 = findViewById(R.id.tvMortalidade1);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String nome = etNome.getText().toString();
                    int idade = Integer.parseInt(etIdade.getText().toString());
                    double leucocitos = Double.parseDouble(etLeucocitos.getText().toString());
                    double glicemia = Double.parseDouble(etGlicemia.getText().toString());
                    double asttgo = Double.parseDouble(etAsttgo.getText().toString());
                    double ldh = Double.parseDouble(etLDH.getText().toString());
                    boolean litiase = litiaseCheckbox.isChecked();
                    int pontos = 0;
                    double mortalidade = 0;

                    //Condições
                    if (litiase){ //Se esta marcado a caixinha
                        if (idade > 70){
                            pontos++;
                        }
                        if (leucocitos > 18000){
                            pontos++;
                        }
                        if (glicemia > 12.2){
                            pontos++;
                        }
                        if (asttgo > 250){
                            pontos++;
                        }
                        if (ldh > 400){
                            pontos++;
                        }

                        else{ //Se a caixinha não estiver marcada
                            if (idade > 55){
                                pontos++;
                            }
                            if (leucocitos > 16000){
                                pontos++;
                            }
                            if (glicemia > 11){
                                pontos++;
                            }
                            if (asttgo > 250){
                                pontos++;
                            }
                            if (ldh > 350){
                                pontos++;
                            }
                        }
                    }

                    if (pontos == 0 || pontos == 1 || pontos == 2){
                        mortalidade = 2;
                    }
                    else if (pontos == 3 || pontos == 4){
                        mortalidade = 15;
                    }
                    else if (pontos == 5 || pontos == 6){
                        mortalidade = 40;
                    }
                    else if (pontos == 7 || pontos == 8){
                        mortalidade = 100;
                    }

                    Paciente paciente = new Paciente(nome, idade, leucocitos, glicemia, asttgo, ldh, litiase, pontos, mortalidade);  //Adiciona os valores em paciente
                    adicionaPaciente(paciente);

                    tvPontuacao1.setText("Pontuação: " + pontos);
                    tvMortalidade1.setText("Mortalidade: " + mortalidade + "%");

                    Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    Toast.makeText(InsertActivity.this, "Deu erro", Toast.LENGTH_SHORT).show();
                }

            }
        }); //Termina o OnclickListener
    }
    private void adicionaPaciente(Paciente paciente) {
        try {
            DAL dal = new DAL(InsertActivity.this);
            dal.insert(paciente); //vai no DAL pega o metodo insert, pega os dados do paciente e joga no DATABASE
        } catch (Exception e) {
            Toast.makeText(this, "Erro ao cadastrar os dados do paciente", Toast.LENGTH_SHORT).show();
        }
    }
}