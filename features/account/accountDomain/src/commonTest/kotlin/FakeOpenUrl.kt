import io.spherelabs.accountdomain.repository.OpenUrl

class FakeOpenUrl : OpenUrl {

    override suspend fun execute(url: String) {
        handleUrl(url).onSuccess {
            println("$it is valid format.")
        }.onFailure {
            throw it
        }
    }

    private fun handleUrl(url: String): Result<String> {
        if (url.isEmpty()) {
            return Result.failure(Exception("Url is empty."))
        }
        return Result.success(url)
    }
}
