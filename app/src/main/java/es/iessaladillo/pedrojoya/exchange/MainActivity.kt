package es.iessaladillo.pedrojoya.exchange

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.iessaladillo.pedrojoya.exchange.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var lblAmount: TextView
    private lateinit var b: MainActivityBinding
    private lateinit var inputAmount: EditText
    private lateinit var rdgFromCoins: RadioGroup
    private lateinit var fromIcon: TextView
    private lateinit var rdgToCoins: RadioGroup
    private lateinit var toIcon: TextView

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
        setInitialState(lblAmount)
        inputAmount.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            changeTextViewColorOnFocus(lblAmount, hasFocus)
        }
        rdgFromCoins.setOnCheckedChangeListener { _, checkedId ->
            fromIcon.setCompoundDrawablesWithIntrinsicBounds(changeIcon(checkedId), 0, 0, 0)}
        rdgToCoins.setOnCheckedChangeListener { _, checkedId ->
            toIcon.setCompoundDrawablesWithIntrinsicBounds(changeIcon(checkedId), 0, 0, 0)}
    }

    private fun changeIcon(checkedId: Int): Int {
        var icon = 0

        if(checkedId == b.fromDollar.id || checkedId == b.toDollar.id){
            icon = R.drawable.ic_dollar
        } else if (checkedId == b.fromEuro.id || checkedId == b.toEuro.id){
            icon = R.drawable.ic_euro
        } else if (checkedId == b.fromPound.id || checkedId == b.toPound.id) icon = R.drawable.ic_pound

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
