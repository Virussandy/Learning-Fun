package com.mollosradix.deals.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.mollosradix.deals.model.DealItem

@Composable
fun DealDetailScreen(dealId: String) {
    var deal by remember { mutableStateOf<DealItem?>(null) }

    LaunchedEffect(dealId) {
        FirebaseFirestore.getInstance()
            .collection("deals")
            .document(dealId)
            .get()
            .addOnSuccessListener {
                deal = it.toObject(DealItem::class.java)
            }
    }

    deal?.let {
        // Show deal UI here
        Column(modifier = Modifier.padding(16.dp)) {
            Text(it.title, fontWeight = FontWeight.Bold)
            AsyncImage(model = it.image, contentDescription = null)
            Text("Price: ₹${it.price}")
            Text("Store: ${it.store}")
            // Add more fields as you want
        }
    } ?: run {
        Text("Loading...", modifier = Modifier.padding(16.dp))
    }
}
