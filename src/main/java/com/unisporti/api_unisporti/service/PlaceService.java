package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.model.Place;
import com.unisporti.api_unisporti.repository.PlaceRepository;
import com.unisporti.api_unisporti.vo.PlaceVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public PlaceVO create(PlaceVO place) throws Exception {
        if (place != null) {
            final Place entity = new Place();
            entity.setName(place.getName());
            entity.setMaxCapacity(place.getMaxCapacity());

            final Place p = placeRepository.save(entity);

            return new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive());
        } else {
            throw new Exception("Local não pode ser nulo.");
        }
    }

    public PlaceVO update(PlaceVO place) throws Exception {
        if (place != null) {
            final Place entity = placeRepository.findById(place.getIdPlace()).orElseThrow(() -> new Exception("Local não encontrado."));
            entity.setName(place.getName());
            entity.setMaxCapacity(place.getMaxCapacity());
            entity.setActive(place.getActive());

            final Place p = placeRepository.save(entity);

            return new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive());
        } else {
            throw new Exception("Local não pode ser nulo.");
        }
    }

    public List<PlaceVO> findAll() {
        final List<Place> places = placeRepository.findAll();

        return places.stream().map(p -> new PlaceVO(p.getIdPlace(), p.getName(), p.getMaxCapacity(), p.getActive())).toList();
    }


    public PlaceVO findById(Integer id) throws Exception {
        if (id != null) {
            final Place entity = placeRepository.findById(id).orElseThrow(() -> new Exception("Local não encontrado."));

            return new PlaceVO(entity.getIdPlace(), entity.getName(), entity.getMaxCapacity(), entity.getActive());
        } else {
            throw new Exception("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Place entity = placeRepository.findById(id).orElseThrow(() -> new Exception("Local não encontrado."));
            placeRepository.delete(entity);

            return true;
        } else {
            throw new Exception("Id não pode ser nulo.");
        }
    }
}
