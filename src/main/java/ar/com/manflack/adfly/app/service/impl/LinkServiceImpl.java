package ar.com.manflack.adfly.app.service.impl;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.app.service.LinkService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

public class LinkServiceImpl implements LinkService
{
    @Override
    public LinkDTO generateLink(String url)
    {
        String linkId = RandomStringUtils.random(10, true, false);



        return null;
    }
}
