
# All in One - MVVM Architecture, DataBinding, Room Database, API Call and LiveData  
  
This is a very simple **All in One** using **MVVM Architecture, DataBinding, Room Database, API Call and LiveData and other important elements** in Android. It loads the data from the web service or local Database (Room) using DataBinding **"@="**, stores it in LiveData, and displays back on the UI.  
  
This example is for those who want to learn the easiest way to fetch the data from online or offline and display the data. This is useful in many ways such as Saving Development Time, Code Reusability, Validations etc.  
  
So let's get started on using these technologies together in a single application:  
  
1. What is MVVM Architecture?   
2. What is DataBinding?  
3. What is LiveData, LifeCycle and that Advantages ?  
4. What is Room Database and Coroutines?  
5. Advantages of Room over SQLite  
6. Important Annotations in Room  
7. How to call the web-service? 
8. Implementation Step-by-Step  
9. All in one - Full example   
10. Conclusion  
  
  
## 1. What is MVVM Architecture?  
  
MVVM is a design pattern for organizing GUI applications that has become popular on Android. MVVM architecture is a Model-View-ViewModel architecture that removes the tight coupling between each component. Most importantly, in this architecture, the children don't have the direct reference to the parent, they only have the reference by observables.  
  
This concept will introduce you to the main 3 components of MVVM, the View, Model, and ViewModel.  
  
**Model:**
- A Model is responsible for exposing data in a way that is easily consumable by WPF. It must implement INotifyPropertyChanged and/or INotifyCollectionChanged as appropriate.  
- What should go in your model layer and what shouldn't.  
- Benefits of model isolation and how it affects testing.  
  
**View:** 
- A View is defined in XAML and should not have any logic in the code-behind. It binds to the view-model by only using data binding.  
- How it interacts with the ViewModel.  
  
**ViewModel:** 
- A ViewModel is a model for a view in the application or we can say as abstraction of the view. It exposes data relevant to the view and exposes the behaviors for the views, usually with Commands.  
- How it supports the View, by providing actions and observable state.  
- Interactions with the Model.  
- Isolation from the View.  
  
<img src="https://i.ibb.co/qxPGGkg/mvvm.png" alt="2" />  
  
## 2. What is DataBinding?  
  
The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically and many more.  
  
<img src="https://miro.medium.com/max/1070/1*pgFREsmroqa52ITl4iBPZQ.png" alt="3" />  
  
## 3. What is LiveData, LifeCycle and Advantages?  
  
LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.  
  
Below are the advantages: You can check my previous blog Live Data here. 
<img src="https://chintankhetiya.files.wordpress.com/2021/05/1-1.png" alt="4" /> 
[Live Data here](https://chintankhetiya.wordpress.com/2021/05/22/understanding-livedata-lifecycle-in-android/)  
  
**Ensures your UI matches your data state:**  
  LiveData follows the observer pattern. LiveData notifies Observer objects when the lifecycle state changes. You can consolidate your code to update the UI in these Observer objects. Instead of updating the UI every time the app data changes, your observer can update the UI every time there's a change.  
      
**No memory leaks:**  
  Observers are bound to Lifecycle objects and clean up after themselves when their associated lifecycle is destroyed.  
      
**No crashes due to stopped activities:**  
  If the observer's lifecycle is inactive, such as in the case of an activity in the back stack, then it doesn’t receive any LiveData events.  
      
**No more manual lifecycle handling:**  
  UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.  
      
**Always up to date data:**  
  If a lifecycle becomes inactive, it receives the latest data upon becoming active again. For example, an activity that was in the background receives the latest data right after it returns to the foreground.  
      
**Proper configuration changes:**  
  If an activity or fragment is recreated due to a configuration change, like device rotation, it immediately receives the latest available data.  
      
**Sharing resources:**  
  You can extend a LiveData object using the singleton pattern to wrap system services so that they can be shared in your app. The LiveData object connects to the system service once, and then any observer that needs the resource can just watch the LiveData object. For more information, see Extend LiveData.   
      
 
  
## 4. What is Room Database and Coroutines?  
  
**Room Database:** 
Database layer on top of SQLite database that takes care of mundane tasks that you used to handle with an SQLiteOpenHelper. Database holder that serves as an access point to the underlying SQLite database. The Room database uses the DAO to issue queries to the SQLite database.  
  
**Entity:** 
When working with Architecture Components, this is an annotated class that describes a database table.  
  
**SQLite database:** 
On the device, data is stored in an SQLite database. For simplicity, additional storage options, such as a web server, are omitted. The Room persistence library creates and maintains this database for you.  
  
**DAO:** 
Data access object. A mapping of SQL queries to functions. You used to have to define these painstakingly in your SQLiteOpenHelper class. When you use a DAO, you call the methods, and Room takes care of the rest.  
  
**Repository:**
 A class that you create, for example using the WordRepository class. You use the Repository for managing multiple data sources.  
  
**Coroutines:** 
Coroutines are a great new feature of Kotlin which allow you to write asynchronous code in a sequential fashion. … However, like RxJava, coroutines have a number of little subtleties that you end up learning for yourself during development time, or tricks that you pick up from others.   
  
<img src="https://www.pikpng.com/pngl/m/487-4871288_room-architecture-android-room-database-example-clipart.png" alt="5" />  
  
  
## 5. Advantages of Room over SQLite  
  
- In case of SQLite, There is no compile time verification of raw SQLite queries. But in Room there is SQL validation at compile time.  
- As your schema changes, you need to update the affected SQL queries manually. Room solves this problem.  
- You need to use lots of boilerplate code to convert between SQL queries and Java data objects. But, Room maps our database objects to Java Object without boilerplate code.  
- Room is built to work with LiveData and RxJava for data observation, while SQLite does not.  
  
## 6. Important Annotations in Room  
  
<img src="https://i.ibb.co/tczyCVL/room.png" alt="6" />  
  
## 7. How to call the web-service?  
  
A web service is a standard for exchanging information between different types of applications irrespective of language and platform. There are SOAP and RESTful web service.  
Most of the application use the REST webservice which accept and return the JOSN data.   
  
<img src="https://androidwave.com/wp-content/uploads/2019/05/mvvm-architecture-app-in-android.png" alt="7" />  
  
## 8. Implementation Step-by-Step  
  
- Add require dependencies to your project  
  
```xml  
dependencies {
    ...
    ...
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'androidx.core:core-ktx:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    def roomVersion = '2.3.0'
    def archLifecycleVersion = '2.2.0'
    def coreTestingVersion = '2.1.0'
    def materialVersion = '1.3.0'
    def coroutines = '1.3.8' // Room components  
    implementation "androidx.room:room-runtime:$roomVersion"
    kapt "androidx.room:room-compiler:$roomVersion"
    androidTestImplementation "androidx.room:room-testing:$roomVersion"
    androidTestImplementation "androidx.arch.core:core-testing:$coreTestingVersion"
    implementation "androidx.room:room-ktx:$roomVersion"
    implementation 'org.conscrypt:conscrypt-android:2.2.1' // Kotlin components  
    api "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"
    api "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines" // Material design  
    implementation "com.google.android.material:material:$materialVersion"
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.multidex:multidex:2.0.1' /*Retrofit & GSON Parsing*/
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2' /*Shimmer Animation*/
    implementation 'com.github.sharish:ShimmerRecyclerView:v1.3'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    /*Image Loading*/
    implementation 'com.github.bumptech.glide:glide:4.11.0' /*Multidex to support 64K Function*/
    implementation 'androidx.multidex:multidex:2.0.1' /*Dexter - Runtime Permission Lib*/
    implementation 'com.karumi:dexter:6.1.2'
    kapt 'com.android.databinding:compiler:3.1.4'
    /*Viewmodel Life cycle and others*/
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    kapt "androidx.lifecycle:lifecycle-compiler:$archLifecycleVersion"
    implementation 'androidx.core:core-ktx:1.5.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-common-java8:2.3.1' /*Pull to refresh*/
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'...
        ...
}
```  
  
- Implement Data binding  
  
```xml  
 dataBinding { enabled = true }
 ``` 
 
- Create project structure  
 
<img src="https://i.ibb.co/5nQJkQg/pro-structure.png " alt="8" />  
  
- Implement the Retrofit to fetch the remote data   
  
```kotlin  
private val objRetrofit = Retrofit.Builder()
.baseUrl(APIConstant.BASE_URL)
.addConverterFactory(GsonConverterFactory.create())
.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
.client(onGetBuilder().build())
.build()
```  
  
- Implement the Room Database to fetch the local data  
  
```kotlin  
@Database(entities = arrayOf(UniversitiesEntity::class), version = 1, exportSchema = true)  
public abstract class UniversitiesDatabase : RoomDatabase() {  
  
    abstract fun UniversitiesDao(): UniversitiesDao  
  
  companion object {  
        @Volatile  
  private var INSTANCE: UniversitiesDatabase? = null  
  
 fun getDatabaseClient(context: Context): UniversitiesDatabase {  
            return INSTANCE ?: synchronized(this) {  
  val instance = Room.databaseBuilder(  
                    context.applicationContext,  
                    UniversitiesDatabase::class.java,  
                    "roomdb_universities"  
  )  
                    .build()  
                INSTANCE = instance  
  // return instance  
  instance  
  }  
  }  
 }  
}
```  
  
- Create the UI to display the data  
  
```xml  
<?xml version="1.0" encoding="utf-8"?>  
<layout xmlns:tools="http://schemas.android.com/tools">  
 <androidx.appcompat.widget.LinearLayoutCompat xmlns:android="http://schemas.android.com/apk/res/android"  
 xmlns:app="http://schemas.android.com/apk/res-auto"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  android:background="@android:color/white"  
  android:orientation="vertical"  
  tools:context=".java.MainJavaActivity"  
  tools:ignore="PrivateResource">  
  
 <include  android:id="@+id/layToolbar"  
 layout="@layout/row_toolbar" />  
 <View style="@style/ListDivider"  android:background="@drawable/shape_shadow" />  
  
 <androidx.swiperefreshlayout.widget.SwipeRefreshLayout  android:id="@+id/swipeContainer"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent">  
  
 <com.cooltechworks.views.shimmer.ShimmerRecyclerView xmlns:app="http://schemas.android.com/apk/res-auto"  
  android:id="@+id/rv_universities_list"  
  android:layout_width="match_parent"  
  android:layout_height="match_parent"  
  app:shimmer_demo_layout="@layout/row_universities_item_shimmer"  
  app:shimmer_demo_layout_manager_type="linear_vertical" />  
  
 </androidx.swiperefreshlayout.widget.SwipeRefreshLayout>  
 <include  android:id="@+id/layNetworkLost"  
 layout="@layout/activity_network_lost"  android:visibility="gone" />  
  
 </androidx.appcompat.widget.LinearLayoutCompat></layout>  
```  
  
- Map the data with UI using MVVM pattern  
  
```kotlin  
class UniversitiesViewModel(application: Application) : AndroidViewModel(application) {  
    lateinit var liveDataUniversitiesAPI: MutableLiveData<List<UniversitiesEntity>>  
    lateinit var liveDataUniversitiesRoom: LiveData<List<UniversitiesEntity>>  
    lateinit var apiUniversities: UniversitiesAPIRepository  
  
  /*Init API Object*/  
  fun initAPI() {  
        apiUniversities = UniversitiesAPIRepository.getInstance()  
        liveDataUniversitiesAPI = apiUniversities.getUniversities()  
    }  
  
    fun insertData(context: Context, name: String, country: String, code: String) {  
        UniversitiesRoomRepository.insertData(context, name, country, code)  
    }  
  
    fun getUniversitiesList(context: Context): LiveData<List<UniversitiesEntity>> {  
        liveDataUniversitiesRoom = UniversitiesRoomRepository.getUniversitiesListFromRoom(context)  
        return liveDataUniversitiesRoom  
  }  
  
}
```  
  
- Add required permission in Androidmanifest.xml  
  
```xml  
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />  
 <uses-permission android:name="android.permission.INTERNET" />  
  
 <application  android:name=".utils.AppController"  
  android:usesCleartextTraffic="true"  
  android:allowBackup="true"  
  android:networkSecurityConfig="@xml/network_security_config"  
   />  
```  
- Add required configuration properties in build.gradle (app)  
  
```kotlin  
androidExtensions { experimental = true }  
testOptions {  
 unitTests.returnDefaultValues = true }  
 android { lintOptions { checkReleaseBuilds false  abortOnError false } }  
```  
  
- Run it!  
   
         
## 9. All in one - Full example  
  
Check out my blog [ChintanKhetiya](https://chintankhetiya.wordpress.com/author/chintankhetiya/) for more details and full source code you can get from my [Github repository](https://github.com/khetiyachintan/MVVM-Architecture-AllInOne).  
  
## 10. Conclusion  
    
Hopefully, this guide should have helped you in making your tasks easier in terms of many things. This is a very simple example. It is all about the free API here I have used which returns the JSON list of Universities.   
1. *If network available* - Fetch the data from web services using Retrofit, store in Room Database, updating UI using LiveData and Data Binding.  
2. *If the network not available* - Check any local records found from Room Database, If yes then updating UI using LiveData and Data Binding.  
3. *If the network not available* - No local record found then updating UI with network lost screen  
4. You can also *sort* the list data with *Sort by university name* and *sort by country* parameters  
5. During the loading you can see the *Shimmer animation* also  
6. To get updated records from the API - Just do *Swipe to refresh*. In addition, If you lost the network you can fetch the records by tap on Retry button.  
  
There all are basic things which gives you the practical idea and you can compare with your application requirement.
        
Hope this helps you!   
  
Feel free to reach me at any time on 
[LinkedIn](https://in.linkedin.com/in/chintankhetiya) 
[Github](https://github.com/khetiyachintan)
[ChintanKhetiya -Blog](https://chintankhetiya.wordpress.com/author/chintankhetiya/)
[Instagram AASE-ChintanKhetiya](https://instagram.com/aase_chintankhetiya)  
  
Thank you!   
Happy Coding :)
