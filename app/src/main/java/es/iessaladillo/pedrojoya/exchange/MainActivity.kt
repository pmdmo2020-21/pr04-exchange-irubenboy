package es.iessaladillo.pedrojoya.exchange

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.exchange.databinding.MainActivityBinding
import es.iessaladillo.pedrojoya.exchange.utils.hideSoftKeyboard

class MainActivity : AppCompatActivity() {

    private lateinit var lblAmount: TextView
    private lateinit var b: MainActivityBinding
    private lateinit var inputAmount: EditText
    private lateinit var rdgFromCoins: RadioGroup
    private lateinit var fromIcon: TextView
    private lateinit var rdgToCoins: RadioGroup
    private lateinit var toIcon: TextView
    private lateinit var btnExchange: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = MainActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        setupViews()
    }

    private fun setupViews() {
        lblAmount = b.lblAmount
        inputAmount = b.inputAmount
        rdgFromCoins = b.rdgFromCoins
        fromIcon = b.fromIcon
        rdgToCoins = b.rdgToCoins
        toIcon = b.toIcon
        btnExchange = b.btnExchange
        inputAmount.requestFocus()
        inputAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            changeTextViewColorOnFocus(lblAmount, hasFocus)
        }
        rdgFromCoins.setOnCheckedChangeListener { _, checkedId ->
            showToCoin()
            hideCoin(checkedId)
            fromIcon.setCompoundDrawablesWithIntrinsicBounds(changeIcon(checkedId), 0, 0, 0)}
        rdgToCoins.setOnCheckedChangeListener { _, checkedId ->
            showFromCoin()
            hideCoin(checkedId)
            toIcon.setCompoundDrawablesWithIntrinsicBounds(changeIcon(checkedId), 0, 0, 0)}
        inputAmount.doAfterTextChanged { validate() }
        btnExchange.setOnClickListener { btnOnClick()}
    }

    private fun showToCoin() {
        b.toDollar.isEnabled = true
        b.toEuro.isEnabled = true
        b.toPound.isEnabled = true
    }

    private fun showFromCoin() {
        b.fromDollar.isEnabled = true
        b.fromEuro.isEnabled = true
        b.fromPound.isEnabled = true
    }

    private fun btnOnClick() {
        inputAmount.hideSoftKeyboard()
        var fromCoin = ""
        var toCoin = ""


        when {
            b.fromDollar.isChecked -> {
                fromCoin = Currency.DOLLAR.toString()
            }
            b.fromEuro.isChecked -> {
                fromCoin = Currency.EURO.toString()
            }
            b.fromPound.isChecked -> {
                fromCoin = Currency.POUND.toString()
            }
        }
        when {
            b.toDollar.isChecked -> {
                toCoin = Currency.DOLLAR.toString()
            }
            b.toEuro.isChecked -> {
                toCoin = Currency.EURO.toString()
            }
            b.toPound.isChecked -> {
                toCoin = Currency.POUND.toString()
            }
        }
        val value = inputAmount.text.toString().toDouble().exchange(
                fromCoin,  toCoin).toString()
        Toast.makeText(this, getString(R.string.main_exchange,
            inputAmount.text.toString() + fromCoin.toCurrency().symbol,
            value + toCoin.toCurrency().symbol),
            Toast.LENGTH_SHORT).show()
    }

    private fun validate(){
        var count = 0

        for(i in inputAmount.text.toString().indices) {
            if(inputAmount.text.toString()[i] == '.'){
                count++
            }
        }

        if(inputAmount.text.toString().isEmpty() || inputAmount.text.toString().startsWith(".") || count > 1){
            inputAmount.setText(R.string.value)
            inputAmount.selectAll()
        }
    }

    private fun hideCoin(checkedId: Int){
        when (checkedId) {
            b.fromDollar.id -> {
                b.toDollar.isEnabled = false
            }
            b.fromEuro.id -> {
                b.toEuro.isEnabled = false
            }
            b.fromPound.id -> {
                b.toPound.isEnabled = false
            }
            b.toDollar.id -> {
                b.fromDollar.isEnabled = false
            }
            b.toEuro.id -> {
                b.fromEuro.isEnabled = false
            }
            b.toPound.id -> {
                b.fromPound.isEnabled = false
            }
        }
    }

    private fun changeIcon(checkedId: Int): Int {
        var icon = 0

        if(checkedId == b.fromDollar.id || checkedId == b.toDollar.id){
            icon = Currency.DOLLAR.drawableResId
        } else if (checkedId == b.fromEuro.id || checkedId == b.toEuro.id){
            icon = Currency.EURO.drawableResId
        } else if (checkedId == b.fromPound.id || checkedId == b.toPound.id) icon = Currency.POUND.drawableResId

        return icon
    }

    private fun changeTextViewColorOnFocus(v : TextView, hasFocus: Boolean){
       if (hasFocus) {
           v.setTextColor(ContextCompat.getColor(this, R.color.pink_200))
       } else {
           setInitialState(v)
       }
    }


    private fun setInitialState(v: TextView) {
        v.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

}

private fun Double.exchange(fromCoin: String, toCoin: String): Double {
    return toCoin.toCurrency().fromDollar(fromCoin.toCurrency().toDollar(this))
}
