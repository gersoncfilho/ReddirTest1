package com.developer.gerson.reddirtest1.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerso on 18/09/2016.
 */

public class PostsHolder {
    private final String URL_TEMPLATE = "http://www.reddit.com/r/SUBREDDIT_NAME/" + ".json" + "?after=AFTER";
    String subreddit;
    String url;
    String after;

    public PostsHolder(String sr)
    {
        subreddit = sr;
        after="";
        generateURL();
    }

    private void generateURL()
    {
        url = URL_TEMPLATE.replace("SUBREDDIT_NAME", subreddit);
        url = url.replace("AFTER", after);
    }

    public List<Post> fetchPosts()
    {
        String raw = RemoteData.readContents(url);
        List<Post> list = new ArrayList<Post>();
        try{
            JSONObject data = new JSONObject(raw).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            //Using this property we can fetch the next set of posts from the same subreddit
            after = data.getString("after");
            for(int i=0;i<children.length();i++)
            {
                JSONObject cur = children.getJSONObject(i).getJSONObject("data");
                Post p = new Post();
                p.title = cur.optString("title");
                p.url = cur.optString("url");
                p.numComments = cur.optInt("num_comments");
                p.points = cur.optInt("score");
                p.author = cur.optString("author");
                p.subreddit = cur.optString("subreddit");
                p.permalink = cur.optString("permalink");
                p.domain = cur.optString("domain");
                p.id = cur.optString("id");
                if (p.title != null)
                {
                    list.add(p);
                }
            }
        }catch(Exception ex){
            Log.e("fetchPosts()", ex.toString());
        }
        return list;
    }

    //this is to fetch the next set of posts useing the 'after' property @return
    List<Post> fetchMorePosts()
    {
        generateURL();
        return fetchPosts();
    }
}
