package ke.co.tracom.currency

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import ke.co.tracom.currency.databinding.ActivityMainBinding

import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var radioButton:RadioButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertBtn.setOnClickListener {
            convertCurr()
            binding.rateText.visibility = View.VISIBLE
            binding.rateLabel.visibility = View.VISIBLE
            binding.convertedCurr.visibility = View.VISIBLE
        }
    }

    private fun convertCurr() {

        var amountEntered = binding.amountEntry.text.toString()
        if (amountEntered == "") {
            amountEntered = "1";
        }
        val amt = amountEntered.toDouble()
        val selectedOption = binding.currOptions.checkedRadioButtonId
        radioButton = findViewById<RadioButton>(selectedOption)


        //calculate converted currency
        val rate = when (selectedOption) {
            R.id.USD -> 118.65
            R.id.EUR ->121.64
            R.id.GBP -> 143.07
            R.id.JPY -> 0.87
            R.id.CAD -> 92.23
            R.id.CHF -> 123.31
            R.id.ZAR -> 7.10
            else -> 0.00
        }
        val convertedAmt = amt / rate

        val df = DecimalFormat("0.000")
        val finalAmt = df.format(convertedAmt)
        binding.convertedCurr.text = finalAmt + " " + radioButton.text
        binding.rateText.text = "1 " + radioButton.text + " = " + rate + " KES"


    }
}