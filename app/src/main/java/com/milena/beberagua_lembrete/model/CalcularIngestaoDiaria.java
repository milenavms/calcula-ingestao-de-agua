package com.milena.beberagua_lembrete.model;

import android.util.Log;

public class CalcularIngestaoDiaria {

    Double ML_JOVEM = 40.0;
    Double ML_ADULTO = 35.0;
    Double ML_IDOSO = 30.0;
    Double ML_MAIS_DE_66_ANOS = 25.0;

    Double resultadoML = 0.0;
    Double resultado_total_ml = 0.0;

    public void calcularTotalML(Double peso, Integer idade) {

        if (idade <= 17) {
            resultadoML = peso * ML_JOVEM;
            resultado_total_ml = resultadoML;
        } else if (idade <= 35) {
            resultadoML = peso * ML_ADULTO;
            resultado_total_ml = resultadoML;
            Log.i("total", "ML_ADULTO: " +ML_ADULTO + "peso:" + peso);
        } else if (idade <= 65) {
            resultadoML = peso * ML_IDOSO;
            resultado_total_ml = resultadoML;
        } else {
            resultadoML = peso * ML_MAIS_DE_66_ANOS;
            resultado_total_ml = resultadoML;
        }

    }

    public double resultadoML(){
        return resultado_total_ml;
    }
}



