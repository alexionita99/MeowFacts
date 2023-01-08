This is the project for the Mobile Computing class.

The chosen public API generates facts about cats and is available at the following link:
https://meowfacts.herokuapp.com/

The app has the following components:
- A fragment + view model
- A RecyclerView list view
- A network component, using Retrofit
- A database, using Room

Once opened, 5 cat facts are retrieved from the API. After that, a user can input a desired number of facts and press the "Get Facts!" button. The new facts are retrieved, saved in the database and shown on the screen.

Because the app uses a database, it can also be accessed with no internet connection. In this case, the latest retrieved facts will be displayed. 

I worked on this app step by step, with support from the recorded labs and the provided Android Codelabs. 