package com.cassiano.collagefm.Service;

import com.cassiano.collagefm.util.PropertiesUtil;

public class LastFmService {
    public static String getLastFmApiRoot(){
        return PropertiesUtil.getProperties("LASTFM_API_ROOT").toString();
    }
    public static String getLastFmKey(){
        return PropertiesUtil.getProperties("LASTFM_API_KEY").toString();
    }
}
