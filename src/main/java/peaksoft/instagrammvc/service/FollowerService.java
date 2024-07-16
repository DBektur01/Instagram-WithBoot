package peaksoft.instagrammvc.service;

import jakarta.persistence.ElementCollection;

public interface FollowerService {

    int getNumberOfSubscribers(Long userId);
    int getNumberOfSubscriptions(Long userId);
    void addSubscriber(Long userId, Long subscriberId);
}
