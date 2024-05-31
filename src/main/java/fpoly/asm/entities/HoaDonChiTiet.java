package fpoly.asm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
@Table( name = "HoaDonChiTiet")
@Component
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive(message = "Vui lòng chọn hóa đơn")
    private int idHD;
    @Positive(message = "Vui lòng chọn sản phẩm")
    private int idSPCT;
    @Positive(message = "Số lượng phải lớn hơn 0")
    private int soLuong;
    @DecimalMin(value = "0.0",inclusive = false, message = "Giá phải lớn hơn không")
    private BigDecimal donGia;
}
