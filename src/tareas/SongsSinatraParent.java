package tareas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SongsSinatraParent {

    public static WebDriver driver;

    public static void navegar(String url) {
        driver = new ChromeDriver(); //carga el driver de Chrome
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //hace un delay de 20 segs
        driver.navigate().to(url); //abre la url solicitada
    }

    public static void realizarLoginCorrecto(String usuario, String password) {
//    HomePage:
//    linkLogin: txt="log in "
//
//    LoginPage:
//    usernameTxt: id="username"
//    passwordTxt: id="password"
//    loginBtn: value="Log In"
        WebElement linkLogin = driver.findElement(By.linkText("log in"));
        if (linkLogin.isDisplayed() && linkLogin.isEnabled()){
            linkLogin.click();
        } else{
            System.out.println("El link de login no existe");
            System.exit(-1);
        }

        WebElement userNameTxt = driver.findElement(By.id("username"));
        userNameTxt.sendKeys(usuario);
        WebElement passwordTxt = driver.findElement(By.id("password"));
        passwordTxt.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.cssSelector("[value^='Log In']"));
        loginBtn.click();
        System.out.println("El usuario hizo login correctamente");
    }

    public static void validarHomePage() {
//    txtBienvenida:
//    imgSinatra: src="/images/sinatra.jpg"
//    linkLogin: txt="href="/login""
//    linkLogout: txt = "href="/logout""

        WebElement imgSinatra = driver.findElement(By.cssSelector("[src='/images/sinatra.jpg']"));
        WebElement linkLogin = driver.findElement(By.cssSelector("[href=\'/login\']"));

        if (imgSinatra.isDisplayed() && linkLogin.isDisplayed()){
            System.out.println("Imagen de Sinatra y Link login desplegados correctamente");
        } else {
            System.out.println("No se encontro la imagencilla");
            System.exit(-1);
        }


    }

    public static void validarmensajeBienvenida(String mensajeBienvenida) {
//    HomePage:
//    mensajeBienvenida: id="flash"
//    linkLogout: href="/logout"

        WebElement homeBtn   = driver.findElement(By.cssSelector(".current"));
        homeBtn.click();

        WebElement msgBienvenida = driver.findElement(By.cssSelector(".flash"));
        if (msgBienvenida.isDisplayed()){
            System.out.println("El mensaje de bienvenida si aparece");
        }else {
            System.out.println("El mensaje de bienvenida nunca aparecio");
            System.exit(-1);
        }

        WebElement linkLogout = driver.findElement(By.cssSelector("[href=\'/logout\']"));
        if (linkLogout.isDisplayed()){
            System.out.println("Link logout desplegado correctamente");
        } else {
            System.out.println("El link de logout no aparece desplegado");
            System.exit(-1);
        }
    }

    public static void cerrarBrowser() {
        driver.quit();
    }

    public static void validateSongsPage() {
        WebElement songsTitle = driver.findElement(By.cssSelector("section h1"));
        String currentUrl = driver.getCurrentUrl();
        WebElement songsLink = driver.findElement(By.cssSelector("[href='/songs']"));
        String currentClass = songsLink.getAttribute("class");
        List<WebElement> listaCanciones = driver.findElements(By.cssSelector("#songs li"));

        if (songsTitle.isDisplayed() &&
                currentUrl.endsWith("songs") &&
                currentClass.equals("current") &&
                listaCanciones.size() > 0) {
            System.out.println("Si estoy en la pagina de songs");
        } else {
            System.out.println("No estoy en la pagina de songs.");
            cerrarBrowser();

            System.exit(-1);
        }
    }

}