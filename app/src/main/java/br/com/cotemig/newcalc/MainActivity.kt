package br.com.cotemig.newcalc

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var valorStr = ""
        var temp = ""
        var sinal = ""
        var resultado = 0.0
        var fecha = 0
        var mode = 1

        //Formata a saída do número com separador de milhar e casas decimais
        //var dec = DecimalFormat("#,##0.00")
        var dec = DecimalFormat("#,###.#######")

        //Variavel para a exibição do Visor da Calculadora
        var tvVisor = findViewById<TextView>(R.id.tvVisor)

        //Variáveis para os botões de função
        var btAC = findViewById<Button>(R.id.btAC)
        val btVirgula = findViewById<Button>(R.id.btVrigula)
        val btMaisMenos = findViewById<Button>(R.id.btMaisMenos)
        val btCalcular = findViewById<Button>(R.id.btCalcular)

        //Variáveis para os botões de operações
        val btMais = findViewById<Button>(R.id.btMais)
        val btMenos = findViewById<Button>(R.id.btMenos)
        val btMultiplicacao = findViewById<Button>(R.id.btMiltiplicacao)
        val btDivisao = findViewById<Button>(R.id.btDivisao)
        val btPorcentagem = findViewById<Button>(R.id.btPorcentagem)


        //Variáveis para os botões numéricos
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


        //Variáveis para guardar pontos de Toque na tela
        var pDownX = 0
        var pDownY = 0
        var pUpX = 0
        var pUpY = 0

        //Variável para contar quantas vezes o touch foi ativado
        var cont = 0


        with(tvVisor) {
            setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    return true
                }

            })
        }


        tvVisor.setOnTouchListener { v, event ->
            val action = event.action

            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    pDownX = event.x.toInt()
                    pDownY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    //   Toast.makeText(this, "Action MOVE", Toast.LENGTH_SHORT).show()

                    //Detecta movimentos para direita /esquerda
                    if (pDownX > pUpX) {
                        // Toast.makeText(this, "Moveu p/ esquerda ${cont} ${pDownX} ${pUpX}", Toast.LENGTH_SHORT).show()

                        Log.i("analise mov ValorStr: ", valorStr)
                        Log.i("analise mov visor: ", tvVisor.text.toString())
                        Log.i("analise mov resultado: ", resultado.toString())

                        Log.i("analise mov3 sinal", sinal.toString())

                        if (sinal != "#" && !tvVisor.text.contains(".")) {

                            tvVisor.text = tvVisor.text.dropLast(1)


                            valorStr = tvVisor.text.toString()
                        }


                        // sinal = ""
                        // resultado = 0.0

                        Log.i("analise mov2 ValorStr: ", valorStr)
                        Log.i("analise mov2 visor: ", tvVisor.text.toString())
                        Log.i("analise mov2 resultado:", resultado.toString())

                        pDownX = pUpX

                    } else if (pDownX < pUpX) {
                        //   Toast.makeText(this, "Moveu direita ${cont} ${pDownX} ${pDownY}", Toast.LENGTH_SHORT).show()
                        Log.i("analise mov3 sinal", sinal.toString())

                        if (sinal != "#" && !tvVisor.text.contains(".")) {
                            tvVisor.text = tvVisor.text.drop(1)

                            // var decSp = DecimalFormat("####.###")
                            // visor.text = decSp.format(visor.text.toString().toDouble())

                            valorStr = tvVisor.text.toString()
                        }
                        //  sinal = ""
                        // resultado = 0.0

                        pDownX = pUpX
                    } else {
                        // Toast.makeText(this, "Mexeu nada ${cont} ${pDownX} ${pDownY}", Toast.LENGTH_SHORT).show()
                    }


                    //Detecta movimentos para cima /baixo
                    if (pDownY > pUpY) {
                        // Toast.makeText(this, "Moveu cima ${cont} ${pDownX} ${pDownY}", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(this, "Moveu baixo ${cont} ${pDownX} ${pDownY}", Toast.LENGTH_SHORT).show()

                    }
                    cont++

                    // return@setOnTouchListener false
                }
                MotionEvent.ACTION_UP -> {

                    // Toast.makeText(this, "Action UP", Toast.LENGTH_SHORT).show()
                    pUpX = event.x.toInt()
                    pUpY = event.y.toInt()
                    //  Toast.makeText(this, "Moveu Cima ${cont} ${pDownX} ${pDownY}", Toast.LENGTH_SHORT).show()
                }

                MotionEvent.ACTION_CANCEL -> {
                    Toast.makeText(this, "Action cancel", Toast.LENGTH_SHORT).show()

                }
                else -> {
                    Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }


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
            fecha = 0

        }









        btAC.setOnClickListener {
            tvVisor.text = "0"
            valorStr = ""
            resultado = 0.0
            sinal = "AC"
            fecha++
            if (fecha >= 2) {
                tvVisor.text = ""
                fecha = 0
            }

        }

        btAC.setOnLongClickListener {
            when(mode){
                1 -> {
                    Toast.makeText(this, "modo Calculadora financeira ativado", Toast.LENGTH_SHORT).show()
                    dec = DecimalFormat("#,##0.00")
                    mode = 2
                }
                2 -> {
                    Toast.makeText(this, "modo Calculadora padrão ativado", Toast.LENGTH_SHORT).show()
                    dec = DecimalFormat("#,###.#######")
                    mode = 1
                }

            }
            return@setOnLongClickListener true

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


        btPorcentagem.setOnClickListener {
            if (sinal == "+" || sinal == "-") {
                if (!valorStr.isEmpty()) {
                    valorStr = ((valorStr.replace(",", ".").toDouble() / 100) * resultado).toString()
                    tvVisor.text = dec.format(valorStr.toDouble())
                }
            } else {
                if (!valorStr.isEmpty() && !tvVisor.text.equals("-")) {
                    valorStr = ((valorStr.replace(",", ".").toDouble() / 100)).toString()
                    tvVisor.text = dec.format(valorStr.toDouble())
                }
            }
        }

        btCalcular.setOnClickListener {
            calcular()
            sinal = "#"
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