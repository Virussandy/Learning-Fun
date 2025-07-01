package com.mollosradix.deals.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.mollosradix.deals.model.DealItem
import kotlinx.coroutines.tasks.await

class DealsRepository {
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val PAGE_SIZE = 18
    private val INTERNAL_BATCH_SIZE = 50

    suspend fun getDeals(lastDocument: QueryDocumentSnapshot? = null): Pair<List<DealItem>, QueryDocumentSnapshot?> {
        var query = db.collection("deals")
            .orderBy("posted_on", Query.Direction.DESCENDING)
            .limit(PAGE_SIZE.toLong())

        if (lastDocument != null) {
            query = query.startAfter(lastDocument)
        }

        val snapshot = query.get().await()
        val lastVisible = if (snapshot.documents.isNotEmpty()) {
            snapshot.documents.last() as QueryDocumentSnapshot
        } else null
        val dealItems = snapshot.documents.mapNotNull { it.toObject(DealItem::class.java) }
        
        return Pair(dealItems, lastVisible)
    }

    suspend fun searchDeals(
        searchText: String,
        startAfterDoc: QueryDocumentSnapshot? = null
    ): Pair<List<DealItem>, QueryDocumentSnapshot?> {

        var lastDocument = startAfterDoc
        val filteredDealItems = mutableListOf<DealItem>()

        while (filteredDealItems.size < PAGE_SIZE) {
            // Build query
            var query = db.collection("deals")
                .orderBy("posted_on", Query.Direction.DESCENDING)
                .limit(INTERNAL_BATCH_SIZE.toLong())

            if (lastDocument != null) {
                query = query.startAfter(lastDocument)
            }

            val snapshot = query.get().await()
            val documents = snapshot.documents

            if (documents.isEmpty()) {
                // No more data to fetch
                break
            }

            // Filter this batch
            val batchDealItems = documents.mapNotNull { it.toObject(DealItem::class.java) }
                .filter {
                    it.title?.contains(searchText.trim(), ignoreCase = true) == true ||
                            it.store?.contains(searchText.trim(), ignoreCase = true) == true
                }

            filteredDealItems.addAll(batchDealItems)

            // Prepare for next batch
            lastDocument = documents.last() as QueryDocumentSnapshot
        }

        return Pair(filteredDealItems.take(PAGE_SIZE), lastDocument)
    }
} 