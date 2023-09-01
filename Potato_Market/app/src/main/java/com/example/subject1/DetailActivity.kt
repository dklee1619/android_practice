package com.example.subject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.subject1.databinding.ActivityDetailBinding
import com.example.subject1.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val getItem = intent.getParcelableExtra<MyItem>("mainItem")
        if(getItem != null) {
            binding.item.setImageResource(getItem.aIcon)
            binding.address.setText(getItem.address)
            binding.seller.setText(getItem.seller)
            binding.productName2.setText(getItem.productName)
            binding.productDescription2.setText(getItem.productDescription)
            binding.price2.setText(String.format("%,d 원", getItem.price))
        }
        binding.backbutton.setOnClickListener {
            finish()
        }
    }

}