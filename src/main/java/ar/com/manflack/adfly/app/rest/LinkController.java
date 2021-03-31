package ar.com.manflack.adfly.app.rest;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.domain.exception.LinkException;
import ar.com.manflack.adfly.domain.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController
{
    @Autowired
    LinkService linkService;

    @PostMapping(path = "/generateLink", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity generateLink(@RequestBody LinkDTO link, @RequestParam(required = false) String password) throws LinkException
    {
        link.setPassword(String.valueOf(link.getPassword().hashCode()));
        LinkDTO response = linkService.generateLink(link);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(path = "/link/{linkId}")
    public ModelAndView redirByLinkId(@PathVariable("linkId") String linkId) throws LinkException
    {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(linkId);
        String url = linkService.getLinkDTOByLinkId(linkDTO, true).getUrl();
        return new ModelAndView("redirect:" + url);
    }

    @GetMapping(path = "/metrics/{linkId}")
    public ResponseEntity getMetrics(@PathVariable("linkId") String linkId) throws LinkException
    {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(linkId);
        LinkDTO response = linkService.getLinkDTOByLinkId(linkDTO, false);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(path = "/invalidate/{linkId}")
    public ResponseEntity invalidateLink(@PathVariable("linkId") String linkId) throws LinkException
    {
        linkService.invalidateLink(linkId);
        return new ResponseEntity("Se ha eliminado correctamente el link", HttpStatus.OK);
    }


}
