package org.example.service;

import org.example.model.Feedback;
import org.example.repository.IFeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService {
    @Autowired
    private IFeedBackRepository feedBackRepository;
    @Autowired
    private BadWordService badWordService;

    public Page<Feedback> findAll(Pageable pageable) {
        return feedBackRepository.findAll(pageable);
    }

    public void save(Feedback feedback) {
        if (badWordService.containsBadWords(feedback.getComment())) {
            throw new IllegalArgumentException("Feedback chứa từ ngữ không phù hợp.");
        }
        feedBackRepository.save(feedback);
    }

    public void delete(Feedback feedback) {
        feedBackRepository.delete(feedback);
    }

    public Feedback findById(Long id) {
        return feedBackRepository.findById(id).orElse(null);
    }
}
