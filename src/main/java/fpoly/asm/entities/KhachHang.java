package fpoly.asm.entities;

import fpoly.asm.entities.enums.ActivityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "KhachHang")
@Component
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống mã")
    private String ma;
    @NotBlank(message = "Vui lòng không để trống tên")
    private String ten;
    @NotBlank(message = "Vui lòng không để trống số điện thoại")
    @Pattern(regexp = "^0\\d{9}$",message = "Số điện thoại không hợp lệ")
    private String sdt;
    @NotNull(message = "Vui lòng chọn trạng thái")
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus trangThai;
}
