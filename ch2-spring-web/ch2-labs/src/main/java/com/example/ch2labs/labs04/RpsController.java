package com.example.ch2labs.labs04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RpsController {

    @GetMapping("/rps")
    @ResponseBody
    public Map<String, String> rps(@RequestParam String user) {
        Map<String, String> response = new HashMap<>();

        int comRps = (int) Math.floor(Math.random() * 3 + 1);
        String server = "";

        switch (comRps) {
            case 1:
                server = "rock";
                break;
            case 2:
                server = "scissors";
                break;
            case 3:
                server = "paper";
                break;
            default:
                server = "error";
        }

        String result = "";

        if(user.equals(server)) {
            result = "무승부";
        } else if (user.equals("rock")) {
            if(server.equals("scissors")) {
                result = "승리!";
            } else {
                result = "패배...";
            }
        } else if (user.equals("scissors")) {
            if(server.equals("paper")) {
                result = "승리!";
            } else {
                result = "패배...";
            }
        } else if (user.equals("paper")) {
            if(server.equals("rock")) {
                result = "승리!";
            } else {
                result = "패배...";
            }
        }

        response.put("user", user);
        response.put("server", server);
        response.put("result", result);


        return response;
    }
}
