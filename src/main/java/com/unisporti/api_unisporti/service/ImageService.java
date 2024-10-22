package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.model.Image;
import com.unisporti.api_unisporti.repository.ImageRepository;
import com.unisporti.api_unisporti.vo.ImageVO;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageVO create(ImageVO image) throws Exception {
        if (image != null) {
            final Image entity = new Image();

            entity.setIdTable(image.getIdTable());
            entity.setTableName(image.getTableName());
            entity.setOrder(image.getOrdering());
            entity.setImage(image.getImage());
            entity.setActive(image.getActive());

            final Image i = imageRepository.save(entity);

            return new ImageVO(i.getIdImage(), i.getTableName(), i.getIdTable(), i.getOrder(), i.getImage(), i.getActive());
        } else {
            throw new MalformedRequestException("Imagem não pode ser nula.");
        }
    }

    public ImageVO update(ImageVO image) throws Exception {
        if (image != null) {
            final Image entity = imageRepository.findById(image.getIdImage()).orElseThrow(() -> new MalformedRequestException("Imagem não encontrada."));

            entity.setIdTable(image.getIdTable());
            entity.setTableName(image.getTableName());
            entity.setOrder(image.getOrdering());
            entity.setImage(image.getImage());
            entity.setActive(image.getActive());

            final Image i = imageRepository.save(entity);

            return new ImageVO(i.getIdImage(), i.getTableName(), i.getIdTable(), i.getOrder(), i.getImage(), i.getActive());
        } else {
            throw new MalformedRequestException("Imagem não pode ser nula.");
        }
    }

    public ImageVO findById(Integer id) throws Exception {
        if (id != null) {
            final Image entity = imageRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Imagem não encontrada."));

            return new ImageVO(entity.getIdImage(), entity.getTableName(), entity.getIdTable(), entity.getOrder(), entity.getImage(), entity.getActive());
        } else {
            throw new MalformedRequestException("Imagem não pode ser nula.");
        }
    }

    public Boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Image entity = imageRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Imagem não encontrada."));

            imageRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Imagem não pode ser nula.");
        }
    }
}
