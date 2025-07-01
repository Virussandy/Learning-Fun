package com.mollosradix.deals.repository

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.mollosradix.deals.model.DealItem
import com.mollosradix.deals.model.NotificationItem

class NotificationsRepository {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Notifications")
    private val db     = FirebaseFirestore.getInstance()

    /**
     * Emits every *new* deal as soon as it is marked “good” in Realtime DB.
     */
    fun listenForNewNotifications(onNewDeal: (DealItem) -> Unit) {
        dbRef.addChildEventListener(object : ChildEventListener {


            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val dealId = snapshot.getValue(NotificationItem::class.java)?.deal_id ?: return

                // 2️⃣ pull full deal from Firestore
                db.collection("deals").document(dealId).get()
                    .addOnSuccessListener { snap ->
                        snap.toObject(DealItem::class.java)?.let { onNewDeal(it) }
                    }
            }
            override fun onChildChanged  (s: DataSnapshot, p: String?) {}
            override fun onChildRemoved  (s: DataSnapshot) {}
            override fun onChildMoved    (s: DataSnapshot, p: String?) {}
            override fun onCancelled     (error: DatabaseError) {}
        })
    }
}


