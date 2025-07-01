package com.mollosradix.deals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.mollosradix.deals.model.DealItem
import com.mollosradix.deals.repository.DealsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit

class DealsViewModel : ViewModel() {
    private val repository = DealsRepository()

    private val _deals = MutableStateFlow<List<DealItem>>(emptyList())
    val deals: StateFlow<List<DealItem>> = _deals.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _hasMoreItems = MutableStateFlow(true)
    val hasMoreItems: StateFlow<Boolean> = _hasMoreItems.asStateFlow()

    private var lastDocument: QueryDocumentSnapshot? = null

    init {
        loadInitialDeals()
    }

    private fun loadInitialDeals() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                lastDocument = null
                val (newDeals, lastDoc) = repository.getDeals()
                _deals.value = newDeals
                lastDocument = lastDoc
                _hasMoreItems.value = newDeals.isNotEmpty() && lastDoc != null
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun reload() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Reset everything
                lastDocument = null
                _deals.value = emptyList()
                _hasMoreItems.value = true

                // Load fresh data
                val (newDeals, lastDoc) = repository.getDeals()
                _deals.value = newDeals
                lastDocument = lastDoc
                _hasMoreItems.value = newDeals.isNotEmpty() && lastDoc != null
            } catch (e: Exception) {
                // Handle error (optional logging)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreDeals() {
        if (_isLoading.value || !_hasMoreItems.value) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val (newDeals, lastDoc) = repository.getDeals(lastDocument)

                if (newDeals.isNotEmpty()) {
                    _deals.value = _deals.value + newDeals
                    lastDocument = lastDoc
                    _hasMoreItems.value = lastDoc != null
                } else {
                    _hasMoreItems.value = false
                }
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun getTimeAgo(timestamp: String?): String {
        if (timestamp == null) return ""
        
        return try {
            val instant = Instant.parse(timestamp)
            val now = Instant.now()
            val diff = ChronoUnit.MINUTES.between(instant, now)

            when {
                diff < 60 -> "$diff minutes ago"
                diff < 1440 -> "${diff / 60} hours ago"
                else -> "${diff / 1440} days ago"
            }
        } catch (e: Exception) {
            ""
        }
    }
} 