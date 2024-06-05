package fpoly.asm.repositories;

import fpoly.asm.entities.MauSac;
import fpoly.asm.entities.enums.ActivityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MauSacRepo extends JpaRepository<MauSac,Integer> {
    @Query("Select ms from MauSac  ms where (:nameormams is null or  ms.ma like %:nameormams%  or ms.ten like %:nameormams% ) and (:trangThai is null or ms.trangThai = :trangThai)")
    Page<MauSac> findMauSacByNameOrByMaAndTrangThai(
            @Param("nameormams") String nameormams,
            @Param("trangThai") ActivityStatus trangThai,
            Pageable pageable
    );
}
