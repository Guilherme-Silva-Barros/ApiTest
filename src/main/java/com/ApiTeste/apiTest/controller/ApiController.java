package com.ApiTeste.apiTest.controller;

import com.ApiTeste.apiTest.dto.DTO;
import com.ApiTeste.apiTest.dto.NewDTO;
import com.ApiTeste.apiTest.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    @Operation(
            summary = "Lista de todos os posts de forma geral ou por por meio do userId",
            description = "Endpoint responsável por mostrar todos os posts da API, sejam de forma geral ou filtrada por userId")
    public List<DTO> listar(@RequestParam(required = false) Long userId) {
        return userId == null ? apiService.listAll():apiService.listPostByUserId(userId);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "mostrar o posts a partir do seu id",
            description = "responsável por mostrar o posts a partir do Id fornecido no corpo da requisição")
    public DTO listId(@PathVariable Long id){
        return apiService.listById(id);
    }
    @GetMapping("/{id}/comments")
    public List<NewDTO>coments(@PathVariable Long id){
        return apiService.findComentyById(id);
    }

}
