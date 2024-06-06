package fpoly.asm.repositories;

import fpoly.asm.entities.KhachHang;
import fpoly.asm.entities.NhanVien;
import fpoly.asm.entities.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KhachHangRepo extends JpaRepository<KhachHang,Integer> {
    @Query("Select kh from KhachHang kh  where (:nameorsdt is null or  kh.ten like %:nameorsdt%  or kh.sdt like %:nameorsdt% ) and (:trangThai is null or kh.trangThai = :trangThai)")
    Page<KhachHang> findKhachHangByNameOrBySdtAndTrangThai(
            @Param("nameorsdt") String nameorsdt,
            @Param("trangThai") ActivityStatus trangThai,
            Pageable pageable
    );
}
