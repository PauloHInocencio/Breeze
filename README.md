# Breeze
This is a sample Android app that implements the MVI architecture design pattern. The app consumes data from the [Unsplash](https://unsplash.com/documentation#getting-started) API.

### Preview

| <video width="240" src="https://github.com/PauloHInocencio/Breeze/assets/7839629/5db44515-3782-4967-9c09-e42a827c36fd" /> |
|---------------------------------------------------------------------------------------------------------------------------|


<br/>

### Technologies

*   Infinity scroll with caching, using **[Room](https://developer.android.com/training/data-storage/room)** database. 
*   User Interface built with **[Jetpack Compose](https://developer.android.com/jetpack/compose)**.
*   A single-activity architecture, using **[Type-Safe Navigation Compose](https://developer.android.com/jetpack/androidx/releases/navigation#2.8.0-alpha08)**.
*   Shared Element Transitions, using the **[SharedTransitionLayout](https://developer.android.com/develop/ui/compose/animation/shared-elements)** APIs.
*   A presentation layer that contains a Compose screen (View) and a **ViewModel** per screen (or feature).
*   The networking layer that uses **[Retrofit](https://square.github.io/retrofit/)**
*   Reactive UIs using **[Flow](https://developer.android.com/kotlin/flow)** and **[coroutines](https://kotlinlang.org/docs/coroutines-overview.html)** for asynchronous operations.
*   Dependency injection using [Hilt](https://developer.android.com/training/dependency-injection/hilt-android).


<br/>


### Screens

- :white_check_mark: Random list of photos screen
- :white_large_square: Detail screen with
  - :white_check_mark: **Pinch** to zoom
  - :white_check_mark: **Double tap** to zoom in/out
  - :white_check_mark: **Drag** to pan (while zoomed)
  - :white_large_square: **Scale and Translation** animations
  - :white_large_square: total of likes info.
  - :white_large_square: photo's description and author
- :white_large_square: Search photos screen
- :white_large_square: Liked photos screen

### Features

- :white_large_square: Like a photo
- :white_large_square: Download a photo

### Tasks

- :white_large_square: Cover `ViewModels` with unit tests
- :white_large_square: Cover screens with UI testing


### Setup

Using `local.properties` to define API key:

```properties
API_ACCESS_KEY="{your-api-access-key}"
```
