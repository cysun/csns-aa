package edu.csula.csns.model;

import java.io.Serializable;

public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    long id;

    String name;

    String abbreviation;

    public Department()
    {
    }

    @Override
    public String toString()
    {
        return name;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation( String abbreviation )
    {
        this.abbreviation = abbreviation;
    }

}
