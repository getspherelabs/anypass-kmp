import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository

class FakeAddNewRepository: AddNewPasswordRepository {

    override suspend fun insertPassword(password: AddNewPasswordDomain) {
        println(password)
    }
}