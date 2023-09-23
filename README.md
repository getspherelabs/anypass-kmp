<h1 align="center"><a href="https://github.com/getspherelabs/locker-kmp">AnyPass: Password Manager & Authenticator</a></h1></br>

<p align="center">
  <a href="https://github.com/getspherelabs/anypass-kmp"><img  alt="AnyPass Banner" src="https://github.com/getspherelabs/anypass-kmp/blob/main/media/banner.jpg?raw=true"/></a> <br>
</p>


[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
![iOS](https://img.shields.io/badge/iOS-14.0%2B-green)
![Swift](https://img.shields.io/badge/Swift-5-F16D39.svg?style=flat)
![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-EAEAEA.svg?style=flat)

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
    features-->generatepassword;
    password-->generatepassword;
    biometry-->shared;
```

### Data Module:

```mermaid
graph TD;
    data-->local;
    data-->authManager;
    data-->settings;

    features-->onboarding;
    features-->auth;
    features-->addnewpassword;
    features-->home;
    features-->auth;

    settings--> features;
    local-->features;
    authManager-->features;
    onboarding-->shared;
    auth-->shared;
    home-->shared;
    addnewpassword-->shared;
```

### Feature Module:

```mermaid
graph TD;
    features-->onboarding;
    features-->auth;
    features-->addnewpassword;
    features-->home;
    features-->auth;
    features-->space;
    features-->generatepassword;
   
    onboarding-->shared;
    auth-->shared;
    home-->shared;
    addnewpassword-->shared;
    space-->shared;
    generatepassword-->shared;
```

## Project Requirements

- Java 17+
- iOS: 14.0+
