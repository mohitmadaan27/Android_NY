# NY Times Most Popular Articles
Simple app to hit the NY Times Most Popular Articles API and show a list of articles.

http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?apikey=sample-key
To use this api you need to generate SAMPLE KEY from https://developer.nytimes.com/apis

**ANDROID** 
Current flow of Project
- Load data from network Api
**:-** To load data from network I have used RETROFIT network library.
- Handling data
**:-** Livedata, GSON, Viewmodel
- Architecture Pattern
**:-**MVVM(Model-View-Viewmodel)
- Image Loading
**:-**Glide for displaying circular transformation
- Language
**:-** Kotlin
- Displaying cards of data
**:-** Recyclerview
- Internet No network handling
**:-** displaying snackbar
- Testing
**:-** Unit testing and Espresso Instrumentation Testing*




How we can improve this more
* DetailView Activity As message is displaying only 4 lines
* Add shared element Transition Animation
* Progress bar for initial loading
* Shimmer fake view for cards message loading
* Network available check
* Alert / Error dialog
* Database – Room for offline and already loaded message
* Paging library for load more data
* Swipe to refres data

