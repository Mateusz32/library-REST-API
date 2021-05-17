package com.restapiforlibrary.library.service;

import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    public Optional<Title> getTitle(Long id) {
        return titleRepository.findById(id);
    }

    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }
}
