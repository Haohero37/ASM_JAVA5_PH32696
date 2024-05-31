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
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "MauSac")
@Component
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Vui lòng không để trống mã màu sắc")
    private String ma;
    @NotBlank(message = "Vui lòng không để trống tên màu sắc")
    private String ten;
    @NotNull(message = "Vui lòng không để trống trạng thái  màu sắc")
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatus trangThai;
}
