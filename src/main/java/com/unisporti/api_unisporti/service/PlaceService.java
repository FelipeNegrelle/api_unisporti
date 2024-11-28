package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Place;
import com.unisporti.api_unisporti.repository.PlaceRepository;
import com.unisporti.api_unisporti.vo.PlaceVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    private Optional<Map<String, String>> validate(PlaceVO placeVO) {
        final Map<String, String> errors = new HashMap<>();

        if (placeVO.getMaxCapacity() == null || placeVO.getMaxCapacity() <= 0) {
            errors.put("maxCapacity", "A capacidade máxima tem que ser maior que 0.");
        }

        if (placeVO.getIdPlace() != null && placeRepository.existsPlaceByNameAndIdPlaceIsNot(placeVO.getName(), placeVO.getIdPlace())) {
            errors.put("name", "Lugar com este nome já esta cadastrado.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public PlaceVO create(PlaceVO place) throws Exception {
        if (place != null) {
            final Map<String, String> errors = validate(place).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Place entity = new Place();
                entity.setName(place.getName());
                entity.setMaxCapacity(place.getMaxCapacity());

                final Place p = placeRepository.save(entity);

                return new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Local não pode ser nulo.");
        }
    }

    public PlaceVO update(PlaceVO place) throws Exception {
        if (place != null) {
            final Map<String, String> errors = validate(place).orElse(new HashMap<>());
            if (errors.isEmpty()) {

                final Place entity = placeRepository.findById(place.getIdPlace()).orElseThrow(() -> new NotFoundException("Local não encontrado."));
                entity.setName(place.getName());
                entity.setMaxCapacity(place.getMaxCapacity());
                entity.setActive(place.getActive());

                final Place p = placeRepository.save(entity);

                return new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Local não pode ser nulo.");
        }
    }

    public List<PlaceVO> findAll() {
        final List<Place> places = placeRepository.findAll();

        return places.stream().map(p -> new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive())).toList();
    }


    public PlaceVO findById(Integer id) throws Exception {
        if (id != null) {
            final Place entity = placeRepository.findById(id).orElseThrow(() -> new NotFoundException("Local não encontrado."));

            return new PlaceVO(entity.getIdPlace(), entity.getName(), entity.getMaxCapacity(), entity.getActive());
        } else {
            throw new NotFoundException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Place entity = placeRepository.findById(id).orElseThrow(() -> new NotFoundException("Local não encontrado."));
            placeRepository.delete(entity);

            return true;
        } else {
            throw new NotFoundException("Id não pode ser nulo.");
        }
    }
}
