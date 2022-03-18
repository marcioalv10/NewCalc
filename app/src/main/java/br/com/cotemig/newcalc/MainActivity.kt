package br.com.cotemig.newcalc

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var valorStr = ""
        var temp = ""
        var sinal = ""
        var resultado = 0.0

        //Formata a saída do número com separador de milhar e casas decimais
        //var dec = DecimalFormat("#,##0.00")
        var dec = DecimalFormat("#,###.#######")

        var tvVisor = findViewById<TextView>(R.id.tvVisor)
        var btAC = findViewById<Button>(R.id.btAC)
        val btVirgula = findViewById<Button>(R.id.btVrigula)
        val btMaisMenos = findViewById<Button>(R.id.btMaisMenos)

        val btMais = findViewById<Button>(R.id.btMais)
        val btMenos = findViewById<Button>(R.id.btMenos)
        val btMultiplicacao = findViewById<Button>(R.id.btMiltiplicacao)
        val btDivisao = findViewById<Button>(R.id.btDivisao)

        val btCalcular = findViewById<Button>(R.id.btCalcular)

        //Botoes numericos
        var bt0 = findViewById<Button>(R.id.bt0)
        var bt1 = findViewById<Button>(R.id.bt1)
        var bt2 = findViewById<Button>(R.id.bt2)
        var bt3 = findViewById<Button>(R.id.bt3)
        var bt4 = findViewById<Button>(R.id.bt4)
        var bt5 = findViewById<Button>(R.id.bt5)
        var bt6 = findViewById<Button>(R.id.bt6)
        var bt7 = findViewById<Button>(R.id.bt7)
        var bt8 = findViewById<Button>(R.id.bt8)
        var bt9 = findViewById<Button>(R.id.bt9)

        fun calcular() {

            if (!valorStr.isEmpty() && !tvVisor.text.equals("-"))
                when (sinal) {
                    "+" -> resultado += valorStr.replace(",", ".").toDouble()
                    "-" -> resultado -= valorStr.replace(",", ".").toDouble()
                    "*" -> resultado *= valorStr.replace(",", ".").toDouble()
                    ":" -> resultado /= valorStr.replace(",", ".").toDouble()

                    else -> resultado = valorStr.replace(",", ".").toDouble()
                }

            tvVisor.text = dec.format(resultado)

            valorStr = ""
            sinal = ""
        }


        fun preenche(algo: String) {
            if (!valorStr.equals("0") && !valorStr.equals("-0") && !valorStr.contains(".")) {
                valorStr += algo
            } else {
                valorStr = algo
            }

            tvVisor.text = valorStr

        }









        btAC.setOnClickListener {
            tvVisor.text = "0"
            valorStr = ""

            resultado = 0.0
            sinal = "AC"

        }

        btVirgula.setOnClickListener {

            if (valorStr.isEmpty()) {
                temp = "0"
                valorStr += temp
                tvVisor.text = valorStr
            }

            if (!valorStr.contains(",") && !valorStr.contains(".")) {

                if (!tvVisor.text.equals("-")) {
                    temp = ","
                    valorStr += temp
                    tvVisor.text = valorStr
                }
            }
        }

        btMaisMenos.setOnClickListener {
            if (!valorStr.equals("") && !valorStr.equals("0") && !tvVisor.text.equals("") && !tvVisor.text.equals("-")) {
                valorStr = (valorStr.replace(",", ".").toDouble() * -1).toString()

                tvVisor.text = dec.format(valorStr.toDouble())
            } else {
                valorStr = "-"
                tvVisor.text = valorStr
            }
        }

        btMais.setOnClickListener {
            if (!valorStr.isEmpty() && !tvVisor.text.equals("-")) {
                calcular()
            }
            valorStr = ""
            sinal = "+"
        }

        btMenos.setOnClickListener {
            if (!valorStr.isEmpty() && !tvVisor.text.equals("-")) {
                calcular()
            }

            valorStr = ""
            sinal = "-"
        }

        btMultiplicacao.setOnClickListener {
            if (!valorStr.isEmpty() && !tvVisor.text.equals("-")) {
                calcular()
            }
            valorStr = ""
            sinal = "*"

        }


        btDivisao.setOnClickListener {
            if (!valorStr.isEmpty() && !tvVisor.text.equals("-")) {
                calcular()
            }

            valorStr = ""
            sinal = ":"
        }

        btCalcular.setOnClickListener {
            calcular()
        }

        bt0.setOnClickListener {
            // tvVisor.text = "0"
            preenche("0")
        }

        bt1.setOnClickListener {
            // tvVisor.text = "1"
            preenche("1")
        }
        bt2.setOnClickListener {
            //  tvVisor.text = "2"
            preenche("2")
        }
        bt3.setOnClickListener {
            preenche("3")
        }
        bt4.setOnClickListener {
            preenche("4")
        }
        bt5.setOnClickListener {
            preenche("5")
        }
        bt6.setOnClickListener {
            preenche("6")
        }
        bt7.setOnClickListener {
            preenche("7")
        }
        bt8.setOnClickListener {
            preenche("8")
        }
        bt9.setOnClickListener {
            preenche("9")
        }


    }


}