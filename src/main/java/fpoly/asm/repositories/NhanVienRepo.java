package fpoly.asm.repositories;

import fpoly.asm.entities.NhanVien;
import fpoly.asm.entities.SanPham;
import fpoly.asm.entities.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NhanVienRepo extends JpaRepository<NhanVien,Integer> {
    @Query("Select nv from NhanVien nv  where (:nameormanv is null or  nv.ten like %:nameormanv%  or nv.ma like %:nameormanv% ) and (:trangThai is null or nv.trangThai = :trangThai)")
    Page<NhanVien> findNhanVienByNameOrByMaAndTrangThai(
            @Param("nameormanv") String nameormanv,
            @Param("trangThai") ActivityStatus trangThai,
            Pageable pageable
    );
}
