package com.restapiforlibrary.library.controller;

import com.restapiforlibrary.library.domain.Title;
import com.restapiforlibrary.library.domain.TitleDto;
import com.restapiforlibrary.library.mapper.TitleMapper;
import com.restapiforlibrary.library.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/title")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper titleMapper;

    @GetMapping(value = "/getTitle")
    public TitleDto getTitle(@RequestParam Long titleId) throws NotFoundException {
        return titleMapper.mapToTitleDto(titleService.getTitle(titleId).orElseThrow(NotFoundException::new));
    }

    @GetMapping(value = "/getTitles")
    public List<TitleDto> getTitles() {
        List<Title> titleList = titleService.getTitles();
        return titleMapper.mapToTitleDtoList(titleList);
    }

    @PostMapping(value = "/createTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        titleService.saveTitle(title);
    }

    @PutMapping(value = "/editTitle")
    public TitleDto editTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedTitle = titleService.saveTitle(title);
        return titleMapper.mapToTitleDto(savedTitle);
    }

    @DeleteMapping("/deleteTitle")
    public void deleteTitle(@RequestParam Long titleId) {
        titleService.deleteTitle(titleId);
    }
}
