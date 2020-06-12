package tareas;

import com.google.common.io.LittleEndianDataOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LikeSongParent {
    static WebDriver driver;
    static WebDriverWait wait;
    private static Integer numeroLikes;

    public static void navegarSitio(String url) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.navigate().to(url);
    }

    public static void validaHomePage() {
        boolean mensajeDesplegado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section p"))).isDisplayed();
        boolean imagenVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/images/sinatra.jpg']"))).isDisplayed();
        boolean linkLoginVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='/login']"))).isDisplayed();
        if(mensajeDesplegado && imagenVisible && linkLoginVisible){
            System.out.println("La página cargó correctamente");
        }else{
            System.out.println("No se cargaron");
            System.exit(-1);
        }
    }

    public static void navegarListadoCanciones() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href='/songs']"))).click();
        WebElement headerSongs = driver.findElement(By.cssSelector("section h1"));
        List <WebElement> listaCanciones = driver.findElements(By.cssSelector("#songs li"));
        WebElement primeraCancion = driver.findElement(By.cssSelector("[href='/songs/1']"));

        if (listaCanciones.size() > 1) {
            System.out.println("Si hay canciones en la lista");
            primeraCancion.click();
        } else {
            System.out.println("No hay canciones en la lista,  esta vacia");
            System.exit(-1);
        }
    }

    public static void navegarPrimeraCancion() {

        String campoLikes = driver.findElement(By.cssSelector("div p")).getText();
        WebElement btnLike = driver.findElement(By.cssSelector(("div [type='submit'")));
        numeroLikes = Integer.valueOf(campoLikes.replaceAll("[^0-9]", ""));
        System.out.println("El numero de likes es" + numeroLikes);
        btnLike.click();
    }

    public static void likeCancion() {
        String[] split = driver.findElement(By.cssSelector("div p")).getText().split(" ");
        int campoLikes = Integer.parseInt(split[split.length-2]);

        if(numeroLikes>campoLikes){
            System.out.println("Se agregaron likes");
        }else {
            System.out.println("Los likes son menores");
            System.exit(-1);
            driver.quit();
        }

    }