package com.organeco.view.activity.guest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.organeco.R
import com.organeco.databinding.ActivityGuestBinding
import com.organeco.model.local.fertilizer.DataDummySource
import com.organeco.model.local.adapter.GuestAdapter
import com.organeco.view.activity.auth.login.LoginActivity
import com.organeco.view.viewpager.ImageData
import com.organeco.view.viewpager.ImageSliderAdapter
import kotlin.math.abs
import kotlin.math.min

class GuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding
    private lateinit var guestAdapter: GuestAdapter
    private lateinit var adapterSlider: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        guestAdapter = GuestAdapter(DataDummySource.getDummyDataList())
        binding.rvFertilizer.layoutManager = LinearLayoutManager(this)
        binding.rvFertilizer.adapter = guestAdapter

        binding.cardCalculator.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Wah!")
                setMessage("Kamu Harus Login Terlebih dahulu")
                setPositiveButton("Login Dulu Yuk") { _, _ ->
                    val intent = Intent(this@GuestActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                show()
            }
        }

        binding.cardEligibility.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Wah!")
                setMessage("Kamu Harus Login Terlebih dahulu")
                setPositiveButton("Login Dulu Yuk") { _, _ ->
                    val intent = Intent(this@GuestActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
                show()
            }
        }

        list.add(ImageData(R.drawable.slide1))
        list.add(ImageData(R.drawable.slide2))
        list.add(ImageData(R.drawable.slide3))

        // adapter slide
        adapterSlider = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapterSlider
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        setUpTransformer()

    }

    // memberikan margin ke slider
    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        binding.viewPager.setPageTransformer(transformer)
    }

    private fun selectedDot(position: Int) {
        val dotCount = min(list.size, 3) // Batasi dotCount menjadi maksimum 3
        for (i in 0 until dotCount) {
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.green_700))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.green_200))
        }
    }

    private fun setIndicator() {
        val dotCount = min(list.size, 3) // Batasi dotCount menjadi maksimum 3
        for (i in 0 until dotCount) {
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}