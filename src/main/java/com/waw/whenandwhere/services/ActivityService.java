package com.waw.whenandwhere.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waw.whenandwhere.entities.Activity;
import com.waw.whenandwhere.repositories.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	public Activity addActivity(Activity activity) {

		if (activity.getTitle() == null || activity.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException("Activity title cannot be empty");
		}
		if (activity.getDescription() == null || activity.getDescription().trim().isEmpty()) {
			throw new IllegalArgumentException("Activity description cannot be empty");
		}
		if (activity.getCategory() == null) {
			throw new IllegalArgumentException("Activity must be associated with a category");
		}
		if (activity.getRegion() == null) {
			throw new IllegalArgumentException("Activity must be associated with a region");
		}
		if (activity.getBusiness() == null) {
			throw new IllegalArgumentException("Activity must be associated with a business");
		}

		if (activity.getValidationDate() == null) {
			activity.setValidationDate(new java.util.Date());
		}
		if (activity.getStatus() == null) {
			activity.setStatus("PENDING");
		}

		return activityRepository.save(activity);
	}

	public Activity updateActivity(Long id, Activity updatedActivity) {
		Optional<Activity> existingActivity = activityRepository.findById(id);
		if (!existingActivity.isPresent()) {
			throw new IllegalArgumentException("Activity with id " + id + " not found");
		}

		Activity activity = existingActivity.get();

		if (updatedActivity.getTitle() != null && !updatedActivity.getTitle().trim().isEmpty()) {
			activity.setTitle(updatedActivity.getTitle());
		}
		if (updatedActivity.getDescription() != null && !updatedActivity.getDescription().trim().isEmpty()) {
			activity.setDescription(updatedActivity.getDescription());
		}
		if (updatedActivity.getLocation() != null) {
			activity.setLocation(updatedActivity.getLocation());
		}
		if (updatedActivity.getVideo() != null) {
			activity.setVideo(updatedActivity.getVideo());
		}
		if (updatedActivity.getDuration() != null) {
			activity.setDuration(updatedActivity.getDuration());
		}
		if (updatedActivity.getPeriod() != null) {
			activity.setPeriod(updatedActivity.getPeriod());
		}
		if (updatedActivity.getLanguage() != null) {
			activity.setLanguage(updatedActivity.getLanguage());
		}
		if (updatedActivity.getOpeningDays() != null) {
			activity.setOpeningDays(updatedActivity.getOpeningDays());
		}
		if (updatedActivity.getSchedules() != null) {
			activity.setSchedules(updatedActivity.getSchedules());
		}
		if (updatedActivity.getStatus() != null) {
			activity.setStatus(updatedActivity.getStatus());
		}
		if (updatedActivity.getValidationDate() != null) {
			activity.setValidationDate(updatedActivity.getValidationDate());
		}
		if (updatedActivity.getCategory() != null) {
			activity.setCategory(updatedActivity.getCategory());
		}
		if (updatedActivity.getRegion() != null) {
			activity.setRegion(updatedActivity.getRegion());
		}
		if (updatedActivity.getBusiness() != null) {
			activity.setBusiness(updatedActivity.getBusiness());
		}

		activity.setActive(updatedActivity.isActive());
		activity.setReservationOnly(updatedActivity.isReservationOnly());
		activity.setMaxSimultaneousReservations(updatedActivity.getMaxSimultaneousReservations());
		activity.setMaxParticipantsReservations(updatedActivity.getMaxParticipantsReservations());

		return activityRepository.save(activity);
	}

	public Activity viewActivity(Long id) {
		Optional<Activity> activity = activityRepository.findById(id);
		if (!activity.isPresent()) {
			throw new IllegalArgumentException("Activity with id " + id + " not found");
		}
		return activity.get();
	}

	public List<Activity> findAllActivities() {
		return activityRepository.findAll();
	}

	public void deleteActivity(Long id) {
		Optional<Activity> activity = activityRepository.findById(id);
		if (!activity.isPresent()) {
			throw new IllegalArgumentException("Activity with id " + id + " not found");
		}

		Activity existActivity = activity.get();

		if (!existActivity.getReservations().isEmpty() || !existActivity.getIncludes().isEmpty()
				|| !existActivity.getExcludes().isEmpty() || !existActivity.getImages().isEmpty()
				|| !existActivity.getReviews().isEmpty()) {
			throw new IllegalStateException(
					"Cannot delete activity with associated reservations, includes, excludes, images, or reviews");
		}

		activityRepository.deleteById(id);
	}
}
