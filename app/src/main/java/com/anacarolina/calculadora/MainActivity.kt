package com.anacarolina.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var text_resultado: TextView
    private lateinit var expressao: TextView

    private lateinit var numeroZero: TextView
    private lateinit var numeroUm: TextView
    private lateinit var numeroDois: TextView
    private lateinit var numeroTres: TextView
    private lateinit var numeroQuatro: TextView
    private lateinit var numeroCinco: TextView
    private lateinit var numeroSeis: TextView
    private lateinit var numeroSete: TextView
    private lateinit var numeroOito: TextView
    private lateinit var numeroNove: TextView

    private lateinit var limpar: TextView
    private lateinit var divisao: TextView
    private lateinit var multiplicacao: TextView
    private lateinit var menos: TextView
    private lateinit var mais: TextView
    private lateinit var apagar: ImageView
    private lateinit var igual: TextView
    private lateinit var ponto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        iniciaView()
        iniciaNumeros()
        iniciaOperadores()
        click()

    }

    private fun iniciaView() {
        text_resultado = findViewById(R.id.Text_resultado)
        expressao = findViewById(R.id.expressao)
    }

    private fun iniciaNumeros() {
        numeroZero = findViewById(R.id.numero_zero)
        numeroUm = findViewById(R.id.numero_um)
        numeroDois = findViewById(R.id.numero_dois)
        numeroTres = findViewById(R.id.numero_tres)
        numeroQuatro = findViewById(R.id.numero_quatro)
        numeroCinco = findViewById(R.id.numero_cinco)
        numeroSeis = findViewById(R.id.numero_seis)
        numeroSete = findViewById(R.id.numero_sete)
        numeroOito = findViewById(R.id.numero_oito)
        numeroNove = findViewById(R.id.numero_nove)
    }

    private fun iniciaOperadores() {
        limpar = findViewById(R.id.limpar)
        divisao = findViewById(R.id.divisao)
        multiplicacao = findViewById(R.id.multiplicacao)
        menos = findViewById(R.id.menos)
        mais = findViewById(R.id.mais)
        apagar = findViewById(R.id.apagar)
        igual = findViewById(R.id.igual)
        ponto = findViewById(R.id.ponto)
    }

    private fun click() {
        numeroZero.setOnClickListener { acrescentarExpressao("0", true) }
        numeroUm.setOnClickListener { acrescentarExpressao("1", true) }
        numeroDois.setOnClickListener { acrescentarExpressao("2", true) }
        numeroTres.setOnClickListener { acrescentarExpressao("3", true) }
        numeroQuatro.setOnClickListener { acrescentarExpressao("4", true) }
        numeroCinco.setOnClickListener { acrescentarExpressao("5", true) }
        numeroSeis.setOnClickListener { acrescentarExpressao("6", true) }
        numeroSete.setOnClickListener { acrescentarExpressao("7", true) }
        numeroOito.setOnClickListener { acrescentarExpressao("8", true) }
        numeroNove.setOnClickListener { acrescentarExpressao("9", true) }
        ponto.setOnClickListener { acrescentarExpressao(".", true) }

        //Operadores
        mais.setOnClickListener { acrescentarExpressao("+", false) }
        menos.setOnClickListener { acrescentarExpressao("-", false) }
        divisao.setOnClickListener { acrescentarExpressao("/", false) }
        multiplicacao.setOnClickListener { acrescentarExpressao("*", false) }

        limpar.setOnClickListener {
            expressao.text = ""
            text_resultado.text = ""
        }

        apagar.setOnClickListener {
            val string = expressao.text.toString()

            if (string.isNotBlank()) {
                expressao.text = string.substring(0, string.length - 1)
            }
            text_resultado.text = ""
        }

        igual.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(expressao.text.toString()).build()

                val resultado = expressao.evaluate()
                val longResultado = resultado.toLong()

                if (resultado == longResultado.toDouble())
                    text_resultado.text = longResultado.toString()
                else
                    text_resultado.text = resultado.toString()


            } catch (e: Exception) {

            }
        }

    }

    private fun acrescentarExpressao(string: String, limpar_dados: Boolean) {
        if (text_resultado.text.isNotEmpty()) {
            expressao.text = ""
        }

        if (limpar_dados) {
            text_resultado.text = ""
            expressao.append(string)
        } else {
            expressao.append(text_resultado.text)
            expressao.append(string)
            text_resultado.text = ""
        }
    }

}
