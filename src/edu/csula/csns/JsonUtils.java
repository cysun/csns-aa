/*
 * This file is part of the CSNetwork Services Android App (CSNS-AA) project.
 * 
 * Copyright 2013, Chengyu Sun (csun@calstatela.edu).
 * 
 * CSNS-AA is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * CSNS-AA is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with CSNS-AA. If not, see http://www.gnu.org/licenses/gpl.html.
 */
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

            Log.d( "JsonUtils", "User: " + user );
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
