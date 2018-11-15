package com.example;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/index", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE, 
        method = {RequestMethod.GET, RequestMethod.POST})
public class RestFulController {

    //Get method
    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public String helloGet(@PathVariable String name) {
        return "Hello " + name + ", have a good day";
    }

    //Post method
    @RequestMapping(value = "/post/noParam", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost() {
        return "have a good day";
    }
    
    //Post method (param)
    @RequestMapping(value = "/post/withParam", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRequest helloPostReq(@RequestBody ObjectRequest request) {
        ObjectRequest objectRequest = new ObjectRequest();
        objectRequest.setName(request.getName()+" Response");
        objectRequest.setValue(request.getValue()+" Response");
        
        return objectRequest;
    }
}
