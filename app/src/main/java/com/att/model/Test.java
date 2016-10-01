package com.att.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avdhesh AKhani on 9/30/2016.
 */

public class Test {

    private Integer totalPages;
    private Integer page;
    private ArrayList<Post> posts = new ArrayList<Post>();



    /**
     *
     * @return
     * The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     *
     * @param totalPages
     * The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The posts
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}
