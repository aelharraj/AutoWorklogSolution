package jira.worklog;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {
	
  	 static String jsonBodyData="{ 'comment': '%s', 'started': '%s','timeSpent': '%s'}";

    public static JsonNode createPostBody(String data) {

        JsonFactory factory = new JsonFactory();
        factory.enable(Feature.ALLOW_SINGLE_QUOTES);
        ObjectMapper mapper = new ObjectMapper(factory);
        InputStream resource = null;
        JsonNode json = null;
        try {
            json = mapper.readTree(data);
        } catch (IOException e) {
            System.out.print( e);
        }
        return json;
    }

}
