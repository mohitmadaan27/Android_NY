# NY Times Most Popular Articles
Simple app to hit the NY Times Most Popular Articles API and show a list of articles.

http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?apikey=sample-key
To use this api you need to generate SAMPLE KEY from https://developer.nytimes.com/apis

ANDROID 
Current flow of Project
1.Load data from network Api
A:-To load data from network I have used RETROFIT network library.
2.Handling data
A:-Livedata, GSON, Viewmodel
3.Architecture Pattern
A:-MVVM(Model-View-Viewmodel)
4.Image Loading
A:-Glide for displaying circular transformation
5.Language
A:- Kotlin
6.Displaying cards of data
A:- Recyclerview
7.Internet No network handling
A:- displaying snackbar
8.Testing
A:- Unit testing and Espresso Instrumentation Testing




How we can improve this more
 DetailView Activity As message is displaying only 4 lines
 Add shared element Transition Animation
 Progress bar for initial loading
 Shimmer fake view for cards message loading
 Network available check
 Alert / Error dialog
 Database – Room for offline and already loaded message
Paging library for load more data
Swipe to refres data

