import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class AccountUseCaseDomainArchitectureTest {

  @Test
  fun `check account use case classes with 'UseCase' suffix in 'usecase' package`() {
    Konsist.scopeFromPackage("io.spherelabs.accountdomain.usecase").classes().run {
      assertTrue { it.hasNameEndingWith("UseCase") }
      assertTrue { it.resideInPackage("..usecase..") }
    }
  }

  @Test
  fun `check account use case interfaces with 'UseCase' suffx in 'usecase' package`() {
    Konsist.scopeFromPackage("io.spherelabs.accountdomain.usecase").interfaces().run {
      assertTrue { it.hasNameEndingWith("UseCase") }
      assertTrue { it.resideInPackage("..usecase..") }
    }
  }

  @Test
  fun `check account interface with 'Repository' suffix in 'repository' package`() {
    Konsist.scopeFromPackage("io.spherelabs.accountdomain.repository")
        .interfaces()
        .withNameEndingWith("Repository")
        .assertTrue { it.resideInPackage("..repository..") }
  }

  @Test
  fun `check use case function name is execute`() {
    Konsist.scopeFromPackage("io.spherelabs.accountdomain.usecase")
        .classes()
        .withNameEndingWith("UseCase")
        .assertTrue {
          it.hasFunction { function -> function.name == "execute" && function.hasOverrideModifier }
        }
  }
}
