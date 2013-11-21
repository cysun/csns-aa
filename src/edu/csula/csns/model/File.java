package edu.csula.csns.model;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DOWNLOAD_URL = "http://csns.calstatela.edu/download?fileId=";

    private long id;

    private String name;

    private long size;

    private Date date;

    public File()
    {
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

    public long getSize()
    {
        return size;
    }

    public void setSize( long size )
    {
        this.size = size;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

}
