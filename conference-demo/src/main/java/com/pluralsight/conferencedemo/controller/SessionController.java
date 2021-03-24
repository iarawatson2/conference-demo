package com.pluralsight.conferencedemo.controller;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions") //MAPEAMENTO DE SOLICITAÇÃO

public class SessionController {
    //quando um controler é construido, cria uma instância de sessões na classe (@Autowired)
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll(); //CONSULTA TODAS AS SESSÕES DO BD E RETORNA UMA LISTA DE OBJETOS DE SESSÕES.
     }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){ //OBTENDO SESSÃO POR ID
    return  sessionRepository.getOne(id);
   }

    @PostMapping
    public Session create (@RequestBody final Session session){ // LISTA PARA CRIAR
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)//EXCLUSÃO DE REGISTRO NO BD COM JPA
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method= RequestMethod.PUT) // ATUALIZAR
    public Session update(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession, "session_id"); //COPIA OS DADOS DA SESSÃO
        return sessionRepository.saveAndFlush(existingSession);
    }
}
