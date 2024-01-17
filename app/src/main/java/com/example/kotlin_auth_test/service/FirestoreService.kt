import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {
    private val firestore = FirebaseFirestore.getInstance()

    fun getUserProfile(uid: String) = firestore.collection("users").document(uid)
}
