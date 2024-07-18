package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 메뉴 단일 조회 기능
    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable("menuCode") int menuCode, Model model) {

        log.info("menuCode = {}", menuCode);

        MenuDTO menu = menuService.findMenuByCode(menuCode);

        model.addAttribute("menu",menu);

        return "menu/detail";
    }
///*페이징 처리 전*/
//    @GetMapping("/list")
//    public String findMenuList(Model model) {
//
//        List<MenuDTO> menuList = menuService.findMenuList();
//
//        model.addAttribute("menuList",menuList);
//
//        return "menu/list";
//    }

    @GetMapping("/list")
    public String findAllMenus(@PageableDefault Pageable pageable, Model model) {

        log.info("pageable = {}", pageable);

        Page<MenuDTO> menuList = menuService.findAllMenus(pageable);

        log.info("조회한 내용 목록 : {}", menuList.getContent());
        log.info("총 페이지 수 : {}", menuList.getTotalPages());
        log.info("총 메뉴 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소 수 : {}", menuList.getSize());
        log.info("해당 페이지에 실제 요소 수 : {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬 방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

        model.addAttribute("paging", paging);
        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage() {}

    @GetMapping("/search")
    public String findByMenuPrice(@RequestParam Integer menuPrice, Model model) {

        log.info("menuPrice ================ {}", menuPrice);

        List<MenuDTO> menulist = menuService.findByMenuPrice(menuPrice,1);

        model.addAttribute("menuList", menulist);
        model.addAttribute("menuPrice",menuPrice);
        model.addAttribute("message",menuPrice+"원을 초과하는 메뉴 목록");

        return "menu/searchResult";
    }

    @GetMapping("/search2")
    public String findByMenuEqualPrice(@RequestParam Integer menuPrice, Model model) {

        log.info("menuPrice ================ {}", menuPrice);

        List<MenuDTO> menulist = menuService.findByMenuPrice(menuPrice,2);

        model.addAttribute("menuList", menulist);
        model.addAttribute("menuPrice",menuPrice);
        model.addAttribute("message",menuPrice+"원과 동일한 메뉴 목록");

        return "menu/searchResult";
    }

    @GetMapping("/search3")
    public String findByMenuLessThanPrice(@RequestParam Integer menuPrice, Model model) {

        log.info("menuPrice ================ {}", menuPrice);

        List<MenuDTO> menulist = menuService.findByMenuPrice(menuPrice,3);

        model.addAttribute("menuList", menulist);
        model.addAttribute("menuPrice",menuPrice);
        model.addAttribute("message",menuPrice+"원 이하의 메뉴 목록");

        return "menu/searchResult";
    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping(value = "category", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<CategoryDTO> categoryPage() {

        List<CategoryDTO> categoryList = menuService.findAllCategory();
        log.info("categoryList =============== {}", categoryList);

        return categoryList;
    }
}
