import io.spherelabs.addnewpassworddomain.model.AddNewCategoryDomain
import io.spherelabs.addnewpassworddomain.model.AddNewPasswordDomain
import io.spherelabs.addnewpassworddomain.repository.AddNewPasswordRepository
import kotlinx.coroutines.flow.Flow

class FakeAddNewRepository: AddNewPasswordRepository {

    override suspend fun insertPassword(password: AddNewPasswordDomain) {
        println(password)
    }

    override fun getCategories(): Flow<List<AddNewCategoryDomain>> {
        TODO("Not yet implemented")
    }
}