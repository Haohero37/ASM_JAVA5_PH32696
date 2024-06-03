package fpoly.asm.repositories;

import fpoly.asm.entities.SanPham;
import fpoly.asm.entities.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepo extends JpaRepository<SanPham,Integer> {
    @Query("Select sp from SanPham sp  where (:nameormasp is null or  sp.ma like %:nameormasp%  or sp.ten like %:nameormasp% ) and (:trangThai is null or sp.trangThai = :trangThai)")
    Page<SanPham> findSanPhamByNameOrByMaAndTrangThai(
            @Param("nameormasp") String nameormasp,
            @Param("trangThai") ActivityStatus trangThai,
            Pageable pageable
    );
}
