package com.plan.todo.controller;

import com.plan.todo.dto.ResponseDTO;
import com.plan.todo.dto.TestRequestBodyDTO;
import com.plan.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    TodoService service;

    @GetMapping()
    public String testController() {

        service.testService();
        return "Hello World!";
    }
    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello World! ID " + id;
    }
    @GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id){
        return "Hello World! ID " + id;
    }

    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "Hello World! ID " + testRequestBodyDTO.getId() + "Message : " + testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseDTO")
    public ResponseDTO<String> testResponseDTO() {
        List<String> list = new ArrayList<>();
        list.add("Hello ~~~");
        ResponseDTO<String> response = ResponseDTO
                                            .<String>builder()
                                            .data(list)
                                            .build();
        return response;
    }

    //ResponseEntity : HTTP response body, status, header 등등을 조작하고 싶을 때 사용
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("gello~~~~~~");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        //return ResponseEntity.badRequest().body(response);
        return ResponseEntity.ok().body(response);
    }

}
