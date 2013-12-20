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
package edu.csula.csns.model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    String accessKey;

    String firstName, lastName;

    String username;

    public User()
    {
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName + ", " + username + ", " + accessKey;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey( String accessKey )
    {
        this.accessKey = accessKey;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

}
