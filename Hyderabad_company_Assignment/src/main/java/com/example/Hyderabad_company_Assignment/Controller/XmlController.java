package com.example.Hyderabad_company_Assignment.Controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;
import org.json.XML;
import java.io.File;
import java.io.IOException;

@RestController
public class XmlController {
    private final String JSON_OUTPUT_PATH = "C:\\output\\output.json";

    @PostMapping("/convert")
    public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile file) {
        try {

            String xmlContent = new String(file.getBytes());

            // Convert XML to JSON

            JSONObject jsonObj = XML.toJSONObject(xmlContent);
            String jsonOutput = jsonObj.toString();

            File outputFile = new File(JSON_OUTPUT_PATH);
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
            FileUtils.writeStringToFile(outputFile, jsonOutput, "UTF-8");

            return ResponseEntity.ok().body("Conversion successful. JSON file saved at: " + JSON_OUTPUT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the file.");
        }
    }
}
