package com.cassiano.collagefm.controllers;

import com.cassiano.collagefm.Service.CollageFmService;
import com.cassiano.collagefm.domain.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CollageFmController {

    private final CollageFmService collageFmService;

    @Autowired
    public CollageFmController(CollageFmService collageFmService){
        this.collageFmService  = collageFmService;
    }

    @PostMapping(path = "api/collage")
    public ResponseEntity<String> generateCollage(@RequestBody UserRequest userRequest){
        return collageFmService.getTopAlbums(userRequest);
    }

}
