package fpoly.asm.controllers;

import fpoly.asm.entities.MauSac;
import fpoly.asm.entities.SanPham;
import fpoly.asm.entities.enums.ActivityStatus;
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
@RequestMapping("cua-hang/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacRepo mauSacRepo;
    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int  limit,
                        @RequestParam(value = "nameormams",required = false) String nameormams,
                        @RequestParam(value = "trangThai",required = false) ActivityStatus trangThai){
        Pageable pageable= PageRequest.of(page-1,limit);
        Page<MauSac> mauSacPage= mauSacRepo.findMauSacByNameOrByMaAndTrangThai(nameormams==null?null:nameormams.trim(),trangThai==null?null:trangThai,pageable);
        model.addAttribute("totalPage",mauSacPage.getTotalPages());
        model.addAttribute("nameormams",nameormams);
        model.addAttribute("trangThai",trangThai);
        model.addAttribute("lstMauSac",mauSacPage);
        model.addAttribute("currentPage",page);
        return "mau_sac/index";
    }
    @GetMapping("edit/{id}")
    public String edit(Model model,@PathVariable("id") int id){
        Optional<MauSac> mauSac= mauSacRepo.findById(id);
        if(mauSac.isPresent()){
            model.addAttribute("MauSac",mauSac.get());
            return "mau_sac/edit";
        }
        return "redirect:/cua-hang/mau-sac/index";
    }
    @GetMapping("create")
    public String create(Model model){
        MauSac mauSac= new MauSac();
        mauSac.setMa(String.format("MS%04d",mauSacRepo.findAll().size()+1));
        model.addAttribute("MauSac",mauSac);
        return "mau_sac/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid MauSac mauSac, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("MauSac",mauSac);
            return "mau_sac/create";
        }
        mauSacRepo.save(mauSac);
        return "redirect:/cua-hang/mau-sac/index";
    }
    @PostMapping("update")
    public String update(Model model,@Valid MauSac mauSac, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("MauSac",mauSac);
            return "mau_sac/edit";
        }
        mauSacRepo.save(mauSac);
        return "redirect:/cua-hang/mau-sac/index";
    }
    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        mauSacRepo.deleteById(id);
        return "redirect:/cua-hang/mau-sac/index";
    }
}
