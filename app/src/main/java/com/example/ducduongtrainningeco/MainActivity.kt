package com.example.ducduongtrainningeco

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ducduongtrainningeco.databinding.ActivityMainBinding
import com.example.ducduongtrainningeco.databinding.ItemSubscriptionBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSubscriptionViews()
        setupPrivacyPolicyText()
        setupOldPrice()
    }

    private fun setupSubscriptionViews() {
        setSelectedSubscription()
        setupSubscriptionTexts()
    }

    private fun setSelectedSubscription() {
        // Initial selection
        selectSubscription(binding.sevenDay)

        // Set up click listeners
        binding.sevenDay.itemContainer.setOnClickListener {
            selectSubscription(binding.sevenDay)
            deselectSubscription(binding.lifeTime)
            deselectSubscription(binding.monthly)
        }

        binding.lifeTime.itemContainer.setOnClickListener {
            selectSubscription(binding.lifeTime)
            deselectSubscription(binding.sevenDay)
            deselectSubscription(binding.monthly)
        }

        binding.monthly.itemContainer.setOnClickListener {
            selectSubscription(binding.monthly)
            deselectSubscription(binding.sevenDay)
            deselectSubscription(binding.lifeTime)
        }
    }

    private fun setupSubscriptionTexts() {
        binding.monthly.apply {
            textDuration.text = getString(R.string.monthly)
        }
        binding.lifeTime.apply {
            textDuration.text = getString(R.string.life_time)
        }
    }

    private fun setupPrivacyPolicyText() {
        val spannableString = SpannableString(getString(R.string.policy))
        val startIndex = getString(R.string.policy).indexOf("Privacy Policy")
        val endIndex = startIndex + "Privacy Policy".length

        val clickableSpan = createPrivacyPolicyClickableSpan()
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvDesPolicy.text = spannableString
        binding.tvDesPolicy.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupOldPrice() {

        setupStrikethroughText(
            binding.sevenDay.textOldPrice,
            binding.lifeTime.textOldPrice,
            binding.monthly.textOldPrice
        )
    }

    private fun createPrivacyPolicyClickableSpan(): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#313131")
                ds.isUnderlineText = true
            }
        }
    }

    private fun setupStrikethroughText(vararg textViews: TextView) {
        for (textView in textViews) {
            val text = textView.text.toString()
            val spannableString = SpannableString(text)

            val strikethroughSpan = StrikethroughSpan()
            spannableString.setSpan(strikethroughSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            textView.text = spannableString
        }
    }

    private fun selectSubscription(view: ItemSubscriptionBinding) {
        view.apply {
            itemContainer.setBackgroundResource(R.drawable.bg_subscription_selected)
            itemContainer.scaleX = 1.10f
            itemContainer.scaleY = 1.10f
            textDuration.scaleX = 0.92f
            textDuration.scaleY = 0.92f
            textPrice.scaleX = 0.92f
            textPrice.scaleY = 0.92f
        }
    }

    private fun deselectSubscription(view: ItemSubscriptionBinding) {
        view.apply {
            itemContainer.setBackgroundResource(R.drawable.bg_subscription_default)
            itemContainer.scaleX = 1f
            itemContainer.scaleY = 1f
            textDuration.scaleX = 1f
            textDuration.scaleY = 1f
            textPrice.scaleX = 1f
            textPrice.scaleY = 1f
        }
    }
}