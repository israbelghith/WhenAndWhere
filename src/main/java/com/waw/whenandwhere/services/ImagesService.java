package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.waw.whenandwhere.entities.Activity;
import com.waw.whenandwhere.entities.Images;
import com.waw.whenandwhere.repositories.ActivityRepository;
import com.waw.whenandwhere.repositories.ImagesRepository;

@Service
public class ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Images addImage(MultipartFile file, Long activityId) {
        try {

            if (file.isEmpty()) {
                throw new IllegalArgumentException("Image file cannot be empty");
            }

            // Validate file size (e.g., max 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                throw new IllegalArgumentException("Image file size cannot exceed 5MB");
            }

            String contentType = file.getContentType();
            if (contentType == null || !isValidImageType(contentType)) {
                throw new IllegalArgumentException("Invalid image type. Only JPEG, PNG, GIF, and WEBP are allowed");
            }

            Optional<Activity> activity = activityRepository.findById(activityId);
            if (!activity.isPresent()) {
                throw new IllegalArgumentException("Activity with id " + activityId + " not found");
            }

            Images image = new Images();
            image.setImage(file.getBytes());
            image.setNameImage(file.getOriginalFilename());
            image.setTypeImage(contentType);
            image.setActivity(activity.get());

            return imagesRepository.save(image);

        } catch (Exception e) {
            throw new RuntimeException("Failed to store image: " + e.getMessage(), e);
        }
    }

    // Add an image from byte array

    public Images addImage(byte[] imageData, String imageName, String imageType, Long activityId) {

        if (imageData == null || imageData.length == 0) {
            throw new IllegalArgumentException("Image data cannot be empty");
        }

        if (imageName == null || imageName.trim().isEmpty()) {
            throw new IllegalArgumentException("Image name cannot be empty");
        }

        if (imageType == null || !isValidImageType(imageType)) {
            throw new IllegalArgumentException("Invalid image type. Only JPEG, PNG, GIF, and WEBP are allowed");
        }

        // Validate file size (e.g., max 5MB)
        if (imageData.length > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("Image size cannot exceed 5MB");
        }

        Optional<Activity> activity = activityRepository.findById(activityId);
        if (!activity.isPresent()) {
            throw new IllegalArgumentException("Activity with id " + activityId + " not found");
        }

        Images image = new Images();
        image.setImage(imageData);
        image.setNameImage(imageName);
        image.setTypeImage(imageType);
        image.setActivity(activity.get());

        return imagesRepository.save(image);
    }

    public Images updateImage(Long id, MultipartFile file) {
        try {
            Optional<Images> existingImage = imagesRepository.findById(id);
            if (!existingImage.isPresent()) {
                throw new IllegalArgumentException("Image with id " + id + " not found");
            }

            Images image = existingImage.get();

            if (file != null && !file.isEmpty()) {
                // Validate file
                if (file.getSize() > 5 * 1024 * 1024) {
                    throw new IllegalArgumentException("Image file size cannot exceed 5MB");
                }

                String contentType = file.getContentType();
                if (contentType == null || !isValidImageType(contentType)) {
                    throw new IllegalArgumentException("Invalid image type. Only JPEG, PNG, GIF, and WEBP are allowed");
                }

                image.setImage(file.getBytes());
                image.setNameImage(file.getOriginalFilename());
                image.setTypeImage(contentType);
            }

            return imagesRepository.save(image);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update image: " + e.getMessage(), e);
        }
    }

    public Images viewImage(Long id) {
        return imagesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image with id " + id + " not found"));
    }

    public List<Images> viewAllImages() {
        return imagesRepository.findAll();
    }

    public List<Images> getImagesByActivity(Long activityId) {

        Optional<Activity> activity = activityRepository.findById(activityId);
        if (!activity.isPresent()) {
            throw new IllegalArgumentException("Activity with id " + activityId + " not found");
        }

        return imagesRepository.findByActivityId(activityId);
    }

    public void deleteImage(Long id) {
        Optional<Images> image = imagesRepository.findById(id);
        if (!image.isPresent()) {
            throw new IllegalArgumentException("Image with id " + id + " not found");
        }

        imagesRepository.deleteById(id);
    }

    public void deleteImagesByActivity(Long activityId) {
        List<Images> images = imagesRepository.findByActivityId(activityId);
        if (!images.isEmpty()) {
            imagesRepository.deleteAll(images);
        }
    }

    // Validate image type

    private boolean isValidImageType(String contentType) {
        return contentType.equals("image/jpeg") ||
                contentType.equals("image/jpg") ||
                contentType.equals("image/png") ||
                contentType.equals("image/gif") ||
                contentType.equals("image/webp");
    }

    // Get image data as byte array (for downloading/displaying)

    public byte[] getImageData(Long id) {
        Images image = viewImage(id);
        return image.getImage();
    }
}
