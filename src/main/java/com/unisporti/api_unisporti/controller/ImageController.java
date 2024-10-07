package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.ImageService;
import com.unisporti.api_unisporti.vo.ImageVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    public ImageVO create(ImageVO image) {
        try {
            return imageService.create(image);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar imagem " + e.getMessage());
        }
    }

    public ImageVO update(ImageVO image) {
        try {
            return imageService.update(image);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar imagem " + e.getMessage());
        }
    }

    public ImageVO findById(Integer id) {
        try {
            return imageService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar imagem " + e.getMessage());
        }
    }

    public Boolean delete(Integer id) {
        try {
            return imageService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar imagem " + e.getMessage());
        }
    }
}
