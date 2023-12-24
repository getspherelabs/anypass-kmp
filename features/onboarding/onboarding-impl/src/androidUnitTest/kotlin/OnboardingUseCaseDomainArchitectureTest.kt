import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class OnboardingUseCaseDomainArchitectureTest {

  @Test
  fun `check onboarding use case classes with 'UseCase' suffix in 'usecase' package`() {
    Konsist.scopeFromPackage("io.spherelabs.onboardingdomain.usecase").classes().run {
      assertTrue { it.hasNameEndingWith("UseCase") }
      assertTrue { it.resideInPackage("..usecase..") }
    }
  }

  @Test
  fun `check onboarding use case interfaces with 'UseCase' suffx in 'usecase' package`() {
    Konsist.scopeFromPackage("io.spherelabs.onboardingdomain.usecase").interfaces().run {
      assertTrue { it.hasNameEndingWith("UseCase") }
      assertTrue { it.resideInPackage("..usecase..") }
    }
  }

  @Test
  fun `check use case function name is execute`() {
    Konsist.scopeFromPackage("io.spherelabs.onboardingdomain.usecase")
        .classes()
        .withNameEndingWith("UseCase")
        .assertTrue {
          it.hasFunction { function -> function.name == "execute" && function.hasOverrideModifier }
        }
  }
}
