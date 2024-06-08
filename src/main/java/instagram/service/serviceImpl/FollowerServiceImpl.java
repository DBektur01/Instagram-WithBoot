package instagram.service.serviceImpl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.repository.FollowerRepository;
import instagram.repository.UserRepository;
import instagram.service.FollowerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;
    @Override
    public int getNumberOfSubscribers(Long userId) {
        Follower follower = followerRepository.findByUserId(userId);
        return follower.getSubscribers().isEmpty() ? 0 : follower.getSubscribers().size();
    }
    @Override
    public int getNumberOfSubscriptions(Long userId) {
        Follower follower = followerRepository.findByUserId(userId);
        return follower.getSubscriptions().isEmpty() ? 0 : follower.getSubscriptions().size();
    }
    @Transactional
    @Override
    public void addSubscriber(Long userId, Long subscriberId) {
        User user = userRepository.findById(userId).orElseThrow();
        User subscriber = userRepository.findById(subscriberId).orElseThrow();

        Follower userFollower = user.getFollower();
        Follower subscriberFollower = subscriber.getFollower();

        List<Long> userSubscriptions = userFollower.getSubscriptions();
        List<Long> subscriberSubscribers = subscriberFollower.getSubscribers();

        if (userSubscriptions.contains(subscriberId)) {
            userSubscriptions.remove(subscriberId);
            subscriberSubscribers.remove(userId);
        } else {
            userSubscriptions.add(subscriberId);
            subscriberSubscribers.add(userId);
        }
    }
}
