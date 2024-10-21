package com.anoop.mvvmd

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anoop.mvvmd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = QuoteDatabase.getDataBase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel :: class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       mainViewModel.getQuotes().observe(this, {
           binding.quotes = it.toString()
       })
        binding.btnAdd.setOnClickListener{
            val quote = Quote(0, "This is quote", "testing")
            mainViewModel.insertQuote(quote)
        }

    }
}