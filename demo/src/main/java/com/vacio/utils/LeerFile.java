package com.vacio.utils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class LeerFile {

    public JsonNode GetJsonNode(String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String json = Files.readString(Path.of(path));
        JsonNode root = mapper.readTree(json);
        return root;
    }

}
