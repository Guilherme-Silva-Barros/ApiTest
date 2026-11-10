package com.ApiTeste.apiTest.view;


import com.ApiTeste.apiTest.dto.DTO;
import com.ApiTeste.apiTest.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

    private final ApiService apiService;
    public ViewController(ApiService apiService){
        this.apiService = apiService;
    }
    @GetMapping("/")
    public String view(@RequestParam(required = false) Long valor,
                       @RequestParam(required = false) String tipo,
                       Model model){

        List<DTO>posts;
        if(valor == null){
            posts = apiService.listAll();
        }else if("id".equals(tipo)){
            try{
                posts = List.of(apiService.listById(valor));
            }catch (Exception e){
                posts = List.of();
            }
        }else {
            posts = apiService.listPostByUserId(valor);
        }
        model.addAttribute("posts", posts);
        model.addAttribute("tipo", tipo);
        model.addAttribute("valor", valor);
        return "posts";
    }
}
