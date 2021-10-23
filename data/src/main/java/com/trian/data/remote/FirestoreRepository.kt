package com.trian.data.remote

import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentSnapshot




class FirestoreRepository<TEntity : Identifiable<String>>(
    private var collectionName: String,
    private var entityClass: Class<TEntity>
) :BaseRepository<TEntity,String> {

    private  var collectionReference: CollectionReference

    init {
        val firebaseFirestore = FirebaseFirestore.getInstance()
        this.collectionReference = firebaseFirestore.collection(this.collectionName)
    }

    override fun get(id: String): Task<TEntity> {
        val doc = collectionReference.document(id)

        return doc.get().continueWith { task ->
            val snapshot = task.result
            snapshot?.let {
                if (it.exists()) {
                    it.toObject(entityClass)
                } else {
                    entityClass.newInstance()
                }
            } ?: entityClass.newInstance()
        }
    }
    override fun exists(id: String): Task<Boolean> {
        val doc = collectionReference.document(id)

        return doc.get().continueWith { task->
            task.result?.exists()
        }
    }

    override fun create(entity: TEntity): Task<Void> {
       val docName = entity.getEntityKey()
        val doc = collectionReference.document(docName)
        return doc.set(entity).addOnFailureListener {

        }
    }

    override fun update(entity: TEntity): Task<Void> {
        val docName = entity.getEntityKey()
        val doc = collectionReference.document(docName)
        return doc.set(entity).addOnFailureListener {

        }
    }

    override fun delete(id: String): Task<Void> {
       val doc = collectionReference.document(id)
        return doc.delete().addOnFailureListener {

        }
    }


}
