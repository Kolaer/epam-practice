package com.epam.practice.controller;

import com.epam.practice.model.Gift;
import com.epam.practice.model.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AdminGiftController {
    @Autowired
    private GiftRepository giftRepository;

    private int pageSize = 15;

    @RequestMapping("/admin/gifts")
    public ModelAndView adminGifts(@RequestParam(required = false, defaultValue = "0", name = "offset") String offsetString) {
        Integer offset;

        try {
            offset = Integer.parseInt(offsetString);
        } catch (NumberFormatException ex) {
            offset = 0;
        }

        ModelAndView ret = new ModelAndView("admin/gifts");

        final long count = giftRepository.count();

        ret.addObject("giftsCount", count);

        long pageCount = count / pageSize;
        if (count % pageSize != 0) {
            pageCount += 1;
        }

        ret.addObject("pageCount", pageCount);
        ret.addObject("thisPage", offset);

        Page<Gift> giftPage = giftRepository.findAll(PageRequest.of(offset, pageSize));

        ret.addObject("gifts", giftPage.getContent());

        return ret;
    }

    @RequestMapping(value = "/admin/gift", method = RequestMethod.GET)
    public ModelAndView adminGift(@RequestParam(name = "id") String idString) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return new ModelAndView("redirect:admin/gifts");
        }

        ModelAndView ret = new ModelAndView("admin/gift");

        Optional<Gift> gift = giftRepository.findById(id);

        if (gift.isPresent()) {
            ret.addObject("gift", gift.get());

            return ret;
        } else {
            return new ModelAndView("redirect:admin/gifts");
        }
    }

    @RequestMapping(value = "/admin/gift", method = RequestMethod.POST)
    public String adminGiftEdit(@RequestParam(name = "id") String idString,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "desc") String desc,
                                @RequestParam(name = "url") String url) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return "redirect:/admin";
        }

        Optional<Gift> optionalGift = giftRepository.findById(id);

        if (optionalGift.isPresent()) {
            Gift gift = optionalGift.get();

            gift.setName(name);
            gift.setDescription(desc);
            gift.setUrl(url);

            giftRepository.save(gift);
        }

        return "redirect:/admin/gifts";
    }

    @RequestMapping(value = "/admin/giftDelete", method = RequestMethod.POST)
    public String adminGiftDelete(@RequestParam(name = "id") String idString) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return "redirect:admin/gifts";
        }

        giftRepository.deleteById(id);

        return "redirect:admin/gifts";
    }

    @RequestMapping(value = "/admin/giftAdd")
    public String adminGiftAddView() {
        return "admin/giftAdd";
    }

    @RequestMapping(value = "/admin/giftAdd", method = RequestMethod.POST)
    public String adminGiftAdd(@RequestParam(name = "name") String name,
                               @RequestParam(name = "desc") String desc,
                               @RequestParam(name = "url") String url) {
        Gift gift = new Gift(name, desc, url);

        giftRepository.save(gift);

        return "redirect:/admin/gifts";
    }
}