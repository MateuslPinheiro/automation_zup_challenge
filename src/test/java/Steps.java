import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Steps {
    private WebDriver driver;
    private String busca;
    @Dado("^que entro no site da Dominaria$")
    public void queEntroNoSiteDaDominaria() throws Throwable {
        driver = new ChromeDriver();
        driver.get("https://www.dominariacg.com.br/");
        driver.manage().window().maximize();
        //Thread.sleep(3000);
    }


    @Quando("^faço uma busca por \"([^\"]*)\"$")
    public void façoUmaBuscaPor(String arg1) throws Throwable {
        driver.findElement(By.name("busca")).sendKeys(arg1);
        driver.findElement(By.name("busca")).sendKeys(Keys.ENTER);
//        driver.findElement(By.xpath("//img[@title='Buscar']")).click();
        busca = arg1;
    }

    @Então("^visualizo os produtos$")
    public void visualizoOsProdutos() throws Throwable {
        Assert.assertEquals("Busca por: " + busca +" | Dominaria Cards & Games", driver.getTitle());
    }

    @Então("^filtro por produtos com estoque$")
    public void filtroPorProdutosComEstoque() throws Throwable {
        driver.findElement(By.name("txt_estoque")).click();
        driver.findElement(By.name("btFiltrar")).click();
    }


    @Quando("^seleciono um produto$")
    public void selecionoUmProduto() throws Throwable {
        driver.findElement(By.id("img_0")).click();
//        driver.findElement(By.xpath("//a[title='Island']"));
    }

    @Então("^visualizo a página do produto$")
    public void visualizoAPáginaDoProduto() throws Throwable {
        Assert.assertNotEquals("Busca por: " + busca +" | Dominaria Cards & Games", driver.getTitle());
    }

    @Quando("^aperto em comprar$")
    public void apertoEmComprar() throws Throwable {
        driver.findElement(By.xpath("//input[starts-with(@class,'botaoComprar')]")).click();
    }

    @Quando("^vejo meu carrinho$")
    public void vejoSeTiveSucesso() throws Throwable {
        driver.findElement(By.id("meuCarrinhoShow")).click();
        driver.findElement(By.name("btMeuCarrinho")).click();
    }
    @Então("^verifico se o item está lá$")
    public void verificoSeOItemEstáLá() throws Throwable {
        String text = driver.findElement(By.className("brdb")).getText();
        Assert.assertEquals("Produto", text);
    }

    @After(order = 1)
    public void screenshot(Scenario cenario) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshot/" + cenario.getId() + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @After(order = 0)
    public void fecharSite(){
        driver.quit();
    }
}
