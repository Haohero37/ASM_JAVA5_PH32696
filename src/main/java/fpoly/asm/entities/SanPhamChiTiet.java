package fpoly.asm.entities;

import fpoly.asm.entities.enums.ActivityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "SanPhamChiTiet")
@Component
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống mã sản phẩm chi tiêt")
    private String maSPCT;
    @Positive(message = "ID kích thước không hợp lệ")
    private int kichThuoc;
    @Positive(message = "ID kích thước không hợp lệ")
    private int mauSac;
    @Positive(message = "ID màu sắc không hợp lệ")
    private int idSP;
    @Positive(message = "Số Lượng phải lớn hơn 0")
    private int soLuong;
    @DecimalMin(value = "0.0",inclusive = false, message = "Giá phải lớn hơn không")
    private BigDecimal dongia;
    @NotNull(message = "Vui lòng chọn trạng thái")
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus trangThai;
}
