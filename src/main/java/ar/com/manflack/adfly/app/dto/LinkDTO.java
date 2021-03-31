package ar.com.manflack.adfly.app.dto;

import lombok.Data;

@Data
public class LinkDTO
{
    private String url;
    private String id;
    private Integer visitas;
    private String password;

    public LinkDTO()
    {
        this.visitas = 0;
    }

    public LinkDTO(String url, String id, Integer visitas)
    {
        this.url = url;
        this.id = id;
        this.visitas = visitas;
    }
}
