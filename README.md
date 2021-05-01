# firebase_change_data
 #Contents
Data model
Using FirebaseUI to populate a RecyclerView
dependency 
In your app/build.gradle file add a dependency on one of the FirebaseUI libraries.

dependencies {
    // FirebaseUI for Firebase Realtime Database
    implementation 'com.firebaseui:firebase-ui-database:7.1.1'

    // FirebaseUI for Cloud Firestore
    implementation 'com.firebaseui:firebase-ui-firestore:7.1.1'

    // FirebaseUI for Firebase Auth
    implementation 'com.firebaseui:firebase-ui-auth:7.1.1'

    // FirebaseUI for Cloud Storage
    implementation 'com.firebaseui:firebase-ui-storage:7.1.1'
}
#picasso
A powerful image downloading and caching library for Android


exemple :Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
Download
Download the latest AAR from Maven Central or grab via Gradle:

implementation 'com.squareup.picasso:picasso:2.71828'
or Maven:

<dependency>
  <groupId>com.squareup.picasso</groupId>
  <artifactId>picasso</artifactId>
  <version>2.71828</version>
</dependency>