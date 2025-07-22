package com.example.demo;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.HO_details;
import com.example.demo.model.Language;
import com.example.demo.model.User;
import com.example.demo.model.people;
import com.opencsv.CSVWriter;

import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;



import org.springframework.core.io.Resource;



@RestController
public class HelloApplication {

    @RequestMapping("/hellomap")
    public String world(){
        return "baby...";
    }

    
     @GetMapping("/music/{fileName}")
    public ResponseEntity<Resource> streamAudio(@PathVariable String fileName) throws IOException {
        Path audioPath = Paths.get("C:/Users/50002113/Downloads/demo/demo/src/main/resources/static/" + "sample_audio.mp3");  //fileName
        Resource audioResource = new UrlResource(audioPath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(audioResource);
    }






    @RequestMapping("/timing")
    public String worldtime(){
        return "goast...";
    }

    @RequestMapping(path = "/languages", method = RequestMethod.GET)
    public List<Language> listLambdaLanguages() 
    {
        return Arrays.asList(new Language("node"), new Language("java"), new Language("python"));
    }

    @RequestMapping(path = "/peoples", method = RequestMethod.GET)
    public List<people> getPeoples() 
    {
        return Arrays.asList(new people("user1", "https://images.pexels.com/photos/2787341/pexels-photo-2787341.jpeg?cs=srgb&dl=pexels-ali-pazani-2787341.jpg&fm=jpg"), 
        new people("user22","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOcOBE1ph0KcYnOj5kmA0arm4zPyu1t-ll8yhv8jcNRG6l_f2DIpSO19c4i4QBW4hZt6k&usqp=CAU"), 
        new people("user44","https://mediaslide-europe.storage.googleapis.com/louisamodels/pictures/3725/13933/profile-1640014292-4a26a41437da03f345e9f0ed8fa0d60e.jpg"));
    }
   
    @RequestMapping(path = "/houseowner", method = RequestMethod.GET)
    public List<HO_details> Houseowner() 
    {

        SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    //        String csv = "C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"+ sdf1.format(timestamp) +"_Segment_Trail8.csv";
        String csv = "/Users/mac/Documents/personal" +"/"+ sdf1.format(timestamp) + "trytestdemo" +".csv" ; // "_Segment_Trail8.csv";  //"C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
            String data_row = "user.getName()"+","+"user.getage()"+","+"user.getgender()";
            String[] record = data_row.split(",");
            writer.writeNext(record);
            writer.close();
    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Arrays.asList(new HO_details("node" , "contactAddress" ,"doc" ,
        "email_phone" , "latitude" , "longitude", "password" ,
        "ref_code" , "reg_date" ,"rej_reason" , "username" ,"verification"),
        
        new HO_details("node" , "contactAddress" ,"doc" ,
        "email_phone" , "latitude" , "longitude", "password" ,
        "ref_code" , "reg_date" ,"rej_reason" , "username" ,"verification"),
        
        new HO_details("node" , "contactAddress" ,"doc" ,
        "email_phone" , "latitude" , "longitude", "password" ,
        "ref_code" , "reg_date" ,"rej_reason" , "username" ,"verification"),
    
        new HO_details("node" , "contactAddress" ,"doc" ,
        "email_phone" , "latitude" , "longitude", "password" ,
        "ref_code" , "reg_date" ,"rej_reason" , "username" ,"verification"),

        new HO_details("node" , "contactAddress" ,"doc" ,
        "email_phone" , "latitude" , "longitude", "password" ,
        "ref_code" , "reg_date" ,"rej_reason" , "username" ,"verification"));

    
    }


    @PostMapping(path = "/saveuser" )
public String saveUser(@Validated @RequestBody User user) {

    SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        String csv = "C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"+ sdf1.format(timestamp) +"_Segment_Trail8.csv";
    String csv = "/Users/mac/Documents/personal" +"/"+ sdf1.format(timestamp) +".csv" ; 
    // "_Segment_Trail8.csv";  //"C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"
    try {
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String data_row = user.getName()+","+user.getage()+","+user.getgender();
        String[] record = data_row.split(",");
        writer.writeNext(record);
        writer.close();

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    return "saved";
}

@PostMapping(path = "/saveuser_separate" )
public String saveUser_separate(@Validated @RequestBody User user) {

    SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//        String csv = "C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"+ sdf1.format(timestamp) +"_Segment_Trail8.csv";
    String csv = "/Users/mac/Documents/personal" +"/"+ user.getName() +".csv" ; 
    // "_Segment_Trail8.csv";  //"C:/Users/Dell/Desktop/Mahindra/java/Application/MapExtraction/csvfile/"
    try {
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String data_row = user.getName()+","+user.getlocation()+","+user.getaddress();
        String[] record = data_row.split(",");
        writer.writeNext(record);
        writer.close();

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    return "saved";
}

}
