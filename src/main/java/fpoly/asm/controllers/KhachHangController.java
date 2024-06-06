package fpoly.asm.controllers;

import fpoly.asm.entities.KhachHang;
import fpoly.asm.entities.enums.ActivityStatus;
import fpoly.asm.repositories.KhachHangRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("cua-hang/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangRepo khachHangRepo;

    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,
                        @RequestParam(value = "nameorsdt", required = false) String nameorsdt,
                        @RequestParam(value = "trangThai", required = false) ActivityStatus trangThai) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<KhachHang> khachHangPage = khachHangRepo.findKhachHangByNameOrBySdtAndTrangThai(nameorsdt == null ? null : nameorsdt.trim(), trangThai == null ? null : trangThai, pageable);
        model.addAttribute("totalPage", khachHangPage.getTotalPages());
        model.addAttribute("nameorsdt", nameorsdt);
        model.addAttribute("trangThai", trangThai);
        model.addAttribute("lstKhachHang", khachHangPage);
        model.addAttribute("currentPage", page);
        return "khach_hang/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<KhachHang> khachHang = khachHangRepo.findById(id);
        if (khachHang.isPresent()) {
            model.addAttribute("KhachHang",khachHang.get());
            return "khach_hang/edit";
        }
        return "redirect:/cua-hang/khach-hang/index";
    }

    @GetMapping("create")
    public String create(Model model) {
        KhachHang khachHang = new KhachHang();
        khachHang.setMa(String.format("KH%04d", khachHangRepo.findAll().size() + 1));
        model.addAttribute("KhachHang", khachHang);
        return "khach_hang/create";
    }

    @PostMapping("store")
    public String store(Model model, @Valid KhachHang khachHang, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<FieldError> fieldErrorList = validateResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (int i = 0; i < fieldErrorList.size(); i++) {
                errors.put(fieldErrorList.get(i).getField(), fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("KhachHang",khachHang);
            return "khach_hang/create";
        }
        khachHangRepo.save(khachHang);
        return "redirect:/cua-hang/khach-hang/index";
    }

    @PostMapping("update")
    public String update(Model model, @Valid KhachHang khachHang, BindingResult validateResult) {
        if (validateResult.hasErrors()) {
            List<FieldError> fieldErrorList = validateResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            for (int i = 0; i < fieldErrorList.size(); i++) {
                errors.put(fieldErrorList.get(i).getField(), fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors", errors);
            model.addAttribute("KhachHang", khachHang);
            return "khach_hang/edit";
        }
        khachHangRepo.save(khachHang);
        return "redirect:/cua-hang/khach-hang/index";
    }

    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") int id) {
        khachHangRepo.deleteById(id);
        return "redirect:/cua-hang/khach-hang/index";
    }
}
