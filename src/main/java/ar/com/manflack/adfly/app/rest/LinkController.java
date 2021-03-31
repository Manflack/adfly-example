package ar.com.manflack.adfly.app.rest;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.app.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LinkController
{
    LinkService linkService;

    @PostMapping
    public ResponseEntity generateLink(@RequestBody LinkDTO link)
    {
        LinkDTO response = linkService.generateLink(link.getUrl());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
