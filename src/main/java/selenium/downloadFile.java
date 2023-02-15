package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class downloadFile {
    public static void main(String[] args) throws IOException {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory","/Users/mimran/Documents");
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://eternallybored.org/misc/wget/");
//        WebElement element = driver.findElement(By.cssSelector("a[href*=\"1.21.3\"][href*=\"32.zip\"]"));
//        element.click();

        URL url= new URL("https://eternallybored.org/misc/wget/releases/wget-1.21.3-win32.zip");
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        String fileToDownload = null;
        double updatedFileSize=0;
        double totalFileSize = tryGetFileSizeInKB(url)*1024;
        int percent;
        String[] pathContents = url.getPath().split("[\\\\/]");
        if(pathContents != null){
            fileToDownload =pathContents[pathContents.length-1];
            System.out.println("File Name : " + fileToDownload );
        }

        System.out.println("Size : "+ totalFileSize/1024 + " KB");

        while (-1 != (n = in.read(buf))) {
            updatedFileSize = updatedFileSize +n;
            out.write(buf, 0, n);
            percent = (int)Math.round((updatedFileSize/totalFileSize)*100);
            if(percent == 100)
            {
                System.out.println("File Downloaded");
                break;
            }
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("/Users/mimran/Documents"+fileToDownload);
        fos.write(response);
        fos.close();
        driver.close();

    }

    public static int tryGetFileSizeInKB(URL url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength()/1024;
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }
}
