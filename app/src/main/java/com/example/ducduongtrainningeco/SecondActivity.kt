package com.example.ducduongtrainningeco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ducduongtrainningeco.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnShowBottomSheet.setOnClickListener {
            showOfferBottomSheet()
        }

    }

    private fun showOfferBottomSheet() {
        val bottomSheetFragment = OfferBottomSheetFragment.newInstance()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}