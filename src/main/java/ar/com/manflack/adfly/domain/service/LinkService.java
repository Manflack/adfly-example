package ar.com.manflack.adfly.domain.service;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.domain.exception.LinkException;

public interface LinkService
{
    LinkDTO generateLink(LinkDTO url) throws LinkException;

    LinkDTO getLinkDTOByLinkId(LinkDTO link, boolean updateVisitas) throws LinkException;

    void invalidateLink(String linkId) throws LinkException;
}
