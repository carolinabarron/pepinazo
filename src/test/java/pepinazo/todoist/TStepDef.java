package todoist;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TStepDef {
    public WebDriver driver;
    public WebDriverWait wait;
    public int numeroCanicas;
    @Before
    public void setUpTest() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        driver = new ChromeDriver(opt);
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().setSize(new Dimension(900, 550));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @After
    public void tearDownTest() {
        driver.quit();
    }
    @Given("I navigate to todoist")
    public void iNavigateToTodoist() {
        driver.get("https://todoist.com");
        //asegurar que el titulo sea igual a "Todoist: The to do list to organize work & life";
        assertEquals("Todoist: The to do list to organize work & life", driver.getTitle());
    }
    @When("I enter my credentials")
    public void iEnterMyCredentials(String usuario, String password) {
        //click login button
        driver.findElement(By.cssSelector("[href='/users/showlogin']")).click();
        WebElement userNameTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        //asegurarse que el userNameTxt este visible
        assertTrue(userNameTxt.isDisplayed());
        //enter username
        userNameTxt.sendKeys(usuario);
        //enter password
        driver.findElement(By.id("password")).sendKeys(password);

        //click login button
        WebElement loginButton = driver.findElement(By.cssSelector(".submit_btn"));
        ///asegurarse que el boton este habilitado
        assertTrue(loginButton.isEnabled());
        //click al boon
        loginButton.click();
    }
    @Then("I can see the Project page")
    public void iCanSeeTheProjectPage() {
        //asegurar que el titulo de la pagina sea "Today: Todoist"
        assertTrue(driver.getTitle().contains("Todoist"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Inbox']")));
    }
    @When("I log in using facebook")
    public void iLogInUsingFacebook() {
        //click login button
        driver.findElement(By.cssSelector("[href='/users/showlogin']")).click();
        WebElement userNameTxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        //darle click a login with facebook
        driver.findElement(By.cssSelector(".ist_button_facebook")).click();
        //autorizar la aplicacion
        driver.findElement(By.id("email")).sendKeys("jomarnavarro@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Test123456");
        driver.findElement(By.id("loginbutton")).click();
    }


    @Given("hice login con {word} y {word}")
    public void yoIngreseAlaAplicacionDeTodoist(){
        iNavigateToTodoist();
        iEnterMyCredentials("jomarnavarro@gmail.com", "Test@1234");
        iCanSeeTheProjectPage();


    }

    @When("Agrego un proyecto nuevo desde {string}")
    public void agregoUnProyectoNuevoDesde(String manera) {
        if(manera.contains("+")) {
            agregarProyectoDesdeIcono();
        } else if (manera.contains("New Project")) {
            agregarProyectoLigaNewProject();
        } else if(manera.contains("above")) {
            agregarProyectoArriba();
        } else if(manera.contains("below")) {
            agregarProyectoAbajo();
        } else if(manera.contains("task")) {
            agregarProyectoTask();
        }

    }


    private void agregarProyectoDesdeIcono() {
        //primero encontrar el elemento de 'projects'
        //css="button[data-track='navigation|projects_panel']"
        WebElement projectsElement = driver.findElement(By.cssSelector("button[data-track='navigation|projects_panel']"));

        //luego poner el cursor encima del elemento projects
        Actions ac = new Actions(driver);
        ac.moveToElement(projectsElement).perform();


        //wait hasta que este visible el icono de mas
        //css="button[data-track='navigation|projects_panel'] + div > button"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-track='navigation|projects_panel'] + div > button"))).click();
        //darle click al elemento

        //wait hasta que este presente el elemento css="div[role='dialog'] form"
        WebElement projName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='dialog'] form")));

        //buscar el elemento de projectname id="edit_project_modal_field_name"
        WebElement projectNameElement = driver.findElement(By.id("edit_project_modal_field_name"));
        projectNameElement.sendKeys("TestEquipo2");


        //buscar el elemento que dice css="button[aria-labelledby="edit_project_modal_field_color_label"]"
        WebElement projectColor = driver.findElement(By.cssSelector("button[aria-labelledby='edit_project_modal_field_color_label']"));
        //darle click
        projectColor.click();

        // esperar hasta que haya muchos elementos en la lista css="li[id*='dropdown-select']"
        List<WebElement> listaColor = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li[id*='dropdown-select']")));
        //click en el elemento cuyo linkText="<color deseado>"
     //  WebElement color = driver.findElement(By.linkText("Berry Red"));
        WebElement color = listaColor.get(6);
        color.click();

        //buscar el elemento de 'favorito', y darle click solo en el caso de que sea fovorito name="is_favorite"
      //  WebElement favorito = driver.findElement(By.name("is_favorite"));
        WebElement favorito = driver.findElement(By.cssSelector(".reactist_switch"));
        favorito.click();


        //buscar por linkText="Add" y darle click
        WebElement agregar = driver.findElement(By.cssSelector("[type='submit']"));
        agregar.click();

       }


    private void agregarProyectoLigaNewProject() {
    }
    private void agregarProyectoArriba() {
    }

    private void agregarProyectoAbajo() {
    }

    private void agregarProyectoTask() {
    }




    @Then("El nuevo proyecto debe estar listado al final")
    public void elNuevoProyectoDebeEstarListadoAlFinal(){

    }


    @Given("I have {int} marbles")
    public void iHaveMarbles(int numMarbles){
        numeroCanicas=numMarbles;
    }
    @When("I give away {int} marbles")
    public void iGiveAway(int numMarbles){
        numeroCanicas = numeroCanicas - numMarbles;
    }

    @Then("I have {int} marbles left")
    public void iHaveMarblesLeft(int numMarbles) {
        assertTrue(numeroCanicas == numMarbles);
    }

}
