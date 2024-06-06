package fpoly.asm.controllers;

import fpoly.asm.entities.NhanVien;
import fpoly.asm.entities.SanPham;
import fpoly.asm.entities.enums.ActivityStatus;
import fpoly.asm.repositories.NhanVienRepo;
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
@RequestMapping("cua-hang/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienRepo nhanVienRepo;
    @GetMapping("index")
    public String index(Model model, @RequestParam(defaultValue =  "1") int page, @RequestParam(defaultValue = "10") int  limit,
                        @RequestParam(value = "nameormanv",required = false) String nameormanv,
                        @RequestParam(value = "trangThai",required = false) ActivityStatus trangThai){
        Pageable pageable= PageRequest.of(page-1,limit);
        Page<NhanVien> nhanVienPage= nhanVienRepo.findNhanVienByNameOrByMaAndTrangThai(nameormanv==null?null:nameormanv.trim(),trangThai==null?null:trangThai,pageable);
        model.addAttribute("totalPage",nhanVienPage.getTotalPages());
        model.addAttribute("nameormanv",nameormanv);
        model.addAttribute("trangThai",trangThai);
        model.addAttribute("lstNhanVien",nhanVienPage);
        model.addAttribute("currentPage",page);
        return "nhan_vien/index";
    }
    @GetMapping("edit/{id}")
    public String edit(Model model,@PathVariable("id") int id){
        Optional<NhanVien> nhanVien= nhanVienRepo.findById(id);
        if(nhanVien.isPresent()){
            model.addAttribute("NhanVien",nhanVien.get());
            return "nhan_vien/edit";
        }
        return "redirect:/cua-hang/nhan-vien/index";
    }
    @GetMapping("create")
    public String create(Model model){
        NhanVien nhanVien= new NhanVien();
        nhanVien.setMa(String.format("NV%04d",nhanVienRepo.findAll().size()+1));
        model.addAttribute("NhanVien",nhanVien);
        return "nhan_vien/create";
    }
    @PostMapping("store")
    public String store(Model model, @Valid NhanVien nhanVien, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("NhanVien",nhanVien);
            return "nhan_vien/create";
        }
        nhanVienRepo.save(nhanVien);
        return "redirect:/cua-hang/nhan-vien/index";
    }
    @PostMapping("update")
    public String update(Model model, @Valid NhanVien nhanVien, BindingResult validateResult){
        if(validateResult.hasErrors()){
            List<FieldError> fieldErrorList=validateResult.getFieldErrors();
            Map<String,String> errors= new HashMap<>();
            for(int i=0;i<fieldErrorList.size();i++){
                errors.put(fieldErrorList.get(i).getField(),fieldErrorList.get(i).getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            model.addAttribute("NhanVien",nhanVien);
            return "nhan_vien/edit";
        }
        nhanVienRepo.save(nhanVien);
        return "redirect:/cua-hang/nhan-vien/index";
    }
    @GetMapping("remove/{id}")
    public String remove(@PathVariable("id") int id){
        nhanVienRepo.deleteById(id);
        return "redirect:/cua-hang/nhan-vien/index";
    }
}
