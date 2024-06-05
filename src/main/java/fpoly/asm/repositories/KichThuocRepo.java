package fpoly.asm.repositories;

import fpoly.asm.entities.KichThuoc;
import fpoly.asm.entities.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KichThuocRepo extends JpaRepository<KichThuoc,Integer> {
    @Query("Select kt from KichThuoc  kt where (:nameormakt is null or  kt.ma like %:nameormakt%  or kt.ten like %:nameormakt% ) and (:trangThai is null or kt.trangThai = :trangThai)")
    Page<KichThuoc> findKichThuocByNameOrByMaAndTrangThai(
            @Param("nameormakt") String nameormakt,
            @Param("trangThai") ActivityStatus trangThai,
            Pageable pageable
    );
}
