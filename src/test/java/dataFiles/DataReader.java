package dataFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    //I take this method to FundamentalUseForTest class.
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to String
       String jsonContent =  FileUtils.readFileToString(new File(filePath));
       //Convert String to HashMap Jackson databind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return  data;
    }






}
