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

public class LikeSongFrankSinatra extends LikeSongParent {

    public static void main(String[] args) {
        navegarSitio("https://evening-bastion-49392.herokuapp.com/");
        validaHomePage();
        navegarListadoCanciones();
        navegarPrimeraCancion();
        likeCancion();
    }
}
