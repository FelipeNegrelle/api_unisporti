package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.model.PollResponse;
import com.unisporti.api_unisporti.repository.PollResponseRepository;
import com.unisporti.api_unisporti.vo.PollResponseVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollResponseService {
    private final PollResponseRepository pollResponseRepository;

    public PollResponseService(PollResponseRepository pollResponseRepository) {
        this.pollResponseRepository = pollResponseRepository;
    }

    public List<PollResponseVO> findAll() {
        final List<PollResponse> pollResponses = pollResponseRepository.findAll();

        return pollResponses.stream().map(pr -> new PollResponseVO(pr.getIdPollResponse(), pr.getUser().getIdUser(), pr.getPoll().getIdPoll(), pr.getResponseDateTime())).toList();
    }
}
