package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ImageVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_image")
    private Integer idImage;

    @JsonProperty("table_name")
    private String tableName;

    @JsonProperty("id_table")
    private Integer idTable;

    @JsonProperty("ordering")
    private Short ordering = 0;

    @JsonProperty("image")
    private byte[] image;

    @JsonProperty("active")
    private Boolean active = true;
}
