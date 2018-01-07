package com.spring_ballet.keep.CommonUtils;


public class ApiList {
    //启动图
    public static final String BingDayPic = "http://api.dujin.org/bing/1366.php";

    //movie
    public static final String DouBanTop250 = "http://api.douban.com/v2/movie/top250";
    public static final String MovieComingSoon = "http://api.douban.com/v2/movie/coming_soon";
    public static final String HotMovieList = "https://api.douban.com/v2/movie/in_theaters";
    public static final String UsBoxList = "https://api.douban.com/v2/movie/us_box";
    //后接电影id
    public static final String MovieItemInfo = "https://api.douban.com/v2/movie/subject/";
    public static final String TopMovieImageUrl = "http://ojyz0c8un.bkt.clouddn.com/one_01.png";
    //后接搜索内容
    public static final String DouBanMovieSearchUrl ="https://api.douban.com/v2/movie/search?q=";

    //book
    public static final String SearchBookUrl="https://api.douban.com/v2/book/search?tag=";
    public static final String[] BookSearchTags={"文学","文化","生活"};
}
