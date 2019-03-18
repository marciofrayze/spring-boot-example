package change.me;

import io.github.theangrydev.domainenforcer.DomainEnforcer;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LayersValidationTest {

    private final DomainEnforcer domainEnforcer = DomainEnforcer.enforceSources(Paths.get("./src/main/java"));

    @Test
    public void checkDomainDoesNotDependOnAnyOtherPackage()
    {
        List<String> violations =
                domainEnforcer.checkThatPackageOnlyTalksToItself("change.me.domain").apartFrom("java");

        assertThat(violations)
                .describedAs("Domain package should be independent")
                .isEmpty();
    }

    /*
    @Test
    public void checkApplicationOnlyDependsOnDomain()
    {
        List<String> violations =
                domainEnforcer.checkThatPackageOnlyTalksToItself("change.me.application").apartFrom("java", "change.me.domain");

        assertThat(violations)
                .describedAs("Application package should dependent only on domain")
                .isEmpty();
    }

    @Test
    public void checkNoPackageDependsOnInfrastructure()
    {
        List<String> violations =
                domainEnforcer.checkThatNobodyTalksTo("change.me.infrastructure");

        assertThat(violations)
                .describedAs("Nobody should depend on the infrastructure package")
                .isEmpty();
    }*/

}
