package ar.com.manflack.adfly.app.repository;

import ar.com.manflack.adfly.app.dto.LinkDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class LinksRepository
{
    private HashMap<String, LinkDTO> repository = new HashMap<>();

    public LinkDTO getByLinkId(String linkId)
    {
        LinkDTO linkDTO = repository.get(linkId);
        linkDTO.setVisitas(linkDTO.getVisitas() + 1);
        repository.put(linkId, linkDTO);
        return linkDTO;
    }

    public LinkDTO saveAndFlush(LinkDTO linkDTO)
    {
        repository.put(linkDTO.getId(), linkDTO);
        return linkDTO;
    }
}
