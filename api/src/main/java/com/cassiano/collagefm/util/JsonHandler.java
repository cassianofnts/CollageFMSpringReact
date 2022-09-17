package com.cassiano.collagefm.util;
import java.util.ArrayList;
import java.util.Iterator;

import com.cassiano.collagefm.domain.Album;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHandler {
    public static ArrayList<Album> jsonAlbuns(String body){
        ArrayList<Album> listaDeAlbuns = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        
        JsonNode JsonBody;
        try {
            JsonBody = objectMapper.readTree(body);
            JsonNode topAlbunsNode = JsonBody.path("topalbums");
            JsonNode albunsNode   = topAlbunsNode.path("album");
    
            Iterator<JsonNode> albumList = albunsNode.elements();
    
            while(albumList.hasNext()){
    
                JsonNode albumNode = albumList.next();
                var albumName = albumNode.get("name").textValue();
                var albumUrl = albumNode.get("url").textValue();
                
                JsonNode artistNode = albumNode.get("artist");
                var artistName = artistNode.get("name").textValue();
    
                JsonNode imageNode  = albumNode.get("image");
                Iterator<JsonNode> imageList = imageNode.elements();
                
                var coverImage = "";

                 while(imageList.hasNext()){
                    JsonNode image = imageList.next();
                    if(image.get("size").textValue().equalsIgnoreCase("extralarge"))
                        coverImage = image.get("#text").textValue();
                }

                Album album = new Album(albumName, artistName, coverImage, albumUrl);

                listaDeAlbuns.add(album);
            }
            return listaDeAlbuns;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
   }
}
