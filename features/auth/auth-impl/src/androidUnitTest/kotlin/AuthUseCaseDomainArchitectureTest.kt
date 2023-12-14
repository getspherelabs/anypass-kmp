import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class AuthUseCaseDomainArchitectureTest {

    @Test
    fun `check classes with 'UseCase' suffix in 'usecase' package`() {
        Konsist
            .scopeFromPackage("io.spherelabs.authdomain.usecase")
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
            .scopeFromPackage("io.spherelabs.authdomain.usecase")
            .interfaces()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.resideInPackage("..usecase..")
            }
    }

    @Test
    fun `check use case function name is execute`() {
        Konsist
            .scopeFromPackage("io.spherelabs.authdomain.usecase")
            .classes()
            .withNameEndingWith("UseCase")
            .assertTrue {
                it.hasFunction { function ->
                    function.name == "execute" && function.hasOverrideModifier
                }
            }
    }
}
