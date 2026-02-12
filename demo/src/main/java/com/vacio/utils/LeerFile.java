package com.vacio.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class LeerFile {

    public JsonNode GetJsonNode(String path) {
        JsonNode root= null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = Files.readString(Path.of(path));
            root = mapper.readTree(json);
        } catch(IOException e){
            System.out.println("Muy feo");
        }
        return root;
        
    }

}
