package fpoly.asm.controllers;

import fpoly.asm.entities.KichThuoc;
import fpoly.asm.entities.MauSac;
import fpoly.asm.entities.enums.ActivityStatus;
import fpoly.asm.repositories.KichThuocRepo;
import fpoly.asm.repositories.MauSacRepo;
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
@RequestMapping("cua-hang/kich-thuoc")
public class KichThuocController {
    @Autowired
    private KichThuocRepo kichThuocRepo;
    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int  limit,
                        @RequestParam(value = "nameormakt",required = false) String nameormakt,
                        @RequestParam(value = "trangThai",required = false) ActivityStatus trangThai){
        Pageable pageable= PageRequest.of(page-1,limit);
        Page<KichThuoc> kichThuocPage = kichThuocRepo.findKichThuocByNameOrByMaAndTrangThai(nameormakt==null?null:nameormakt.trim(),trangThai==null?null:trangThai,pageable);
        model.addAttribute("totalPage",kichThuocPage.getTotalPages());
        model.addAttribute("nameormakt",nameormakt);
        model.addAttribute("trangThai",trangThai);
        model.addAttribute("lstKichThuoc",kichThuocPage);
        model.addAttribute("currentPage",page);
        return "kich_thuoc/index";
    }
    @GetMapping("edit/{id}")
    public String edit(Model model,@PathVariable("id") int id){
        Optional<KichThuoc> kichThuoc= kichThuocRepo.findById(id);
        if(kichThuoc.isPresent()){
            model.addAttribute("KichThuoc",kichThuoc.get());
            return "kich_thuoc/edit";
        }
        return "redirect:/cua-hang/kich-thuoc/index";
    }
    @GetMapping("create")
    public String create(Model model){
        KichThuoc kichThuoc= new KichThuoc();
        kichThuoc.setMa(String.format("KT%04d",kichThuocRepo.findAll().size()+1));
        model.addAttribute("KichThuoc",kichThuoc);
        return "kich_thuoc/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid KichThuoc kichThuoc, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("KichThuoc",kichThuoc);
            return "kich_thuoc/create";
        }
        kichThuocRepo.save(kichThuoc);
        return "redirect:/cua-hang/kich-thuoc/index";
    }
    @PostMapping("update")
    public String update(Model model,@Valid KichThuoc kichThuoc, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("KichThuoc",kichThuoc);
            return "kich_thuoc/edit";
        }
        kichThuocRepo.save(kichThuoc);
        return "redirect:/cua-hang/kich-thuoc/index";
    }
    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        kichThuocRepo.deleteById(id);
        return "redirect:/cua-hang/kich-thuoc/index";
    }
}
