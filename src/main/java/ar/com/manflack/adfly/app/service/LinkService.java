package ar.com.manflack.adfly.app.service;

import ar.com.manflack.adfly.app.dto.LinkDTO;

public interface LinkService
{
    LinkDTO generateLink(String url);
}
