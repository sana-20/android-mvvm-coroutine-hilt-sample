# android-mvvm-rxjava2-koin-sample



## Demo video
![Alt Text](https://media.giphy.com/media/vjNDBFXdVv97QWZDcl/giphy.gif)             ![Alt Text](https://media.giphy.com/media/mmm7RWjX1w5COo4OpQ/giphy.gif)




## Prerequiste
1. Get your own API key here.
https://developers.themoviedb.org/3/getting-started/introduction

2. Add your API key in the Constants.kt file.



## Technical Stacks
- The entire code is based in [Kotlin](https://kotlinlang.org/)
- Use Android Jetpack Libraries
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding/start) Declaratively bind observable data to UI elements.
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) Create a UI that automatically responds to lifecycle events.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) Build data objects that notify views when the underlying database changes.  
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) Store UI-related data that isn't destroyed on app rotations.
  - [Paging](https://developer.android.com/jetpack/androidx/releases/paging) Makes it easier for you to load data gradually and gracefully within your app's RecyclerView.

- Follow [MVVM Architecture](https://developer.android.com/jetpack/guide)
  - View - DataBinding - ViewModel - Model
  - [Koin](https://insert-koin.io/) for dependency injection

- Reactive Programming
  - [RxJava2](https://github.com/ReactiveX/RxJava) Compose asynchronous and event-based programs by using observable sequences.
  - [RxAnndroid](https://github.com/ReactiveX/RxAndroid) Reactive Extensions for Android. It makes it easy writing reactive components in Android 
  
- Build a Restful API
  - [Retrofit2](https://square.github.io/retrofit/) A type-safe HTTP client for Android and Java
  - [Gson](https://github.com/google/gson) Convert Java Objects into JSON representation
  - [OkHttp3](https://square.github.io/okhttp/) for HTTP requests
  
- Use [Glide](https://github.com/bumptech/glide) for loading images




## License
[MIT](https://choosealicense.com/licenses/mit/)
