package com.anoop.mvvmd

import androidx.lifecycle.LiveData

class QuoteRepository (private val quoteDao: QuoteDao){

    fun getQuotes() : LiveData<List<Quote>> {
        return quoteDao.getQuotes()
    }

    suspend fun insertQuotes (quote: Quote) {
    quoteDao.insertQuotes(quote)
    }

    }