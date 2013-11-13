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
