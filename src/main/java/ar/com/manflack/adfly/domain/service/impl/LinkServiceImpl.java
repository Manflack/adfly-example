package ar.com.manflack.adfly.domain.service.impl;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.domain.exception.LinkException;
import ar.com.manflack.adfly.domain.repository.LinksRepository;
import ar.com.manflack.adfly.domain.service.LinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService
{
    @Autowired
    LinksRepository repository;

    @Override
    public LinkDTO generateLink(LinkDTO link) throws LinkException
    {
        String url = link.getUrl();
        if (!urlValidator(url))
            throw new LinkException(LinkException.URL_NOT_VALID, LinkException.URL_NOT_VALID_MSG);

        String linkId = RandomStringUtils.random(10, true, false);
        LinkDTO linkDTO = new LinkDTO(url, linkId, 0);
        if (link.getPassword() != null)
            linkDTO.setPassword(link.getPassword());
        linkDTO = repository.saveAndFlush(linkDTO);

        return linkDTO;
    }

    @Override
    public LinkDTO getLinkDTOByLinkId(LinkDTO link, boolean updateVisitas) throws LinkException
    {
        LinkDTO response = repository.getByLinkId(link.getUrl(), updateVisitas);
        if (response.getPassword() != null && !response.getPassword().equals(String.valueOf(link.getPassword().hashCode())))
            throw new LinkException(LinkException.PASSWORD_INVALID, LinkException.PASSWORD_INVALID_MSG);
        return response;
    }

    @Override
    public void invalidateLink(String linkId) throws LinkException
    {
        repository.removeByLinkId(linkId);
    }

    public static boolean urlValidator(String url)
    {
        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }
}
