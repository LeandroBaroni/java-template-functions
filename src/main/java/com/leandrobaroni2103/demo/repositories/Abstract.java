package com.leandrobaroni2103.demo.repositories;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.leandrobaroni2103.demo.models.BaseModel;

@Service
public abstract class Abstract<T extends BaseModel> {
	Firestore dbFirestore;
	String collectionName;
	
	boolean controlTimestamp = true;
	boolean hasTimestamp = true;
	
	public Abstract(
		final String collection,
		boolean controlTimestamp,
		boolean hasTimestamp
	) {
		this.collectionName = collection;
		this.controlTimestamp = controlTimestamp;
		this.hasTimestamp = hasTimestamp;
	}
	
	public String add(T object) throws InterruptedException, ExecutionException {
	    if (controlTimestamp) {
	        object.setCreatedAt(new Date());
	    }

	    Firestore dbFirestore = FirestoreClient.getFirestore();
	    CollectionReference collectionRef = dbFirestore.collection(collectionName);
	    DocumentReference documentRef = collectionRef.document();

	    ApiFuture<WriteResult> writeResult = documentRef.set(object);

	    if (writeResult != null) {
		    // Return the document ID
		    return documentRef.getId();
		}

	    return null;
	}
}
