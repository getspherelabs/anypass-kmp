<p align="center">
  <a href="https://github.com/getspherelabs/anypass-kmp"><img  alt="AnyPass Banner" src="https://github.com/getspherelabs/anypass-kmp/blob/main/media/rounded-banner.png?raw=true"/></a>
</p>

<p align="center">
<a href="">
    <img src="https://img.shields.io/badge/Kotlin-1.9.0-blue.svg?style=flat&logo=kotlin">
</a>
<a href="">
    <img src="https://img.shields.io/badge/iOS-14.0%2B-green">
</a>    
<a href="">
    <img src="https://img.shields.io/badge/Swift-5-F16D39.svg?style=flat">
</a>   
<a href="">
    <img src="http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat">
</a> 
</a> 
<a href="">
    <img src="http://img.shields.io/badge/platform-ios-EAEAEA.svg?style=flat">
</a>  
</p>

# AnyPass (Work In Progress ⛏)

[AnyPass]() is your one-stop solution for password management, password generation, and password health analysis.

# Architecture
The app architecture has three layers: a data layer, a domain layer and a UI layer. AnyPass uses Meteor KMP to create application using MVI architecture. It provides a unidirectional data flow (UDF), allowing you to handle state changes and propagate them to the UI efficiently.

### Single source of truth

When a new data type is defined in your app, you should assign a Single Source of Truth (SSOT) to it. The SSOT is the owner of that data, and only the SSOT can modify or mutate it. To achieve this, the SSOT exposes the data using an immutable type, and to modify the data, the SSOT exposes functions or receive events that other types can call.

This pattern brings multiple benefits:

- It centralizes all the changes to a particular type of data in one place.
- It protects the data so that other types cannot tamper with it.
- It makes changes to the data more traceable. Thus, bugs are easier to spot.

In an offline-first application, the source of truth for application data is typically a database. In some other cases, the source of truth can be a ViewModel or even the UI.
# Technology

- [Kotlin]()
- [Compose Multiplatform]()
- [Kotlin Coroutine]()
- [Swift]()
- [SqlDelight]()
- [Koin]()
- [Meteor]()
- [Firebase]()
- [Sentry]()
- [Google Admob]()
- [Multiplatform Settings]()
- [Moko Resource]()
- [Sentry Multiplatform]()
- [BuildKonfig]()
- [Coil]()
- [Meteor ViewModel]()
- [Turbine]()
- [Ktlint]()
- [Spotless]()
- [Jacoco]()

# Modularization
Modularization is the practice of breaking the concept of a monolithic, one-module codebase into loosely coupled, self contained modules.

A barebone module is simply a directory with a Gradle build script inside. Usually though, a module will consist of one or more source sets and possibly a collection of resources or assets. Modules can be built and tested independently. Due to Gradle's flexibility there are few constraints as to how you can organize your project. In general, you should strive for low coupling and high cohesion.

- **Low coupling** - Modules should be as independent as possible from one another, so that changes to one module have zero or minimal impact on other modules. They should not possess knowledge of the inner workings of other modules.
- **High cohesion** - A module should comprise a collection of code that acts as a system. It should have clearly defined responsibilities and stay within boundaries of certain domain knowledge.

### Core Module:
```mermaid
graph TD;
    core-->common;
    core-->designsystem;
    core-->analytics;
    designsystem-->shared;
    common-->shared;
    analytics-->shared;
```

### Manager Module:

```mermaid
graph TD;
    manager-->password;
    manager-->biometry;

    password-->features;
    biometry-->features;

    features-->generatepassword;
    features-->account;
```

### Data Module:

```mermaid
graph TD;
    data-->local;
    data-->authManager;
    data-->prefs;

    features-->onboarding;
    features-->auth;
    features-->addnewpassword;
    features-->home;

    prefs--> features;
    local-->features;
    authManager-->features;
```

### Feature Module:

```mermaid
graph TD;
    features-->onboarding;
    features-->auth;
    features-->addnewpassword;
    features-->home;
    features-->auth;
    features-->account;
    features-->generatepassword;
   
    onboarding-->shared;
    auth-->shared;
    home-->shared;
    addnewpassword-->shared;
    account-->shared;
    generatepassword-->shared;
```

# Setup

1. Check your **Xcode** version is 14.1 or newer. Additionally, check your **Android Studio** version is Flamingo or newer. 

2. Clone the repository using the following command:

    ```
    https://github.com/getspherelabs/anypass-kmp.git
    ```

3. When building the iOS app, you may face common issues:

    - **Issue**: ```Unicode Normalization not appropriate for ASCII-8BIT```
    - **Solution**: Try add your `~/.zprofile`, `~/.zshrc` files:
      
      ```
       LANG=en_US.UTF-8
       LANGUAGE=en_US.UFT-8
       LC_ALL=en_US.UFT-8
      ```
    - **Issue**: Sometimes you can not build your iOS app from Xcode 
    - **Solution**: Follow these steps after executing the cleanup script:
      
      1. `pod deintegrate`
      2. `pod init`
      3. Copy the `pod` modules
      4. `pod install`

4. Build and run the app on your **Android / iOS** device or simulator.

> :warning: Make fake `google-services`.json for building properly.

5. We recommend including linkers from Xcode. To include linkers from Xcode, follow these steps:
   
    1. Navigate to **iosApp/Build Settings/Linking**.
    2. Add the following linkers to your **Other Linking Flags**:
        - `lsqlite3`
        - `ObjC`
       
## Screenshots

## Contributers 

**Want to contribute?** See **[CONTRIBUTING.md](/CONTRIBUTING.md)** [![Fork AnyPass](https://img.shields.io/github/forks/getspherelabs/anypass-kmp?logo=github&style=social)](https://github.com/getspherelabs/anypass-kmp/fork)

### Those are the list of contributors of this project, thanks for your contribution 🧑‍💻:**

<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/oybekjon94">
          <img src="https://avatars.githubusercontent.com/u/91370134?v=4" width="100" alt="John Doe">
        </a>
        <br>
        <a href="https://github.com/oybekjon94">Oybek Kholiqov</a>
      </td>
      <td align="center">
        <a href="https://github.com/khurshiddev">
          <img src="https://avatars.githubusercontent.com/u/92572647?v=4" width="100" alt="Jane Smith">
        </a>
        <br>
        <a href="https://github.com/khurshiddev">Khurshid Usarov</a>
      </td>
        <td align="center">
        <a href=" https://github.com/sardor-m">
          <img src="https://avatars.githubusercontent.com/u/65296404?v=4" width="100" alt="Jane Smith">
        </a>
        <br>
        <a href="https://github.com/sardor-m">Sardor-M</a>
      </td>
      <!-- Add more contributors in a similar format -->
    </tr>
  </tbody>
</table>


## Project Requirements

- Java 17+
- iOS: 14.0+
