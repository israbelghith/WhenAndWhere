package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Excludes;
import com.waw.whenandwhere.repositories.ExcludesRepository;

@Service
public class ExcludesService {

	@Autowired
    private ExcludesRepository excludesRepository;

    public Excludes addExcludes(Excludes excludes) {
        if (excludes.getTitle() == null || excludes.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (excludes.getActivity() == null) {
            throw new IllegalArgumentException("Activity cannot be null");
        }
        return excludesRepository.save(excludes);
    }

    public Excludes updateExcludes(Long id, Excludes updatedExcludes) {
        Optional<Excludes> existingExcludes = excludesRepository.findById(id);
        if (!existingExcludes.isPresent()) {
            throw new IllegalArgumentException("Excludes with id " + id + " not found");
        }

        Excludes excludes = existingExcludes.get();

        if (updatedExcludes.getTitle() != null && !updatedExcludes.getTitle().trim().isEmpty()) {
            excludes.setTitle(updatedExcludes.getTitle());
        }
        if (updatedExcludes.getActivity() != null) {
            excludes.setActivity(updatedExcludes.getActivity());
        }

        return excludesRepository.save(excludes);
    }

    public Excludes viewExcludes(Long id) {
        Optional<Excludes> excludes = excludesRepository.findById(id);
        if (!excludes.isPresent()) {
            throw new IllegalArgumentException("Excludes with id " + id + " not found");
        }
        return excludes.get();
    }

    public List<Excludes> viewAllExcludes() {
        return excludesRepository.findAll();
    }

    public void deleteExcludes(Long id) {
        Optional<Excludes> excludes = excludesRepository.findById(id);
        if (!excludes.isPresent()) {
            throw new IllegalArgumentException("Excludes with id " + id + " not found");
        }

        excludesRepository.deleteById(id);
    }
}
