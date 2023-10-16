import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class ChangePasswordDomainArchitectureTest {
    @Test
    fun `check classes with 'UseCase' suffix in 'usecase' package`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                println("Use case in ${it.name}")
                println("Use case in ${it.moduleName}")
                it.resideInPackage("..usecase..")
            }
    }


    @Test
    fun `check the interfaces with 'UseCase' suffix in 'usecase' package`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.resideInPackage("..usecase..")
            }
    }

    @Test
    fun `check interface with 'Repository' suffix in 'repository' package`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith("Repository")
            .assertTrue {
                it.resideInPackage("..repository..")
            }
    }

    @Test
    fun `check use case function name is execute`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.hasFunction { function ->
                    function.name == "execute" && function.hasOverrideModifier
                }
            }
    }
}
