package com.cassiano.collagefm.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cassiano.collagefm.domain.UserRequest;

@CrossOrigin
@RestController
public class CollageFmController {

    @RequestMapping(path="/collage", method = RequestMethod.POST)
    public String hello(@RequestBody UserRequest userRequest){

        return LastFmController.getTopAlbums(userRequest);
    }
}
