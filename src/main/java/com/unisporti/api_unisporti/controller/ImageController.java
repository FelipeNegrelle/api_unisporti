package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.ImageService;
import com.unisporti.api_unisporti.vo.ImageVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ImageVO create(@RequestParam Map<String, String> body) {
        try {
            final ImageVO image = new ImageVO();
            image.setIdTable(Integer.parseInt(body.getOrDefault("id_table", "0")));
            image.setTableName(body.get("table_name"));
            image.setOrdering(Short.parseShort(body.getOrDefault("order", "0")));
            image.setImage(body.get("image").getBytes());
            image.setActive(Boolean.parseBoolean(body.getOrDefault("active", "true")));

            return imageService.create(image);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("Erro ao criar imagem " + e.getMessage());
        }
    }

    @PutMapping
    public ImageVO update(@RequestBody ImageVO image) {
        try {
            return imageService.update(image);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar imagem " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ImageVO findById(@PathVariable Integer id) {
        try {
            return imageService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar imagem " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return imageService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar imagem " + e.getMessage());
        }
    }
}
