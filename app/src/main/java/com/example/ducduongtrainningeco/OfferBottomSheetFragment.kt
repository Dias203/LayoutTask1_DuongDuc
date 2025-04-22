package com.example.ducduongtrainningeco

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ducduongtrainningeco.databinding.LayoutBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OfferBottomSheetFragment : BottomSheetDialogFragment() {

    // Khai báo biến binding
    private var _binding: LayoutBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): OfferBottomSheetFragment {
            return OfferBottomSheetFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LayoutBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            setupBottomSheetAppearance(bottomSheet)
            setupBottomSheetBehavior(bottomSheet)
        }
        return dialog
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClaimOffer.setOnClickListener {
            dismiss()
        }
        setupOldPrice()
        setupSlipText()
        setTextTermsAndPrivacy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //  Giao diện (nền trong suốt, dim 0)
    private fun setupBottomSheetAppearance(bottomSheet: View?) {
        bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
        dialog?.window?.setDimAmount(0f)
    }

    // Chiều cao, behavior, layout
    private fun setupBottomSheetBehavior(bottomSheet: View?) {
        bottomSheet ?: return
        val behavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheet.post {
            val screenHeight = resources.displayMetrics.heightPixels
            val halfHeight = screenHeight / 1.3f

            behavior.peekHeight = halfHeight.toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            behavior.skipCollapsed = false
            behavior.isHideable = true

            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            bottomSheet.requestLayout()
        }
    }



    private fun setupOldPrice() {
        setupStrikethroughText(binding.tvOldPrice)
    }

    private fun setupStrikethroughText(textView: TextView) {
        val text = textView.text.toString()
        val spannableString = SpannableString(text)

        val strikethroughSpan = StrikethroughSpan()
        spannableString.setSpan(strikethroughSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString

    }

    private fun setupSlipText() {
        val str = "I’ll let this deal slip away"
        val spannableString = SpannableString(str)
        val startIndex = str.indexOf(str)
        val endIndex = startIndex + str.length

        spannableString.setSpan(
            createClickableSpan { dismiss() },
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tv5.text = spannableString
        binding.tv5.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setTextTermsAndPrivacy() {
        val fullText = getString(R.string.full_text)
        val spannable = SpannableString(fullText)

        val termsStart = fullText.indexOf("Terms")
        val termsEnd = termsStart + "Terms".length

        val privacyStart = fullText.indexOf("Privacy")
        val privacyEnd = privacyStart + "Privacy".length

        spannable.setSpan(
            createClickableSpan {
                Toast.makeText(requireContext(), "Terms clicked", Toast.LENGTH_SHORT).show()
            },
            termsStart,
            termsEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            createClickableSpan {
                Toast.makeText(requireContext(), "Privacy clicked", Toast.LENGTH_SHORT).show()
            },
            privacyStart,
            privacyEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tv6.text = spannable
        binding.tv6.movementMethod = LinkMovementMethod.getInstance()
        binding.tv6.highlightColor = Color.TRANSPARENT
    }


    private fun createClickableSpan(onClick: () -> Unit): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) = onClick()

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#313131")
                ds.isUnderlineText = true
            }
        }
    }



}