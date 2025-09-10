# ✅ QuoteFlow - An Inspirational Quote Application
### A comprehensive Android project built with a modern, database-driven architecture.

QuoteFlow is a modern, inspirational quote application for Android, built entirely with Kotlin and Jetpack Compose. It provides a clean, intuitive user interface for viewing, saving, and sharing your favorite quotes. The app is designed with a robust MVVM architecture using a Repository pattern and the Room database, ensuring a scalable and maintainable codebase.

---
---
## 📸 Screenshots

| Home Screen | Add Quote Dialog | Favorites (Empty) | Favorites (With Quotes) |
| :---: |:---:|:---:|:---:|
| ![1 Home Screen](https://github.com/user-attachments/assets/bb182402-409d-4dcb-a1e0-dbdf60432b27) | ![2 Add a Quote](https://github.com/user-attachments/assets/166cfa57-b0ce-49f5-9ddc-45a1797d9e0d) | ![3 Empty Favourites](https://github.com/user-attachments/assets/f5db3e86-f0e8-405c-ab05-755ca4a6809b) | ![4 Favourites](https://github.com/user-attachments/assets/3e491f34-353f-487e-8ddf-d16f9f980592) |

---

## 🚀 Features

| Feature | Description |
|---|---|
| 💾 **Persistent Favorites** | Save your favorite quotes to a local **Room database**. Your data is never lost, even when you close the app or restart your device. |
| ✨ **Modern UI/UX** | Built entirely with **Jetpack Compose & Material 3**. Features a clean, dark-themed user interface that is both beautiful and easy to use. |
| ➕ **Add Your Own Quotes** | Use the simple "Add a Quote" dialog to contribute your own favorite quotes to your personal collection. |
| 🔄 **Discover New Quotes** | Get a new random "Quote of the Day" with a single tap of the **Refresh** button, pulling from both the initial and user-added quotes. |
| 📤 **Share with Friends** | Easily share any quote with friends or on social media using Android's native share sheet functionality. |
| 🏗️ **Robust Architecture** | Built on a modern **MVVM** foundation with a `Repository` pattern and a reactive data flow using Kotlin `StateFlow`. |

---

## 🔧 Installation

1.  Clone the repository:
    ```bash
    git clone https://github.com/therahuls916/QuoteFlow.git
    ```
2.  Navigate to the project directory:
    ```bash
    cd QuoteFlow
    ```
3.  Open the project in the latest stable version of Android Studio, let Gradle sync, and click ▶️ **Run**.

## 🛠 Tech Stack

-   **Tech:** Kotlin, Coroutines, Flow
-   **Architecture:** MVVM (Model-View-ViewModel), Repository Pattern
-   **UI:** Jetpack Compose, Material 3
-   **Dependency Injection:** Manual DI via Application Class & ViewModelFactory
-   **Data Persistence:**
    -   Room: For creating and managing the local SQLite database for quotes.
-   **Navigation:**
    -   Jetpack Compose Navigation

---
---
## 📂 Folder Structure
```plaintext
app/src/main/java/com/rahul/auric/quoteflow/
├── data/
│   ├── db/
│   │   ├── QuoteDao.kt          # Data Access Object for database queries
│   │   └── QuoteDatabase.kt     # Room database definition
│   └── Quote.kt               # Room @Entity for the quotes table
├── navigation/
│   ├── AppNavigation.kt       # Composable that hosts the NavHost and defines routes
│   └── Screen.kt              # Sealed class for type-safe navigation routes
├── repositories/
│   └── QuoteRepository.kt     # Repository to abstract the data source
├── screens/
│   ├── FavoritesScreen.kt     # UI for the list of favorite quotes
│   └── HomeScreen.kt          # Main UI screen with the Quote of the Day
├── ui/
│   ├── composables/
│   │   ├── AddQuoteDialog.kt    # Composable for the Add/Edit dialog
│   │   ├── FavoriteQuoteCard.kt # Composable for a single card in the favorites list
│   │   └── QuoteCard.kt         # Composable for the main quote card
│   └── theme/
│       ├── Color.kt
│       ├── Shapes.kt
│       └── Theme.kt
│       └── Typography.kt
├── utils/
│   └── ShareUtils.kt          # Helper function for the share intent
├── viewmodels/
│   ├── QuoteViewModel.kt      # ViewModel to hold state and business logic
│   └── ViewModelFactory.kt    # Factory to create the ViewModel with its repository dependency
├── QuoteFlowApp.kt            # Application class to initialize the database and repository
└── MainActivity.kt            # Main entry point for the application
```
---
## 🔐 Permissions Used

This application currently requires **no special permissions**, providing a secure and privacy-focused user experience.

---

## 🧠 How It Works

-   **UI Layer:** The entire UI is built with Jetpack Compose. `AppNavigation` manages two main screens: `HomeScreen` and `FavoritesScreen`. The screens observe data from the `QuoteViewModel` using `collectAsState()` and are automatically updated when the data changes.
-   **State Management:** The `QuoteViewModel` holds the application state (the current "Quote of the Day," the list of favorites) using `StateFlow`. It gets its data from the repository and contains all business logic, such as toggling a favorite or getting a new random quote.
-   **Data Layer:** The `QuoteRepository` acts as a single source of truth for all quote data. It provides a clean API for the ViewModel and abstracts away the fact that the data is coming from a Room database.
-   **Database:** Room is used to persist all quotes. The `Quote` data class is the table entity, and the `isFavorite` boolean flag within it is used to manage the user's favorites list. The `QuoteDao` interface defines all database operations and returns reactive `Flows`, which automatically push updates to the UI when data changes.
-   **Dependency Injection:** A manual dependency injection pattern is used. The `QuoteFlowApp` class creates a singleton instance of the `QuoteRepository`. This repository is then passed to the `QuoteViewModelFactory`, which creates the `QuoteViewModel` and injects the repository into it.

---

## ✅ Planned Features

-   [ ] ⚙️ **Improve Bottom Navigation State:** Correctly highlight the active tab ("Home" or "Favorites") in the bottom navigation bar.
-   [ ] ✨ **UI Animations:** Add animations for adding/removing quotes in the favorites list using `animateItemPlacement`.
-   [ ] ⚠️ **Confirmation Dialog:** Show an "Are you sure?" dialog before a user unfavorites a quote to prevent accidental removal.
-   [ ] 📋 **Copy to Clipboard:** Add a "Copy" icon to each quote card to allow users to easily copy the quote text.
-   [ ] 🎨 **Theme Switching:** Add a settings option to manually switch between Light and Dark themes.

---

## 🤝 Contributing

Contributions are welcome! If you'd like to help, please fork the repository, create a new branch for your feature or fix, and submit a pull request.

---

## 📄 License

This project is licensed under the MIT License - see the `LICENSE` file for details.

---

## 👨‍💻 Developer

**Rahul Salunke**  
[GitHub](https://github.com/therahuls916)  
[LinkedIn](https://www.linkedin.com/in/rahulasalunke/)