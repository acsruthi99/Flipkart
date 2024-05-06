package demo;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    
    @BeforeClass
    public TestCases(){
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void enterText(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    private void hitEnter(WebElement element) {
        // Hit Enter  sendKeys("Keys.RETURN")
        element.sendKeys(Keys.RETURN);
    }

        @Test
        public void testCase01(){
        System.out.println("Start Test case: testCase01");
        
        driver.get("http://www.flipkart.com");
        WebElement searchbar = driver.findElement(By.Class("zDPmFV"));
        enterText(searchbar, "Washing machine");
        Thread.sleep(3000);
        hitEnter(searchbar);//div[text()='Popularity']
         driver.findElement(By.text("Popularity")).click();

         List <WebElement> ratesLessthanFive = driver.findElements(By.xpath("//div[@class='XQDdHH']"));
         Thread.sleep(3000);
         // get the count of ratings lessthan 4
         int count = 0;
         WebElement pageInfoElement = driver.findElement(By.xpath("//div[@class='_1G0WLw']/span"));
         Thread.sleep(3000);
         String pageInfoText = pageInfoElement.getText();
         String[] parts = pageInfoText.split("of");
        String numberAfterOf = parts[1].trim();
        int pageNumber = Integer.parseInt(numberAfterOf);
         WebElement nxtBtn = driver.findElement(By.xpath("//a[@class='_9QVEpD' and .//span[contains(text(), 'Next')]]"));
         Thread.sleep(3000);
         for(int i=0; i<pageNumber; i++){
         for(WebElement rating : ratesLessthanFive){
            double ratings = ratesLessthanFive.getText();
            if(ratings <= 4){
                count++;
            }
        }
        nxtBtn.click();
    }
         System.out.println("The count is :: "+ count);

         //scenario :: Iphone
         enterText(searchbar, "I Phone");
        Thread.sleep(3000);
         List <WebElement> title = driver.findElement(By.class("KzDlHZ"));
         Thread.sleep(3000);
         List <WebElement> discountpercent = driver.findElement(By.class("UkUFwK"));
         Thread.sleep(3000);

         for(int i=0; i<pageNumber; i++){
            for(WebElement disc : discountpercent){
                String discountPercentText = discountPercentElement.getText();
                Thread.sleep(3000);
                String percentageValue = discountPercentText.replaceAll("[^\\d.]", ""); // Remove all non-numeric characters
                int discountPercentage = Integer.parseInt(percentageValue);
               
               if(discountPercentage > 17){
                   System.out.Println("Title :: "+title+ "Percent :: "+ discountPercentage);
               }
           }
           nxtBtn.click();
       }

       // scenario :: Coffee Mug

       enterText(searchbar, "Coffee Mug");
        Thread.sleep(3000);
       WebElement fourAndAboveRating = driver.findElement(By.xpath("//div[text()= '4★ & above']"));
       Thread.sleep(3000);
       fourAndAboveRating.click();
      
       List <WebElement> numberOfReviews = driver.findElements(By.xpath("//div[@class='dVXNbG']/preceding-sibling::div[1]/span[2]"));
       Thread.sleep(3000);
       List <WebElement> title = driver.findElements(By.Class("wjcEIp"));
       Thread.sleep(3000);
       List <WebElement> imgURL = driver.findElements(By.xpath("//a[@class='wjcEIp']/..//img[contains(@alt,'Coffee Mug')]"));
       Thread.sleep(3000);
       
       List<Integer> integerList = new ArrayList<>();
       for (WebElement sortedViewsCount : numberOfReviews){
        String str = sortedViewsCount.getText();
        int num = Integer.parseInt(str);
                integerList.add(num);
                Collections.sort(integerList);
       }
       for (int i = 0; i < 5; i++) {
        System.out.println("numberOfReviews: " + integerList[i] +" title: " + title.get(i).getText()+" imgURL: " + imgURL.get(i).getAttribute("src"));
       }
    }

     @AfterClass
    public void endTest(){
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }
}
