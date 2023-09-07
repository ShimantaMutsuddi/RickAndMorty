
# RickAndMorty

The Rick and Morty - App was created using clean architecture principles, the Repository Pattern, the MVVM pattern, and architecture components. 

**App features:**
- List of Rick and Morty characters
- Detail of characters
**Screenshots:**
  <table>
  <tr>
     <td>Characters Screen</td>
     <td>Character Detail Screen</td>
  </tr>
  <tr>
    <td><img src="app/screenshots/Character.jpeg" width=300 ></td>
    <td><img src="app/screenshots/Character_Detail.jpeg" width=300 ></td>
  </tr>
 </table>


## Code Flow:
```
app
│
├── data
│   ├── local
│   │   ├── dao         // Room database DAOs
│   │   └── database    // Room database setup
│   │
│   ├── remote
│   │   ├── api         // Retrofit API interfaces
│   │
│   ├── paging          // Paging3 for pagination
│   │
│   ├── model           // Network model and entities
│   │
│   ├── repository      // Repository pattern for data management
│   │
│
├── di                  // Dependency Injection setup
│
├── ui
│   ├── common          // Common UI components, extensions, etc.
│   │
│   ├── features
│   │   ├── feature1
│   │   │   ├── view    // Fragments/Activities
│   │   │   ├── viewmodel   // ViewModels
│   │   │   ├── adapter // Adapters for RecyclerViews
│   │   │
│   │   ├── feature2
│   │   └── ...         // Other feature modules
│   │
│
├── utils               // Utility classes and extensions
│
└── RickAndMorty.kt     // Application class



```
## Tech stack - Library:

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for dependency injection.
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For reactive style programming (from VM to UI). 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get lifecyle event of an activity or fragment and performs some action in response to change
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Used to create room db and store the data.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments
  - [View Binding](https://developer.android.com/topic/libraries/view-binding) - Makes it easier to write code that interacts with views.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [Retrofit](https://github.com/square/retrofit) - Used for REST api communication.
- [Timber](https://github.com/JakeWharton/timber) - Used for logging.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines .
## TODO
- [X] Paging with RemoteMediator
- [ ] Unit test
- [ ] Use Jetpack Compose
