package io.spherelabs.homeimpl.presentation

import io.spherelabs.meteor.configs.Change
import io.spherelabs.meteor.extension.effect
import io.spherelabs.meteor.extension.expect
import io.spherelabs.meteor.extension.route
import io.spherelabs.meteor.extension.unexpected
import io.spherelabs.meteor.reducer.Reducer

class HomeReducer : Reducer<HomeState, HomeWish, HomeEffect> {

    override fun reduce(
        currentState: HomeState,
        currentWish: HomeWish,
    ): Change<HomeState, HomeEffect> {
        return when (currentWish) {
            is HomeWish.GetCategories -> {
                expect {
                    currentState.copy(
                        categories = currentWish.categories,
                        isVisible = currentWish.categories.isNotEmpty(),
                    )
                }
            }
            is HomeWish.GetPasswordByCategory -> {
                expect { currentState.copy(passwords = currentWish.passwords) }
            }
            is HomeWish.OnPasswordsChanged -> {
                val currentPasswords = currentState.passwords + currentWish.passwords
                expect { currentState.copy(passwords = currentPasswords) }
            }
            is HomeWish.OnCopyClipboardChanged -> {
                effect { HomeEffect.CopyClipboard("Copied '${currentWish.password}'") }
            }
            HomeWish.NavigateToAddNewPassword -> {
                route { HomeEffect.NavigateToAddNewPassword }
            }
            HomeWish.NavigateToAuthenticator -> {
                route { HomeEffect.NavigateToAuthenticator }
            }
            HomeWish.NavigateToGenerator -> {
                route { HomeEffect.NavigateToGenerator }
            }
            HomeWish.NavigateToHelp -> {
                route { HomeEffect.NavigateToHelp }
            }
            HomeWish.OnVisibleChanged -> {
                return if (currentState.categories.isNotEmpty()) {
                    expect { currentState.copy(isVisible = true) }
                } else {
                    expect { currentState.copy(isVisible = false) }
                }
            }
            HomeWish.NavigateToMyAccount -> {
                route { HomeEffect.NavigateToMyAccount }
            }
            HomeWish.NavigateToPasswordHealth -> {
                route { HomeEffect.NavigateToPasswordHealth }
            }
            HomeWish.ToggleHiddenVisibility -> {
                expect { currentState.copy(isHidden = !currentState.isHidden) }
            }
            else -> unexpected { currentState }
        }
    }
}
