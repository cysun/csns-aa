package edu.csula.csns;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csula.csns.model.Department;
import edu.csula.csns.model.News;
import edu.csula.csns.model.User;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static User readUser( InputStream in )
    {
        User user = null;
        try
        {
            Map<String, User> result = objectMapper.readValue( in,
                new TypeReference<Map<String, User>>() {
                } );
            user = result.get( "user" );

            Log.d( "JsonUtils", "User: " + user.getUsername() );
        }
        catch( Exception e )
        {
            Log.e( "JsonUtils", "readUser(InputStream) error.", e );
        }
        return user;
    }

    public static List<Department> readDepartments( InputStream in )
    {
        List<Department> departments = null;
        try
        {
            Map<String, List<Department>> result = objectMapper.readValue( in,
                new TypeReference<Map<String, List<Department>>>() {
                } );
            departments = result.get( "departments" );

            Log.d( "JsonUtils", "Departments:" );
            for( Department department : departments )
                Log.d( "JsonUtils", "    " + department.getName() );
        }
        catch( Exception e )
        {
            Log.e( "JsonUtils", "readDepartments(InputStream) error.", e );
        }
        return departments;
    }

    public static List<News> readNewses( InputStream in )
    {
        List<News> newses = null;
        try
        {
            Map<String, List<News>> result = objectMapper.readValue( in,
                new TypeReference<Map<String, List<News>>>() {
                } );
            newses = result.get( "newses" );

            Log.d( "JsonUtils", "Newses:" );
            for( News news : newses )
                Log.d( "JsonUtils", "    " + news.getTitle() );
        }
        catch( Exception e )
        {
            Log.e( "JsonUtils", "readNewses(InputStream) error.", e );
        }
        return newses;
    }

    public static List<News> readNewses( String s )
    {
        List<News> newses = null;
        try
        {
            newses = objectMapper.readValue( s,
                new TypeReference<List<News>>() {
                } );

            Log.d( "JsonUtils", "Newses:" );
            for( News news : newses )
                Log.d( "JsonUtils", "    " + news.getTitle() );
        }
        catch( Exception e )
        {
            Log.e( "JsonUtils", "readNewses(String) error.", e );
        }
        return newses;
    }

    public static String writeNewses( List<News> newses )
    {
        String s = null;
        try
        {
            s = objectMapper.writeValueAsString( newses );
            Log.d( "Newses2Json", s );
        }
        catch( JsonProcessingException e )
        {
            Log.e( "JsonUtils", "writeNewses(List<News>) error.", e );
        }
        return s;
    }

}
