import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
//        glue = "steps",
//        tags = {"@fluxoDeExcecao02Carrinho"},
        plugin = {"pretty", "html:target/report-html","json:target/report.json"}, //Deixa bonitinho, coloca a documentação na pasta report, documentação em estado bruto (.json)
        monochrome = true, //Tira as cores
        snippets = SnippetType.CAMELCASE, //Coloca os STEPS em Camel Case
        dryRun = false, //Mostra os steps faltantes
        strict = false // Aumenta o rigor da plotagem
)
public class RunnerTest {
}
