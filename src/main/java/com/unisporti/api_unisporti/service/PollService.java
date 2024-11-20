package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.config.Util;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Poll;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.repository.PollRepository;
import com.unisporti.api_unisporti.vo.PollVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;

    private final UserContext context = UserContext.getCurrentUser();

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    private Optional<Map<String, String>> validate(PollVO poll) {
        final Map<String, String> errors = new HashMap<>();

        if (poll.getIdPoll() > 0 && pollRepository.countPollByNameAndIdPollIsNot(poll.getName(), poll.getIdPoll()) > 1) {
            errors.put("name", "Enquete já cadastrada.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public PollVO create(PollVO poll) throws Exception {
        if (poll != null) {
            final Map<String, String> errors = validate(poll).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Poll entity = new Poll();
                entity.setName(poll.getName());
                entity.setCreatedAt(poll.getCreatedAt());
                entity.setUpdatedAt(poll.getUpdatedAt());
                entity.setActive(poll.getActive());

                final Poll p = pollRepository.save(entity);

                return new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Enquete não pode ser nula.");
        }
    }

    public PollVO update(PollVO poll) throws Exception {
        if (poll != null) {
            final Map<String, String> errors = validate(poll).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Poll entity = pollRepository.findById(poll.getIdPoll()).orElseThrow(() -> new NotFoundException("Enquete não encontrada."));
                entity.setName(poll.getName());
                entity.setCreatedAt(poll.getCreatedAt());
                entity.setUpdatedAt(poll.getUpdatedAt());
                entity.setActive(poll.getActive());

                final Poll p = pollRepository.save(entity);

                return new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Enquete não pode ser nula.");
        }
    }

    public List<PollVO> findAll() {
        final List<Poll> polls = pollRepository.findAll();

        return polls.stream().map(p -> new PollVO(p.getIdPoll(), p.getName(), p.getCreatedAt(), p.getUpdatedAt(), p.getActive())).toList();
    }

    public PollVO findById(Integer id) {
        if (id != null) {
            final Poll entity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Enquete não encontrada."));

            return new PollVO(entity.getIdPoll(), entity.getName(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Poll entity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Enquete não encontrada."));

            pollRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}