package com.demos.vs1.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Checker {

    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is Down";
    private final String INCORRECT_PATH_URL = "Given URL is incorrect";

    @GetMapping("/check")
    public String getUrlMsg(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            System.out.println(conn.getResponseCode());
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2 || responseCodeCategory != 3) {
                returnMessage = SITE_IS_DOWN;

            } else {
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedInputException e) {
            returnMessage = INCORRECT_PATH_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }

}
