package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.service.PlaceService;
import com.unisporti.api_unisporti.vo.PlaceVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/place")
@CrossOrigin(origins = "*")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public PlaceVO create(@RequestBody PlaceVO place) {
        try {
            return placeService.create(place);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping
    @CrossOrigin
    public PlaceVO update(@RequestBody PlaceVO place) {
        try {
            return placeService.update(place);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping
    @CrossOrigin
    public List<PlaceVO> findAll() {
        try {
            return placeService.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public PlaceVO findById(@PathVariable Integer id) {
        try {
            return placeService.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return placeService.delete(id);
        } catch (Exception e) {
            return null;
        }
    }
}
