package com.cassiano.collagefm.Service;

import com.cassiano.collagefm.domain.Album;
import com.cassiano.collagefm.domain.UserRequest;
import com.cassiano.collagefm.util.Imagens;
import com.cassiano.collagefm.util.JsonHandler;
import com.cassiano.collagefm.util.PropertiesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class CollageFmService {


    public static String getLastFmApiRoot(){
        return PropertiesUtil.getProperties("LASTFM_API_ROOT").toString();
    }
    public static String getLastFmKey(){
        return PropertiesUtil.getProperties("LASTFM_API_KEY").toString();
    }

    public ResponseEntity<String> getTopAlbums(UserRequest user){
        var method = "?method=user.gettopalbums&";
        var format = "&format=json";
        var uri = getLastFmApiRoot()+method+format+"&api_key="+ getLastFmKey()+user.getFormatedParams();
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(uri);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            if(response.getStatusCode() == HttpStatus.OK){
                ArrayList<Album> albunsList = JsonHandler.jsonAlbuns(response.getBody());
                String caminho = Imagens.generateCollage(albunsList, user.getUsername());

            }
            return ResponseEntity.ok().build();

        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O exemplo não é válido");
        }

    }
}
