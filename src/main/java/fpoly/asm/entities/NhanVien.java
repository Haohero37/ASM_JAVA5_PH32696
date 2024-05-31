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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "NhanVien")
@Component
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống tên")
    private String ten;
    @NotBlank(message = "Vui lòng không để trống mã")
    private String maNV;
    @NotBlank(message = "Vui lòng không để trống tên đăng nhập")
    private String tenDangNhap;
    @NotBlank(message = "Vui lòng không để trống mật khẩu nhân viên")
    @Length(min = 8,message = "Độ dài mật khẩu tối thiểu 8 kí tự")
    private String matKhau;
    @NotNull(message = "Vui lòng chọn trạng thái nhân viên")
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus trangThai;
}
