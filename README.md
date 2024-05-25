# Breeze
This is a sample Android app that implements the MVI architecture design pattern. The app consumes data from the [Unsplash](https://unsplash.com/documentation#getting-started) API.

### Screenshots


| <img width="240" src="./screenshots/breeze.breeze.mp4" /> |
|-----------------------------------------------------------|

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


## Screens

- [x] Random list of photos screen
- [ ] Detail screen with
  - [x] **Pinch** to zoom
  - [x] **Double tap** to zoom in/out
  - [x] **Drag** to pan (while zoomed)
  - [ ] **Scale and Translation** animations
  - [ ] total of likes info.
  - [ ] photo's description and author
- [ ] Search photos screen
- [ ] Liked photos screen

## Features

- [ ] Like a photo
- [ ] Download a photo

## Tasks

- [ ] Cover `ViewModels` with unit tests
- [ ] Cover screens with UI testing


## Setup

Using `local.properties` for define api key:

```properties
API_ACCESS_KEY="{your-api-access-key}"
```