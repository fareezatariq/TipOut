package com.hfad.tipout

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.hfad.tipout.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
 private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener{calculateTip()}
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
        }

    private fun calculateTip() {
        val stringInTextField= binding.costOfServiceEditText.text.toString()
        val cost= stringInTextField.toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text=""
            return
        }
        val tipPercentage= when(binding.tipOption.checkedRadioButtonId){
            R.id.twenty_percent ->0.20
            R.id.fifteen_percent ->0.15
            else -> 0.10
        }
        var tip= tipPercentage*cost
        if (binding.roundupSwitch.isChecked){
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount, formattedTip)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}
