package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviders {

    @DataProvider
    public Object[][] jsonDataProvider() throws IOException {
        // Path to the JSON file
        String filePath = ".\\testdata\\usersData.json";

        // Read JSON file and map it to a list of Maps
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> dataList = objectMapper.readValue(new File(filePath), new TypeReference<List<Map<String, String>>>() {

        });

        // Convert the List<Map<String, String>> to Object[][]
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i] = new Object[]{dataList.get(i)};
        }
        return dataArray;
    }
}
