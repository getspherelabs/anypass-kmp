package io.spherelabs.navigation


/**
 * Navigation animations for transitioning between navigation states.
 *
 * - [NO_ANIMATION]: Indicates no animation is applied during navigation transitions.
 * - [NAVIGATING]: Represents an animation for navigating forward within the navigation flow.
 * - [POPPING_UP]: Represents an animation for navigating backward or popping a state from the navigation stack.
 * - [EXIT]: Represents an animation for exiting the navigation flow, typically used to close the app.
 */
enum class NavigationAnimation {
    NO_ANIMATION,
    NAVIGATING,
    POPPING_UP,
    EXIT
}