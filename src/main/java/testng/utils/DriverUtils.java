package testng.utils;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {

    private static WebDriver driver;

    private static int TEMPO = 20;

    private static WebDriverWait wait;

    
    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
        }
        return driver;
    }

    public static void fecharDriver() {
        if (driver != null) {
            try {
                DriverUtils.iterarTelaPrincipal();
                By elemnto = By.xpath("/html/body/div/div[1]/form/div/a");
                WebElement logoff = null;
                if (DriverUtils.getDriver().findElements(elemnto).size() > 0) {
                    logoff = DriverUtils.getDriver().findElement(elemnto);
                    DriverUtils.clicar(logoff);
                }
            } catch (Exception e) {
                // Log the exception if necessary
            } finally {
                DriverFactory.killDriver();
                driver = null;
            }
        }
    }
    
    private static void getDriverWait() {
    	if (wait == null) {
    		wait = new WebDriverWait(driver, Duration.ofSeconds(TEMPO));
    	}

    }
    

    public static void mover(String x, String y) {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.moveTo(".concat(x).concat(",").concat(y).concat(")"));
    }

    public static void sendKeys(WebElement elemento, String texto) {
        buscarElementoPresente(elemento);
        elemento.sendKeys(texto);
    }

    public static void sendKeys(WebElement elemento, Keys key) {
        elemento.sendKeys(key);
    }

    public static String clicarMenu(WebElement menu1, String menu2) {
        if (menu2 != null) {
            WebElement eMenu2 = null;
            try {
                eMenu2 = driver.findElement(By.linkText(menu2));
            } catch (Exception e) {
                // Handle the exception if necessary
            }

            if (menu2 != null) {
                if (eMenu2 == null || !eMenu2.isDisplayed()) {
                    clicar(menu1);
                    eMenu2 = driver.findElement(By.linkText(menu2));
                }
                return clicarMenu(eMenu2, null);
            }
        }
        return null;
    }

    public static void clicar(WebElement elemento) {
        try {
            scrollToElemento(elemento);
            elemento.click();
        } catch (ElementClickInterceptedException e) {
            scrollToElemento(elemento);
            elemento.click();
        }
    }

    public static void scrollToElemento(WebElement elemento) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverUtils.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", elemento);
    }

    public static void scroll(String posicao) {
        JavascriptExecutor jse = (JavascriptExecutor) DriverUtils.getDriver();
        String scr = "scroll(0,###);";
        jse.executeScript(scr.replace("###", posicao));
    }

    public static void zerarScroll() {
        JavascriptExecutor jse = (JavascriptExecutor) DriverUtils.getDriver();
        jse.executeScript("window.scrollTo(0,0)");
    }

    public static void limparCampo(WebElement elemento) {
        buscarElementoPresente(elemento);
        elemento.clear();
        delay(200);
    }

    public static void duploClique(WebElement elemento) {
        buscarElementoClicavel(elemento);
        elemento.click();
        elemento.click();
    }

    public static void duploCliqueComAjax(WebElement elemento) {
        buscarElementoClicavel(elemento);
        elemento.click();
        esperarPorAjax();
        elemento.click();
    }

    public static void esperarPorAjax() {
        try {
            delay(800);
            getDriverWait();
            wait.withMessage("Tempo para aguardar o ajax esgotado.");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderMessage")));
        } catch (StaleElementReferenceException e) {
            delay(1000);
            getDriverWait();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loaderMessage")));
        }
    }

    public static void esperarPor(By by) {
        try {
            int qtdEspera = 0;
            do {
                Thread.sleep(1000);
                if (qtdEspera > TEMPO) {
                    throw new TimeoutException();
                }
                qtdEspera++;
            } while (getDriver().findElements(by).size() == 0);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static boolean buscarElementoPresente(WebElement locator) {
        getDriverWait();
        wait.withMessage("Elemento " + locator + " não encontrado");
        wait.until((ExpectedConditions.elementToBeClickable(locator)));
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(locator)));
        return true;
    }

    public static void buscarElementoClicavel(WebElement locator) {
    	getDriverWait();
        wait.withMessage("Elemento " + locator + " não clicável");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    public static void selecionarComboPeloValor(WebElement elemento, Integer valor) {
        buscarElementoPresente(elemento);
        buscarElementoClicavel(elemento);
        Select select = new Select(elemento);
        select.selectByValue(valor.toString());
        esperarPorAjax();
    }

    public static void selecionarComboPeloNome(WebElement elemento, String texto) {
        buscarElementoPresente(elemento);
        buscarElementoClicavel(elemento);
        Select select = new Select(elemento);
        select.selectByVisibleText(texto);
        esperarPorAjax();
    }

    public static void selecionarComboPeloIndice(WebElement elemento, int indice) {
        buscarElementoClicavel(elemento);
        Select select = new Select(elemento);
        select.selectByIndex(indice);
        esperarPorAjax();
    }

    public static String getValor(WebElement elemento) {
        Select select = new Select(elemento);
        return select.getFirstSelectedOption().getAttribute("value");
    }

    public static String getValorCombo(WebElement elemento) {
        Select select = new Select(elemento);
        return select.getFirstSelectedOption().getText();
    }

    public static boolean isComboVazia(WebElement elemento) {
        Select select = new Select(elemento);
        return (select.getOptions().size() > 1 ? false : true);
    }

    public static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*********************************
     * Métodos específicos para manipular frames e pages
     ************************/

    public static void iterarTelaPrincipal() {
        Set<String> windowId = driver.getWindowHandles();
        Iterator<String> iterator = windowId.iterator();
        String principal = iterator.next();
        driver.switchTo().window(principal);
        driver.switchTo().defaultContent();
    }

    public static void avancarFrame() {
    	getDriverWait();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.frameToBeAvailableAndSwitchToIt("_blank")));
    }

    public static void trocarFrame(String frame) {
    	getDriverWait();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame)));
        try {
            getDriver().switchTo().frame(frame);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private static int searchIndexColumnTable(String nomeTabela, String coluna) {
        int col = 1;
        for (WebElement elemento : DriverUtils.getDriver().findElement(By.id(nomeTabela))
                .findElements(By.tagName("th"))) {
            if (elemento.getText().equals(coluna)) {
                break;
            }
            col++;
        }
        return col;
    }

    public static boolean checkPresentText(String tableName, int line, String column, String value) {
        boolean condicao = false;
        int columnIndex = searchIndexColumnTable(tableName, column);
        tableName = ".//*[@id='" + tableName + "']/table[2]/tbody";
        List<WebElement> elements = DriverUtils.getDriver()
                .findElements(By.xpath(tableName + "/tr[" + (line + 2) + "]/td[" + columnIndex + "]"));
        for (WebElement elementLine : elements) {
            if (elementLine.getText().equals(value)) {
                condicao = true;
                break;
            }
        }
        return condicao;
    }

    public static boolean checkButtonPresent(String tableName, int line, String column) {
        boolean condicao = false;
        int columnIndex = searchIndexColumnTable(tableName, column);
        tableName = ".//*[@id='" + tableName + "']/table[2]/tbody";
        List<WebElement> elements = DriverUtils.getDriver()
                .findElements(By.xpath(tableName + "/tr[" + (line + 2) + "]/td[" + columnIndex + "]"));
        for (WebElement elementLine : elements) {
            if (elementLine.findElements(By.tagName("button")).size() > 0) {
                condicao = true;
                break;
            }
        }
        return condicao;
    }

    public static int searchQuantityLinesTable(String tableName) {
        return DriverUtils.getDriver()
                .findElements(By.xpath(".//*[@id='" + tableName + "']/table[2]/tbody/tr[*]/td[1]")).size();
    }

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public static String getText(By by) {
        String texto = null;
        if (buscarElementoPresente(driver.findElement(by))) {
            texto = driver.findElement(by).getText();
        }
        return texto;
    }

    public static boolean elementIsVisibe(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public static void waitToElementDisapear(List<WebElement> element) {
        do {
            delay(1000);
        } while (element.size() != 0);
    }

    public static Integer getRandonNumber(Integer limit) {
        Random rdm = new Random();
        return rdm.nextInt(limit);
    }

}