package com.green.booktodolist.bestSeller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/aladinbestseller")
public class BestSellerController {

    private AladinSearchApi aladin;

    public BestSellerController(AladinSearchApi aladin) {
        this.aladin = aladin;
    }

    @GetMapping
    public String aladinbestbook(){
        String jsonPrintString = null;
        try {
            String result = aladin.search();
            JSONObject jsonObject = XML.toJSONObject(result.toString());
            jsonPrintString = ((JSONObject) jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPrintString;
    }
}
