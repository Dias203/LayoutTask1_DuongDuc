package com.example.ducduongtrainningeco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ducduongtrainningeco.databinding.ActivitySecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnShowBottomSheet.setOnClickListener {
            //showOfferBottomSheet()
            showOfferBottomSheetDialog()
        }

    }

    private fun showOfferBottomSheet() {
        val bottomSheetFragment = OfferBottomSheetFragment.newInstance()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun showOfferBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}