package es.iessaladillo.pedrojoya.exchange

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.doAfterTextChanged
import es.iessaladillo.pedrojoya.exchange.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var b: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = MainActivityBinding.inflate(layoutInflater)
        setContentView(b.root)
        // TODO
    }

}