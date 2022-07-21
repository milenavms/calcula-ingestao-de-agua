package com.milena.beberagua_lembrete;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.milena.beberagua_lembrete.model.CalcularIngestaoDiaria;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editarPeso;
    private  EditText editarIdade;
    private Button btCalcular;
    private ImageView redefinirDados;
    private TextView text_resultado_ml;
    private CalcularIngestaoDiaria calcularIngestaoDiaria;  //variável herda uma classe

    private Button bt_lembrete;
    private Button bt_alarme;
    private TextView text_hora;
    private TextView text_minutos;
    private Calendar calendario; //variável herda uma classe
    private TimePickerDialog timePickerDialog;

    private int horas;
    private int minutos;

    private Double resultadoML = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        calcularIngestaoDiaria = new CalcularIngestaoDiaria();   //inicializando a variável calcularIngestaoDiaria?

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editarPeso.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            R.string.text_informa_peso,
                            Toast.LENGTH_SHORT).show();
                }else if(editarIdade.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                                R.string.text_informa_idade,
                                Toast.LENGTH_SHORT).show();
                }else{
                    //get do peso e idade informado pelo usuário
                    double peso = Double.parseDouble(editarPeso.getText().toString());
                    Integer idade = Integer.valueOf(editarIdade.getText().toString());
                    //calculando o total de ml
                    calcularIngestaoDiaria.calcularTotalML(peso, idade);
                    resultadoML =  calcularIngestaoDiaria.resultadoML();
                    NumberFormat formatar = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
                    //setando na tela o resultado final de agua em ml
                    text_resultado_ml.setText(formatar.format(resultadoML) + " " + "ml");
                }
            }
        });


        redefinirDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle(R.string.dialog_titulo)
                        .setMessage(R.string.dialog_descricao)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editarPeso.setText("");
                                editarIdade.setText("");
                                text_resultado_ml.setText("");
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //nada
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }


        });


        bt_lembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //inicializando o timePickerDialog
                 timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horas = hourOfDay;
                        minutos = minute;
                         calendario = Calendar.getInstance();
                        //setando a hora e min
                        calendario.set(0,0,0,horas,minutos);
                        text_hora.setText(String.format("%02d", horas));
                        text_minutos.setText(String.format("%02d", minutos));

                    }
                },12,0,false
                );
                //Exibindo o tempo selecionado
                //timePickerDialog.updateTime(horas, minutos);
                timePickerDialog.show();

            }
        });

    }


    private void iniciarComponentes() {
        editarPeso = findViewById(R.id.editar_peso);
        editarIdade = findViewById(R.id.editar_idade);
        btCalcular = findViewById(R.id.bt_calcular);
        text_resultado_ml = findViewById(R.id.text_resultado_ml);
        redefinirDados = findViewById(R.id.ic_redefinir_dados);
        bt_alarme = findViewById(R.id.bt_alarme);
        bt_lembrete = findViewById(R.id.bt_definir_lembrete);
        text_hora = findViewById(R.id.txt_hora);
        text_minutos = findViewById(R.id.txt_minutos);


    }
}



