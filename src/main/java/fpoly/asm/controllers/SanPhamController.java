package fpoly.asm.controllers;

import fpoly.asm.entities.SanPham;
import fpoly.asm.entities.enums.ActivityStatus;
import fpoly.asm.repositories.SanPhamRepo;
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
@RequestMapping("cua-hang/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamRepo sanPhamRepo;
    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue =  "1") int page,@RequestParam(defaultValue = "10") int  limit,
                        @RequestParam(value = "nameormasp",required = false) String nameormasp,
                        @RequestParam(value = "trangThai",required = false) ActivityStatus trangThai){
        Pageable pageable= PageRequest.of(page-1,limit);
        Page<SanPham> sanPhamPage= sanPhamRepo.findSanPhamByNameOrByMaAndTrangThai(nameormasp==null?null:nameormasp.trim(),trangThai==null?null:trangThai,pageable);
        model.addAttribute("totalPage",sanPhamPage.getTotalPages());
        model.addAttribute("nameormasp",nameormasp);
        model.addAttribute("trangThai",trangThai);
        model.addAttribute("lstSanPham",sanPhamPage);
        model.addAttribute("currentPage",page);
        return "san_pham/index";
    }
    @GetMapping("edit/{id}")
    public String edit(Model model,@PathVariable("id") int id){
        Optional<SanPham> sanPham= sanPhamRepo.findById(id);
        if(sanPham.isPresent()){
            model.addAttribute("SanPham",sanPham.get());
            return "san_pham/edit";
        }
        return "redirect:/cua-hang/san-pham/index";
    }
    @GetMapping("create")
    public String create(Model model){
        SanPham sanPham= new SanPham();
        sanPham.setMa(String.format("SP%04d",sanPhamRepo.findAll().size()+1));
        model.addAttribute("SanPham",sanPham);
        return "san_pham/create";
    }
    @PostMapping("store")
    public String store(Model model,@Valid SanPham sanPham, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("SanPham",sanPham);
            return "san_pham/create";
        }
        sanPhamRepo.save(sanPham);
        return "redirect:/cua-hang/san-pham/index";
    }
    @PostMapping("update")
    public String update(Model model,@Valid SanPham sanPham, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("SanPham",sanPham);
            return "san_pham/edit";
        }
        sanPhamRepo.save(sanPham);
        return "redirect:/cua-hang/san-pham/index";
    }
    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        sanPhamRepo.deleteById(id);
        return "redirect:/cua-hang/san-pham/index";
    }
}
