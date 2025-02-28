## 📌 Overview  
This project is a **Selenium Test Automation Framework** using **TestNG** to test the login, cart functionality, and navigation of **SauceDemo**. It also includes **automated test reports** using **Extent Reports** for better insights.  

## 🛠 Tech Stack  
✅ **Java** (JDK 11+)  
✅ **Selenium WebDriver**  
✅ **TestNG**  
✅ **Extent Reports**  
✅ **Maven** (for dependency management)  

## 📂 Project Structure  
```
project-root/
│── src/
│   ├── main/java/com/example/
│   │   ├── helper/
│   │   │   ├── ExtentManager.java
│   ├── test/java/com/example/
│   │   ├── AppTest.java
│   ├── resources/
│   │   ├── testng.xml
│── target/  # Compiled files & reports are stored here
│── pom.xml  # Maven dependencies
│── README.md  # Project documentation
```

## ⚙️ Setup Instructions  

### 1️⃣ Prerequisites  
Before running the tests, ensure you have the following installed:  
🔹 Java JDK 11+  
🔹 Maven  
🔹 Google Chrome & ChromeDriver  

### 2️⃣ Clone the Repository  
```sh
git clone https://github.com/naufal67741/web-auto-test.git
cd web-auto-test
```

### 3️⃣ Install Dependencies  
```sh
mvn clean install
```

### 4️⃣ Run Tests  
```sh
mvn clean test
```

### 5️⃣ View Test Report  
After running tests, the **Extent Report** will be generated in the `target/` folder.  

To open the report:  
```sh
open target/ExtentReport.html  # macOS  
start target\ExtentReport.html  # Windows  
```

## 🧪 Test Cases  

| Test Name              | Description                                       |
|------------------------|---------------------------------------------------|
| `loginTest()`         | Validates login with correct credentials          |
| `addItemToCartTest()` | Verifies adding an item to the cart               |
| `aboutUsPageTest()`   | Checks navigation to the "About Us" page          |

## 🛠 Configuration  

### Modify ChromeDriver Path  
To ensure the tests run correctly, update the **ChromeDriver path** in `AppTest.java`:  
```java
System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
```
Replace `"/path/to/chromedriver"` with the actual path on your system.  

---
