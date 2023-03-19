package com.cassiano.collagefm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    
    public static Object getProperties(String key){
        var properties = new Properties();
        InputStream configFile;

            try {
                configFile = new FileInputStream(new File("C:\\Users\\cassiano\\Desktop\\CollageFMSpringReact\\api\\src\\main\\java\\com\\cassiano\\collagefm\\properties\\application.properties"));
                properties.load(configFile);
                var value = properties.get(key);
                configFile.close();
                return value;

            } catch (FileNotFoundException e) {
                System.out.println("arquivo property nao encontrado");
                return null;
                
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
    }
}
