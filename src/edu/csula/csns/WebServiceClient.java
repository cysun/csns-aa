package edu.csula.csns;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csula.csns.model.Department;

public class WebServiceClient {

    private static final String baseUrl = "http://10.0.2.2:8080/csns2/service";

    private static final ObjectMapper mapper = new ObjectMapper();

    private static WebServiceClient wsClient;

    private WebServiceClient()
    {
    }

    public static WebServiceClient getInstance()
    {
        if( wsClient == null ) wsClient = new WebServiceClient();

        return wsClient;
    }

    public List<Department> getDepartments()
    {
        List<Department> departments = null;
        HttpURLConnection urlConnection = null;

        try
        {
            URL url = new URL( baseUrl + "/department/list" );
            urlConnection = (HttpURLConnection) url.openConnection();
            Map<String, List<Department>> result = mapper.readValue(
                urlConnection.getInputStream(),
                new TypeReference<Map<String, List<Department>>>() {
                } );
            departments = result.get( "departments" );
        }
        catch( Exception e )
        {
            Log.w( "WebServiceClient", e );
        }
        finally
        {
            if( urlConnection != null ) urlConnection.disconnect();
        }

        return departments;
    }

}
