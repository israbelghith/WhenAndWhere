package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Includes;
import com.waw.whenandwhere.repositories.IncludesRepository;

@Service
public class IncludesService {

	@Autowired
    private IncludesRepository includesRepository;

    public Includes addIncludes(Includes includes) {
        if (includes.getTitle() == null || includes.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (includes.getActivity() == null) {
            throw new IllegalArgumentException("Activity cannot be null");
        }
        return includesRepository.save(includes);
    }

    public Includes updateIncludes(Long id, Includes updatedIncludes) {
        Optional<Includes> existingIncludes = includesRepository.findById(id);
        if (!existingIncludes.isPresent()) {
            throw new IllegalArgumentException("Includes with id " + id + " not found");
        }

        Includes includes = existingIncludes.get();

        if (updatedIncludes.getTitle() != null && !updatedIncludes.getTitle().trim().isEmpty()) {
            includes.setTitle(updatedIncludes.getTitle());
        }
        if (updatedIncludes.getActivity() != null) {
            includes.setActivity(updatedIncludes.getActivity());
        }

        return includesRepository.save(includes);
    }

    public Includes viewIncludes(Long id) {
        Optional<Includes> includes = includesRepository.findById(id);
        if (!includes.isPresent()) {
            throw new IllegalArgumentException("Includes with id " + id + " not found");
        }
        return includes.get();
    }

    public List<Includes> viewAllIncludes() {
        return includesRepository.findAll();
    }

    public void deleteIncludes(Long id) {
        Optional<Includes> includes = includesRepository.findById(id);
        if (!includes.isPresent()) {
            throw new IllegalArgumentException("Includes with id " + id + " not found");
        }

        includesRepository.deleteById(id);
    }
}
