# Project Title

Android Proficiency Exercise app displays facts about Canada.

It is a single Activity app that contains a Fragment. The Fragment has SwipeRefreshLayout that allows users to refresh the ListView containing inside the SwipeRefreshLayout. Items in the ListView has a title, a description and a picture about certain facts, which are organized using LinearLayout for simplicity. The ListView uses ArrayAdapter with ViewHolder pattern for performance.

#### Highlights
* The app informs the user when network is unavailable.
* The app informs the user when server is unavailable.
* The app uses ViewModel pattern for state management during screen rotation.
* The app uses androidx.
* The Activity tuned the Fragment management for performance.

## Getting Started

### Running the app

```
Install Git.
Install Android Studio and open this project.
Create an emulator and run the app.
```

### Running the tests

```
Right click on the unit test package and click run. It runs all the unit tests.
```

### Deployment

```
Enabled USB debugging on an android phone, plug it in, and run the app.
```

## Built With

* [Picasso](https://square.github.io/picasso/) - Used to download image and cache
* [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client used to fetch server data
* [Robolectric](http://robolectric.org/) - Used to write unit test


## Authors

* **Raju At** - *Initial work* - [rajuat](https://github.com/rajuat/)

## Improvements

* The description and image can be club in TextView instead of having a separate TextView and ImageView respectively.
* The targetSdkVersion 14 was not supported with the current Google Play service. However, the minSdkVersion is configured as 14. API level 14 corresponds to Android 4.0.

## Acknowledgments

This project was develop as an assignment.