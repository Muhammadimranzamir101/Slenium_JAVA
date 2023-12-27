package com.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Iterator;


public class loginByPass {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://dev-qmsuat.baitussalam.org/");
            authenticateViaApi("TestUser943", "abc", driver);
            Thread.sleep(5000);

            driver.get("https://dev-qmsuat.baitussalam.org/app/dashboard");
            Thread.sleep(500000);

        } finally {
            driver.quit();
        }
    }

    public static void authenticateViaApi(String username, String password, WebDriver driver) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPost httpPost = new HttpPost("https://devapi-qmsuat.baitussalam.org/api/v1/auth/login");
            httpPost.setHeader("Content-Type", "application/json");

            JSONObject loginData = new JSONObject();
            loginData.put("userName", username);
            loginData.put("password", password);

            StringEntity entity = new StringEntity(loginData.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode value = null;

                for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext(); ) {
                    String fieldName = it.next();
                    JavascriptExecutor js = (JavascriptExecutor) driver;

                    if(fieldName == "accessToken"){
                        value = jsonNode.get(fieldName);
                        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "access_token", value.asText());
                    }
                    else if (fieldName == "roles") {
                        value = jsonNode.get(fieldName);
                        JsonNode jsonNode1 = objectMapper.readTree(value.traverse());
                        JsonNode authorityValue = null;

                        for (JsonNode node : jsonNode1) {
                            JsonNode authorityNode = node.get("authority");
                            if (authorityNode != null && authorityNode.isTextual()) {
                                authorityValue = authorityNode;
                                System.out.println("Authority Value: " + authorityValue.asText());
                            }
                        }

                        value = authorityValue;
                        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", fieldName, "[\"" + value.asText() + "\"]");

                        System.out.println("Field Name" + fieldName);
                        System.out.println("Value" + value);
                    }
                    else if (fieldName == "bookingOutlets") {
                        value = jsonNode.get(fieldName);
                        System.out.println("Key: " + fieldName);
                        System.out.println("Value: " + value.toString());
                        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", fieldName, value.toString());
                    } else {
                        value = jsonNode.get(fieldName);
                        System.out.println("Key: " + fieldName);
                        System.out.println("Value: " + value.asText());
                        js.executeScript("localStorage.setItem(arguments[0],arguments[1])", fieldName, value.asText());
                    }

                    System.out.println("Setting local storage: Key=" + fieldName + ", Value=" + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
