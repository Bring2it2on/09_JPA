package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springdatajpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.model.entity.Category;
import com.ohgiraffers.springdatajpa.menu.model.entity.Menu;
import com.ohgiraffers.springdatajpa.menu.model.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
        this.menuRepository = menuRepository;
        this.modelMapper = modelMapper;
    }

    public MenuDTO findMenuByCode(int menuCode) {

        // MenuDTO -> 일반클래스
        // Menu -> Entity

        /*
        * findBy 메소드는 이미 구현이 되어있다.
        * 반환값은 Optional 타입이고 Optional Type은 NPE를 방지하기 위해 다양한 기능을 제공
        * */
        Menu menu = menuRepository.findById(menuCode)
//                .orElseThrow(IllegalArgumentException::new);
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 메뉴 코드에 해당하는 메뉴가 존재하지 않습니다.")
                );

        log.info("menu ==========> {}", menu);


        return modelMapper.map(menu,MenuDTO.class);
    }

    public List<MenuDTO> findMenuList() {

        List<Menu> menuList =
                menuRepository.findAll();
//                menuRepository.findAll(Sort.by("menuPrice").descending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    public Page<MenuDTO> findAllMenus(Pageable pageable) {


        // page 파라미터가 Pageable의 number로 넘어옴
        // 조회했을때는 인덱스 기준이 되기 때문에 -1
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("menuCode").descending());

        Page<Menu> menuList = menuRepository.findAll(pageable);

        return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
    }

    public List<MenuDTO> findByMenuPrice(Integer menuPrice, int condition) {

        List<Menu> menuList = null;

//        if (menuPrice == 0) {
//            menuList = menuRepository.findAll();
//        } else if (menuPrice > 0) {
//            menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice,
//                    Sort.by("menuPrice").descending());
//        }

        if (menuPrice == 0) {
            menuList = menuRepository.findAll();
        } else if (menuPrice > 0 && condition == 1) {
            menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuCode(menuPrice);
        } else if (menuPrice > 0 && condition == 2) {
            menuList = menuRepository.findByMenuPriceEquals(menuPrice);
        } else if (menuPrice > 0 && condition == 3) {
            menuList = menuRepository.findByMenuPriceLessThanEqual(menuPrice);
        }

        return menuList.stream()
                .map(menu -> modelMapper.map(menu,MenuDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> findAllCategory() {

//        List<Category> categoryList = categoryRepository

        return null;
    }
}
