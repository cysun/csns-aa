package edu.csula.csns.model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String title, content, author;

    private Date publishDate, expireDate;

    public News()
    {
    }

    @Override
    public String toString()
    {
        return title;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }

    public void setPublishDate( Date publishDate )
    {
        this.publishDate = publishDate;
    }

    public Date getExpireDate()
    {
        return expireDate;
    }

    public void setExpireDate( Date expireDate )
    {
        this.expireDate = expireDate;
    }

}
