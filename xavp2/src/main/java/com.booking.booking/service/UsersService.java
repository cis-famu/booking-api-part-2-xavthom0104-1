package com.booking.booking.service;

import com.booking.booking.model.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.booking.booking.model.Users;
import com.booking.booking.model.paymentInformation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
@Service
public class UsersService {
    private Firestore firestore;

    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();

    }

    private Users documentSnapshotToUsers(DocumentSnapshot document)
    {
        Users user = null;
        if(document.exists())
            user = new Users(document.getId(), document.getString("name"), document.getString("email"), document.getString("phone"), document.getString("address"),(paymentInformation) document.get("pay"),document.getTimestamp("createdAt"));
        return user;
    }
    public ArrayList<Users> getAllUsers()throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        ArrayList<Users> userssList = new ArrayList<>();

        for(DocumentSnapshot document: future.get().getDocuments()){
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                userssList.add(users);
        }
        return userssList;

    }

    public Users getUsersById(String userID) throws ExecutionException, InterruptedException {
        CollectionReference userssCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userssCollection.document(userID).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToUsers(document);
    }
    public ArrayList<Users> getUserssBycreatedAtAndSort(String createdAt) throws ExecutionException, InterruptedException {
        ArrayList<Users> userss = null;

        DocumentReference usersRef = firestore.collection("Users").document(createdAt);

        CollectionReference usersCollection = firestore.collection("Users");
        Query query = usersCollection.whereEqualTo("createdAt", usersRef)
                .orderBy("createdAt", Query.Direction.DESCENDING);

        ApiFuture<QuerySnapshot> future = query.get();

        for(QueryDocumentSnapshot document :  future.get().getDocuments())
        {
            Users users = documentSnapshotToUsers(document);
            if(users != null)
                userss.add(users);

        }
        return userss;
    }
    public String createUsers(Users users) throws ExecutionException, InterruptedException {
        String usersId = null;
        ApiFuture<DocumentReference> future = firestore.collection("Users").add(users);
        DocumentReference postRef = future.get();
        usersId = postRef.getId();
        return usersId;

    }
    public void updateUsers(String id, Map<String, String> updatedValues){
        String[] allowed = {"name", "email", "address"};
        List<String> list = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, String> entry : updatedValues.entrySet())
        {
            String key = entry.getKey();
            if(list.contains(key))
            {
                formattedValues.put(key, entry.getValue());
            }
        }
        DocumentReference usersDoc = firestore.collection("Users").document(id);
        if(usersDoc != null)
            usersDoc.update(formattedValues);
    }
    public void deleteUser(String roomID) throws ExecutionException, InterruptedException {
        CollectionReference userssCollection = firestore.collection("Users");
        ApiFuture<DocumentSnapshot> future = userssCollection.document(roomID).get();
        DocumentSnapshot document = future.get();
        if(document.getId().equals(roomID)){
            userssCollection.document(roomID).delete();
        }
    }
}

