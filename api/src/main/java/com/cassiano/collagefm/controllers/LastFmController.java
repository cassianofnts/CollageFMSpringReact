package com.cassiano.collagefm.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cassiano.collagefm.Service.LastFmService;
import com.cassiano.collagefm.domain.Album;
import com.cassiano.collagefm.domain.UserRequest;
import com.cassiano.collagefm.util.Imagens;
import com.cassiano.collagefm.util.JsonHandler;

@RestController
public class LastFmController {

    @GetMapping
    public static String getTopAlbums(UserRequest user){
        var method = "?method=user.gettopalbums&";
        var format = "&format=json";
        var uri = LastFmService.getLastFmApiRoot()+method+format+"&api_key="+LastFmService.getLastFmKey()+user.getFormatedParams();
        RestTemplate restTemplate = new RestTemplate();

        var jsonResponse = restTemplate.getForEntity(uri, String.class);

        ArrayList<Album> albunsList = JsonHandler.jsonAlbuns(jsonResponse.getBody());

        Imagens.generateCollage(albunsList, user.getUsername());
        return "file";
    }
}
