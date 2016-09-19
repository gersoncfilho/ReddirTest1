package com.developer.gerson.reddirtest1.Util;

/**
 * Created by gerso on 18/09/2016.
 */

public class Post {
    String subreddit;
    String title;
    String author;
    int points;
    int numComments;
    String permalink;
    String url;
    String domain;
    String id;

    public String getDetails()
    {
        String details = author + "postou isso e teve " + numComments + " replies";
        return details;
    }

    public String getTitle()
    {
        return title;
    }

    public String getScore()
    {
        return Integer.toString(points);
    }
}
