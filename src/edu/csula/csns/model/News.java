package edu.csula.csns.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String title, content, author;

    private Date publishDate, expireDate;

    private List<File> attachments;

    public News()
    {
    }

    @Override
    public String toString()
    {
        return title;
    }

    public String getContentWithAttachments()
    {
        StringBuffer sb = new StringBuffer(
            "<link rel='stylesheet' href='css/style.css'>" );

        sb.append( content );
        if( attachments != null && attachments.size() > 0 )
        {
            sb.append( "<div class='news-attachments'><b>Attachments</b><ul>" );
            for( File file : attachments )
                sb.append( "<li><a href='" )
                    .append( File.DOWNLOAD_URL )
                    .append( file.getId() )
                    .append( "'>" )
                    .append( file.getName() )
                    .append( "</a></li>" );
            sb.append( "</ul></div>" );
        }

        return sb.toString();
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

    public List<File> getAttachments()
    {
        return attachments;
    }

    public void setAttachments( List<File> attachments )
    {
        this.attachments = attachments;
    }

}
