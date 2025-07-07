package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Region;
import com.waw.whenandwhere.repositories.RegionRepository;

@Service
public class RegionService {
	@Autowired
    private RegionRepository regionRepository;

    
    public Region addRegion(Region region) {
        
        if (region.getName() == null || region.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Region name cannot be empty");
        }
        return regionRepository.save(region);
    }

    
    public Region updateRegion(Long id, Region updatedRegion) {
        Optional<Region> existingRegion = regionRepository.findById(id);
        if (!existingRegion.isPresent()) {
            throw new IllegalArgumentException("Region with id " + id + " not found");
        }

        Region region = existingRegion.get();
        
        if (updatedRegion.getName() != null && !updatedRegion.getName().trim().isEmpty()) {
            region.setName(updatedRegion.getName());
        }
        

        return regionRepository.save(region);
    }

    
    public Region viewRegion(Long id) {
        Optional<Region> region = regionRepository.findById(id);
        if (!region.isPresent()) {
            throw new IllegalArgumentException("Region with id " + id + " not found");
        }
        return region.get();
    }

    
    public List<Region> viewAllRegions() {
        return regionRepository.findAll();
    }

    
    public void deleteRegion(Long id) {
        Optional<Region> region = regionRepository.findById(id);
        if (!region.isPresent()) {
            throw new IllegalArgumentException("Region with id " + id + " not found");
        }

        Region existingRegion = region.get();
        
        if (!existingRegion.getActivities().isEmpty() || existingRegion.getAddress() != null) {
            throw new IllegalStateException("Cannot delete region with associated activities or address");
        }

        regionRepository.deleteById(id);
    }
}
