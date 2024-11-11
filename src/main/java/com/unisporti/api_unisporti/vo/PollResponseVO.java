package com.unisporti.api_unisporti.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PollResponseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id_poll_response")
    private Integer idPollResponse;

    @JsonProperty("id_user")
    private Integer idUser;

    @JsonProperty("id_poll")
    private Integer idPoll;

    @JsonProperty("created_at")
    private Date createdAt = new Date();
}
