package com.naite.bookingTour.controller.admin;

import com.naite.bookingTour.model.Category;
import com.naite.bookingTour.model.User;
import com.naite.bookingTour.service.CategoryService;
import com.naite.bookingTour.service.UserManagementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {
    private final UserManagementService userManagementService;
    private final CategoryService categoryService;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public CategoriesController(UserManagementService userManagementService, CategoryService categoryService) {
        this.userManagementService = userManagementService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/", ""})
    public String list(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
        }
        Optional<User> userOptional = userManagementService.findByUsername((String) session.getAttribute("username")); // Retrieve the user's full name from the service or repository
        User user = userOptional.get();
        model.addAttribute("fullname", user.getFullname());
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/category-list";
    }

    @GetMapping("/new")
    public String add(HttpSession session, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
        }
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    @PostMapping("/new")
    public String save(HttpSession session, @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
        }
        Category existingCategory = categoryService.findByName(category.getName());
        if (existingCategory != null) {
            redirectAttributes.addFlashAttribute("error", "Category name already exists");
            return "redirect:/admin/categories/new";
        }
        List<Category> categories = categoryService.findAll();
        List<Long> existingIds = new ArrayList<>();
        // Lấy danh sách các ID hiện có
        for (Category c : categories) {
            existingIds.add(c.getId());
        }
        Long newCategoryId = findUnusedId(existingIds); // Tìm ID chưa được sử dụng
        category.setId(newCategoryId);
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(HttpSession session, @PathVariable("id") Long id, Model model) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
        }
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
        } else {
            return "error-404";
        }

        return "admin/category-form";
    }


    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(HttpSession session, @PathVariable("id") Long id) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login"; // Chuyển hướng người dùng đến trang đăng nhập nếu chưa đăng nhập
        }
        categoryService.deleteById(id);
        idGenerator.decrementAndGet();
        return "redirect:/admin/categories";
    }

    private Long findUnusedId(List<Long> existingIds) {
        long candidateId = idGenerator.incrementAndGet();
        while (existingIds.contains(candidateId)) {
            candidateId = idGenerator.incrementAndGet();
        }
        return candidateId;
    }

}
