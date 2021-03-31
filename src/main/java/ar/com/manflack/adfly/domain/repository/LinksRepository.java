package ar.com.manflack.adfly.domain.repository;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import ar.com.manflack.adfly.domain.exception.LinkException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class LinksRepository
{
    private HashMap<String, LinkDTO> repository = new HashMap<>();

    public LinkDTO getByLinkId(String linkId, boolean updateVisitas) throws LinkException
    {
        LinkDTO linkDTO = repository.get(linkId);
        if (linkDTO == null)
            throw new LinkException(LinkException.LINK_NOT_FOUND, LinkException.LINK_NOT_FOUND_MSG);
        if (updateVisitas)
            linkDTO.setVisitas(linkDTO.getVisitas() + 1);
        repository.put(linkId, linkDTO);
        return linkDTO;
    }

    public LinkDTO saveAndFlush(LinkDTO linkDTO) throws LinkException
    {
        if (repository.get(linkDTO.getId()) != null)
            throw new LinkException(LinkException.ID_ALREADY_EXISTS, LinkException.ID_ALREADY_EXISTS_MSG);
        repository.put(linkDTO.getId(), linkDTO);
        return linkDTO;
    }

    public void removeByLinkId(String linkId) throws LinkException
    {
        getByLinkId(linkId, false);
        repository.remove(linkId);
    }
}
