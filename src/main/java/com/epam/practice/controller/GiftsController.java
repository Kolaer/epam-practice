package com.epam.practice.controller;

import com.epam.practice.model.Gift;
import com.epam.practice.model.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GiftsController {

    private final GiftRepository giftRepository;
    private final int pageSize = 15;

    @Autowired
    public GiftsController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @RequestMapping(value = "/gifts")
    public ModelAndView gifts(@RequestParam(required = false, defaultValue = "0", name = "offset") String offsetString) {
        Integer offset;

        try {
            offset = Integer.parseInt(offsetString);
        } catch (NumberFormatException ex) {
            offset = 0;
        }

        ModelAndView ret = new ModelAndView("gifts");

        final long count = giftRepository.count();

        ret.addObject("giftsCount", count);

        long pageCount = count / pageSize;
        if(count % pageSize != 0) {
            pageCount += 1;
        }

        ret.addObject("pageCount", pageCount);
        ret.addObject("thisPage", offset);

        Page<Gift> giftPage = giftRepository.findAll(PageRequest.of(offset, pageSize));

        ret.addObject("gifts", giftPage.getContent());

        return ret;
    }
}
