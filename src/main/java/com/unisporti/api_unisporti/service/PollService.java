package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.model.Poll;
import com.unisporti.api_unisporti.repository.PollRepository;
import com.unisporti.api_unisporti.vo.PollVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public PollVO create(PollVO poll) throws Exception {
        if (poll != null) {
            final Poll entity = new Poll();
            entity.setName(poll.getName());
            entity.setCreatedAt(poll.getCreatedAt());
            entity.setUpdatedAt(poll.getUpdatedAt());
            entity.setActive(poll.getActive());

            final Poll p = pollRepository.save(entity);

            return new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive());
        } else {
            throw new Exception("Enquete não pode ser nula.");
        }
    }

    public PollVO update(PollVO poll) throws Exception {
        if (poll != null) {
            final Poll entity = pollRepository.findById(poll.getIdPoll()).orElseThrow(() -> new Exception("Enquete não encontrada."));
            entity.setName(poll.getName());
            entity.setCreatedAt(poll.getCreatedAt());
            entity.setUpdatedAt(poll.getUpdatedAt());
            entity.setActive(poll.getActive());

            final Poll p = pollRepository.save(entity);

            return new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive());
        } else {
            throw new Exception("Enquete não pode ser nula.");
        }
    }

    public List<PollVO> findAll() {
        final List<Poll> polls = pollRepository.findAll();

        return polls.stream().map(p -> new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive())).toList();
    }

    public PollVO findById(Integer id) throws Exception {
        if (id != null) {
            final Poll entity = pollRepository.findById(id).orElseThrow(() -> new Exception("Enquete não encontrada."));

            return new PollVO(entity.getIdPoll(), entity.getName(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getActive());
        } else {
            throw new Exception("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Poll entity = pollRepository.findById(id).orElseThrow(() -> new Exception("Enquete não encontrada."));

            pollRepository.delete(entity);

            return true;
        } else {
            throw new Exception("Id não pode ser nulo.");
        }
    }
}