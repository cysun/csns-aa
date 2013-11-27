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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import edu.csula.csns.model.Department;
import edu.csula.csns.model.News;
import edu.csula.csns.model.User;

public class WebServiceClient {

    private static final String BASE_URL = "http://csns.calstatela.edu/service";
 // private static final String BASE_URL = "http://10.0.2.2:8080/csns2/service";

    public static HttpURLConnection getConnection( String url )
        throws IOException
    {

        HttpURLConnection connection = (HttpURLConnection) (new URL( url )).openConnection();
        connection.setConnectTimeout( 10000 );
        connection.setReadTimeout( 10000 );
        return connection;
    }

    public static HttpURLConnection getConnection( String url,
        Map<String, String> params ) throws IOException
    {
        StringBuffer sb = new StringBuffer( url );

        boolean isFirst = true;
        for( String key : params.keySet() )
        {
            if( isFirst )
            {
                sb.append( "?" );
                isFirst = false;
            }
            else
                sb.append( "&" );

            sb.append( URLEncoder.encode( key, "UTF-8" ) )
                .append( "=" )
                .append( URLEncoder.encode( params.get( key ), "UTF-8" ) );
        }

        return getConnection( sb.toString() );
    }

    public static User login( String username, String password )
    {
        String url = BASE_URL + "/user/login";

        User user = null;
        HttpURLConnection connection = null;
        try
        {
            Map<String, String> params = new HashMap<String, String>();
            params.put( "username", username );
            params.put( "password", password );
            connection = getConnection( url, params );
            user = JsonUtils.readUser( connection.getInputStream() );
        }
        catch( IOException e )
        {
            Log.e( "WebServiceClient", "login(String,String) error.", e );
        }
        finally
        {
            if( connection != null ) connection.disconnect();
        }

        return user;
    }

    public static List<Department> getDepartments()
    {
        String url = BASE_URL + "/department/list";

        List<Department> departments = null;
        HttpURLConnection connection = null;
        try
        {
            connection = getConnection( url );
            departments = JsonUtils.readDepartments( connection.getInputStream() );
        }
        catch( IOException e )
        {
            Log.e( "WebServiceClient", "getDepartments() error.", e );
        }
        finally
        {
            if( connection != null ) connection.disconnect();
        }

        return departments;
    }

    public static List<News> getNewses( String dept )
    {
        String url = BASE_URL + "/news/" + dept;

        List<News> newses = null;
        HttpURLConnection connection = null;
        try
        {
            connection = getConnection( url );
            newses = JsonUtils.readNewses( connection.getInputStream() );
        }
        catch( IOException e )
        {
            Log.e( "WebServiceClient", "getNewses(String) error.", e );
        }
        finally
        {
            if( connection != null ) connection.disconnect();
        }

        return newses;
    }

}
