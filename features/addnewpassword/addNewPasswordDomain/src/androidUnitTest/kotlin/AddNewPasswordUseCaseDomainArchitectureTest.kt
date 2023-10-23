import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class AddNewPasswordUseCaseDomainArchitectureTest {

    @Test
    fun `check add  new password classes with 'UseCase' suffix in 'usecase' package`() {
        Konsist
            .scopeFromPackage("io.spherelabs.addnewpassworddomain.usecase")
            .classes()
            .run {
                assertTrue {
                    it.hasNameEndingWith("UseCase")
                }
                assertTrue {
                    it.resideInPackage("..usecase..")
                }
            }
    }

    @Test
    fun `check the interfaces with 'UseCase' suffix in 'usecase' package`() {
        Konsist
            .scopeFromPackage("io.spherelabs.addnewpassworddomain.usecase")
            .interfaces()
            .run {
                assertTrue {
                    it.hasNameEndingWith("UseCase")
                }
                assertTrue {
                    it.resideInPackage("..usecase..")
                }
            }

    }

    @Test
    fun `check interface with 'Repository' suffix in 'repository' package`() {
        Konsist
            .scopeFromPackage("io.spherelabs.addnewpassworddomain.repository")
            .interfaces()
            .withNameEndingWith("Repository")
            .assertTrue {
                it.resideInPackage("..repository..")
            }
    }

    @Test
    fun `check use case function name is execute`() {
        Konsist
            .scopeFromPackage("io.spherelabs.addnewpassworddomain.usecase")
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.hasFunction { function ->
                    function.name == "execute" && function.hasOverrideModifier
                }
            }
    }
}
