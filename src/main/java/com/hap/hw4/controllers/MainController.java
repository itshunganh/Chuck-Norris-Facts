package com.hap.hw4.controllers;
import com.hap.hw4.models.API;
import com.hap.hw4.models.APIRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    APIRepo apiRepo;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        String factValue ="";
        String apikey = "11be34823bmsh1585771edbe7789p142059jsn5e07fdb6c03d";
        try
        {
            URL url = new URL("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);
            connection.connect();
            BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            factValue = obj.getString("value");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        mv.addObject("hw4", apiRepo.findAll());
        mv.addObject("fact", factValue);
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("id") String id, @RequestParam("quotes") String quotes){
        ModelAndView mv = new ModelAndView("redirect:/");
        API quotesToSave ;
        if(!id.isEmpty())
        {
            Optional<API> users = apiRepo.findById(id);
            quotesToSave = users.get();
        }
        else
        {
            quotesToSave = new API();
            quotesToSave.setId(UUID.randomUUID().toString());
        }
        quotesToSave.setQuotes(quotes);
        apiRepo.save(quotesToSave);
        mv.addObject("hw4", apiRepo.findAll());
        return mv;
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        apiRepo.deleteById(id);
        return mv;
    }

}
