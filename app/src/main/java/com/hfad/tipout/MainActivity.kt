package com.hfad.tipout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.tipout.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
 lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener{CalculateTip()}
    }

    fun CalculateTip() {
        val stringInTextField= binding.costOfService.text.toString()
        val cost= stringInTextField.toDouble()
        val selectId=binding.tipOption.checkedRadioButtonId
        val tipPercentage= when(selectId){
            R.id.twenty_percent ->0.20
            R.id.fifteen_percent ->0.15
            else -> 0.10
        }
        var tip= tipPercentage*cost
        val roundUp=binding.roundupSwitch.isChecked
        if (roundUp){
            tip=kotlin.math.ceil(tip)
        }
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text=getString(R.string.tip_amount, formattedTip)
    }
}
