package com.hfad.tipout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.tipout.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
 private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener{calculateTip()}
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
}
