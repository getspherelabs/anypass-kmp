import io.spherelabs.changepassworddomain.repository.ChangePasswordRepository

class FakeChangePasswordRepository: ChangePasswordRepository {

    override suspend fun getCurrentKeyPassword(): String {
        return "1234"
    }

    override suspend fun setNewKeyPassword(newKeyPassword: String) {
       handleSet(newKeyPassword)
    }

    private fun handleSet(newKeyPassword: String) {
        if (newKeyPassword.isNotEmpty()) {
            Result.success("It is successful")
        }
    }
}
