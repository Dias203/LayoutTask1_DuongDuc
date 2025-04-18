package com.example.ducduongtrainningeco

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ducduongtrainningeco.databinding.ActivityMainBinding

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
        setupSubscriptionClickListeners()
    }

    private fun setupSubscriptionClickListeners() {
        binding.apply {
            sevenDayBtn.setOnClickListener {
                handleSubscriptionSelection(
                    selectedView = sevenDayBtn,
                    selectedTextViews = arrayOf(tvSevenDayDuration, tvSevenDayPrice, tvSevenDayOldPrice),
                    lifeTimeBtn to arrayOf(textDurationLifeTime, textPriceLifetime, textOldPriceLifetime),
                    monthlyBtn to arrayOf(tvMonthlyDuration, tvMonthlyPrice, tvMonthlyOldPrice)
                )
            }

            lifeTimeBtn.setOnClickListener {
                handleSubscriptionSelection(
                    selectedView = lifeTimeBtn,
                    selectedTextViews = arrayOf(textDurationLifeTime, textPriceLifetime, textOldPriceLifetime),
                    sevenDayBtn to arrayOf(tvSevenDayDuration, tvSevenDayPrice, tvSevenDayOldPrice),
                    monthlyBtn to arrayOf(tvMonthlyDuration, tvMonthlyPrice, tvMonthlyOldPrice)
                )
            }

            monthlyBtn.setOnClickListener {
                handleSubscriptionSelection(
                    selectedView = monthlyBtn,
                    selectedTextViews = arrayOf(tvMonthlyDuration, tvMonthlyPrice, tvMonthlyOldPrice),
                    sevenDayBtn to arrayOf(tvSevenDayDuration, tvSevenDayPrice, tvSevenDayOldPrice),
                    lifeTimeBtn to arrayOf(textDurationLifeTime, textPriceLifetime, textOldPriceLifetime)
                )
            }
        }
    }

    private fun handleSubscriptionSelection(
        selectedView: ConstraintLayout,
        selectedTextViews: Array<TextView>,
        vararg viewsToDeselect: Pair<ConstraintLayout, Array<TextView>>
    ) {
        selectSubscription(selectedView)
        scaleSelectedText(*selectedTextViews)

        viewsToDeselect.forEach { (view, textViews) ->
            deselectSubscription(view)
            resetTextScale(*textViews)
        }
    }

    private fun scaleSelectedText(vararg textViews: TextView) {
        textViews.forEach {
            it.scaleX = 1.1f
            it.scaleY = 1.1f
        }
    }

    private fun resetTextScale(vararg textViews: TextView) {
        textViews.forEach {
            it.scaleX = 1.0f
            it.scaleY = 1.0f
        }
    }

    private fun selectSubscription(view: ConstraintLayout) {
        view.apply {
            setBackgroundResource(R.drawable.bg_subscription_selected)
            scaleX = 1.15f
            scaleY = 1.10f
        }
    }

    private fun deselectSubscription(view: ConstraintLayout) {
        view.apply {
            setBackgroundResource(R.drawable.bg_subscription_default)
            scaleX = 1.0f
            scaleY = 1.0f
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

    private fun createPrivacyPolicyClickableSpan(): ClickableSpan {
        return object : ClickableSpan() {
            override fun onClick(widget: View) {
                // TODO: Implement navigation to privacy policy
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#313131")
                ds.isUnderlineText = true
            }
        }
    }

    private fun setupOldPrice() {
        setupStrikethroughText(
            binding.tvSevenDayOldPrice,
            binding.textOldPriceLifetime,
            binding.tvMonthlyOldPrice
        )
    }

    private fun setupStrikethroughText(vararg textViews: TextView) {
        for (textView in textViews) {
            val text = textView.text.toString()
            val spannableString = SpannableString(text)

            val strikethroughSpan = StrikethroughSpan()
            spannableString.setSpan(strikethroughSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val italicSpan = StyleSpan(Typeface.ITALIC)
            spannableString.setSpan(
                italicSpan,
                0,
                text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannableString
        }
    }
}
