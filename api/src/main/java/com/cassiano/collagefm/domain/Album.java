package com.cassiano.collagefm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Album {
    
    private String albumName;
    private String artistName;
    private String coverImage;
    private String albumUrl;
    //private BufferedImage infoImage;

}
