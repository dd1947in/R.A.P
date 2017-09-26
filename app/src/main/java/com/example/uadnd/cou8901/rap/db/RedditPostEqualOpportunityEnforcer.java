package com.example.uadnd.cou8901.rap.db;

import java.util.ArrayList;

/**
 * Created by dd2568 on 9/9/2017.
 */

public class RedditPostEqualOpportunityEnforcer {
    // policy enforced final list
    // This list will be added to  ContentProvider
    // ContentProvider will feed the recyclerview.
    private ArrayList<Post> masterPostList;
    private ArrayList<ArrayList<Post>> listOfLists;  // List of all subreddit posts

    private  int maxSizeOfAList;
    private int sizeOfListOfLists;

    public RedditPostEqualOpportunityEnforcer() {
        //Initilize all properties
        masterPostList = new ArrayList<Post>();
        listOfLists = new ArrayList<ArrayList<Post>>();
        maxSizeOfAList = 0;
        sizeOfListOfLists = 0;
    }
    public void addPostList(ArrayList<Post> arrayListOfPosts) {
        int sizeOfList = arrayListOfPosts.size();
        if (sizeOfList > 0) {
            listOfLists.add(arrayListOfPosts);

            sizeOfListOfLists = listOfLists.size();
            if (sizeOfList > maxSizeOfAList) {
                maxSizeOfAList = sizeOfList;
            }
        }


    }
    public ArrayList<Post> getMasterPostList() {
        // We will start with an empty master list
        masterPostList = new ArrayList<Post>();

        // Loop as many times as the biggest List Size
        for(int i = 0; i < maxSizeOfAList; i++) {

            // Loop through all lists picking i th item from each
            // subreddit post lists only if i th item exists
            for(int j = 0 ; j < sizeOfListOfLists; j++) {
                ArrayList<Post> alp = listOfLists.get(j);
                if(alp != null &&   alp.size() > i) {
                    Post post = alp.get(i);
                    masterPostList.add(post);
                }
            }
        }
        return masterPostList;
    }
}
