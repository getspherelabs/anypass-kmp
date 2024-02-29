package io.spherelabs.addnewpasswordimpl.presentation.addnewlogin

import io.spherelabs.addnewpasswordapi.domain.usecase.GetWebsites
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class AddNewLoginMiddleware(
    private val getWebsitesUseCase: GetWebsites,
) : Middleware<AddNewLoginState, AddNewLoginWish> {

    override suspend fun process(
        state: AddNewLoginState,
        wish: AddNewLoginWish,
        next: suspend (AddNewLoginWish) -> Unit,
    ) {
        when (wish) {
            AddNewLoginWish.StartLoadedWebsites -> {
                getWebsitesUseCase.execute().collectLatest { newResult ->
                    newResult.onSuccess { data ->
                        val websites = data.list.distinct()
                        next.invoke(AddNewLoginWish.LoadedWebsites((websites)))
                    }
                }
            }
            else -> {}
        }
    }
}
