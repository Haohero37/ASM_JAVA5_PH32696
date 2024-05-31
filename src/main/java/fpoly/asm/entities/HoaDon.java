package fpoly.asm.entities;

import fpoly.asm.entities.enums.ActivityStatus;
import fpoly.asm.entities.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "HoaDon")
@Component
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive( message = "Vui lòng chọn nhân viên")
    private int idNV;
    @Positive(message = "Vui lòng chọn khách hàng")
    private int idKhachHang;
    @FutureOrPresent(message = "Ngày mua hàng chưa hợp lệ")
    private LocalDateTime ngayMuaHang;
    @NotNull(message = "Vui lòng chọn trạng thái")
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus trangThai;
}
