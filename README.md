# Android Clean Architecture with MVI

This repository demonstrates an Android application using **Clean Architecture** and the **Model-View-Intent (MVI)** pattern. The project is designed with scalability, maintainability, and testability in mind, making it a great foundation for any Android application.

## Features

- **MVI Architecture**: A reactive architectural pattern that manages UI state effectively.
- **Clean Architecture**: Separation of concerns between different layers (Presentation, Domain, and Data).
- **Jetpack Compose**: A modern UI toolkit for building native Android interfaces with ease.
- **ExoPlayer Integration**: Video streaming with offline playback, and **prefetching** capabilities.
- **Dependency Injection**: Managed using **Hilt** for easy scalability and testing.
- **Version Catalog**: Centralized dependency management using Gradle's version catalog for better consistency and upgradeability.

## Architecture Overview

This project follows **Clean Architecture** principles with **MVI** for state management, allowing clear separation between UI and business logic.

- **Presentation Layer**: Handles the UI with **Jetpack Compose** and interacts with ViewModels.
- **Domain Layer**: Contains business logic and use cases.
- **Data Layer**: Responsible for network and local data sources.
