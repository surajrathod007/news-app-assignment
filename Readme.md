# News App

A modern news application built using Jetpack Compose that fetches the latest news headlines from the United States using the News API. The app allows users to mark articles as favorites, and these favorite articles are accessible offline.

## Features

-   View the latest news headlines.

-   Mark articles as favorites for quick access.

-   Offline access to favorite articles.

-   Smooth and modern UI using Jetpack Compose.


----------

## Project Setup

1.  Clone the repository:

    ```
    git clone https://github.com/surajrathod007/news-app-assignment.git
    ```

2.  Open the project in Android Studio.

3.  Build and run the project on your emulator or device.


----------

## Libraries Used

### Jetpack Lifecycle

-   **ViewModel**: Manages UI-related data in a lifecycle-conscious way.

-   **ViewModel Compose Utilities**: Integrates ViewModel with Jetpack Compose.

-   **LiveData**: Observable data holder class for UI updates.

### Dependency Injection
-   **Hilt**: Simplifies dependency injection and improves testability.


### Networking

-   **Retrofit**: Simplifies API calls and network requests.

-   **Gson**: Converts JSON data to Kotlin objects and vice versa.


### Navigation

-   **Navigation Compose**: Enables navigation within Jetpack Compose apps.


### Local Storage

-   **Room**: Provides an abstraction layer over SQLite for local data storage.


### UI

-   **Compose Material**: Provides Material Design components for Jetpack Compose.

-   **Coil**: Loads and displays images efficiently in Compose.


----------

## Architecture and Design Choices

### Architecture Pattern

-   **MVVM (Model-View-ViewModel)**:

    -   Decouples UI and business logic, making the app easier to maintain and test.

    -   The ViewModel holds the app’s state and communicates with the Repository for data operations.


### Dependency Injection

-   **Hilt**: Simplifies dependency injection, improves testability, and allows for clear separation of concerns.


### Data Handling

-   **NetworkResult**: A custom wrapper class to handle API responses generically. It provides:

    -   Success data.

    -   Loading states.

    -   Error handling.


### Offline Functionality

-   **Room Database**:

    -   Stores favorite articles locally for offline access.


### Networking

-   **Retrofit**:

    -   Fetches news data from the API efficiently.


### Image Loading

-   **Coil**: Optimized image loading library for Compose, ensuring smooth and fast image rendering.


----------

## Future Enhancements

-   Making whole app work offline
-   Giving categories for news
    

   