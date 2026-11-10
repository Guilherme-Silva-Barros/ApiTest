package com.ApiTeste.apiTest.service;

import com.ApiTeste.apiTest.exceptions.PostNotFoundException;
import com.ApiTeste.apiTest.dto.DTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ApiService {

    private final RestClient restClient;
    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    //Listar todos os Posts da API
    public List<DTO> listAll(){
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<DTO>>() {});

    }
    //Mostrar um post a partir do seu Id específico
    public DTO listById(Long id){
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(status ->status.value()==404,(request, response) ->{
                    throw new PostNotFoundException(id);
                        })
                .body(DTO.class);
    }
    //Listar todos os posts por ID de usuário
    public List<DTO>listPostByUserId(Long userId){
        return restClient.get()
                .uri(uri ->uri.path("/posts")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<DTO>>() {});
    }

}
