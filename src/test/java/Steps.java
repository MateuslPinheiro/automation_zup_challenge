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
    private String verificacaoX;

    @Dado("^que entro no site da Dominária$")
    public void queEntroNoSiteDaDominária() throws Throwable {
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

    @Então("^visualizo a página da carta$")
    public void visualizoAPáginaDaCarta() throws Throwable {
        String produto = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr/td[1]/div[2]")).getText();
        Assert.assertEquals("Imagem meramente ilustrativa.", produto);
    }

    @Então("^visualizo a página inicial$")
    public void visualizoAPáginaInicial() throws Throwable {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.dominariacg.com.br/", url);
    }

    @Então("^visualizo a mensagem \"([^\"]*)\"$")
    public void visualizoAMensagem(String arg1) throws Throwable {
        String alerta = driver.findElement(By.className("alertaErro")).getText();
        Assert.assertEquals(arg1, alerta );
    }

    @Então("^visualizo a página de busca avançada$")
    public void visualizoAPáginaDeBuscaAvançada() throws Throwable {
        String texto = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td[2]/b")).getText();
        Assert.assertEquals("Busca Avançada", texto);
    }

    @Dado("^que estou em uma página de uma carta com estoque$")
    public void queEstouEmUmaPáginaDeUmaCartaComEstoque() throws Throwable {
        driver = new ChromeDriver();
        driver.get("https://www.dominariacg.com.br/?view=ecom/item&tcg=1&card=62913");
        driver.manage().window().maximize();

    }

    @Quando("^compro (\\d+) unidades?$")
    public void comproUnidade(int arg1) throws Throwable {
        String unidades = String.valueOf(arg1);
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(unidades);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[starts-with(@class,'botaoComprar')]")).click();
    }

    @Quando("^acesso o carrinho$")
    public void acessoOCarrinho() throws Throwable {
        driver.findElement(By.id("meuCarrinhoShow")).click();
        driver.findElement(By.name("btMeuCarrinho")).click();
    }

    @Então("^visualizo o carrinho com uma carta$")
    public void visualizoOCarrinhoComUmaCarta() throws Throwable {
        String text = driver.findElement(By.className("brdb")).getText();
        Assert.assertEquals("Produto", text);
    }



    @Então("^recebo a mensagem \"([^\"]*)\"$")
    public void receboAMensagem(String arg1) throws Throwable {
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();
        alerta.accept();
        Assert.assertEquals(arg1, texto);
    }


    @Quando("^compro o dobro de unidades que meu estoque permite$")
    public void comproODobroDeUnidadesQueMeuEstoquePermite() throws Throwable {
        Calculo unidades = new Calculo();
        String texto = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr[2]/td[5]")).getText();
        unidades.setEstoque(texto);
        verificacaoX = unidades.getEstoque();
        unidades.dobrar();
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(unidades.getEstoque());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[starts-with(@class,'botaoComprar')]")).click();
    }

    @Então("^visualizo que há x unidades$")
    public void visualizoQueHáXUnidades() throws Throwable {
        String texto = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr[2]/td[3]")).getText();
        texto = texto.replaceAll("\\D","");
        Assert.assertEquals(verificacaoX,texto);
    }


    @Quando("^compro exatamente o número de unidades do meu estoque$")
    public void comproExatamenteONúmeroDeUnidadesDoMeuEstoque() throws Throwable {
        Calculo unidades = new Calculo();
        String texto = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table[3]/tbody/tr/td[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr/td[2]/div[2]/table/tbody/tr[2]/td[5]")).getText();
        unidades.setEstoque(texto);
        verificacaoX = unidades.getEstoque();
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"txt_qty_12938717\"]")).sendKeys(unidades.getEstoque());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[starts-with(@class,'botaoComprar')]")).click();
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
