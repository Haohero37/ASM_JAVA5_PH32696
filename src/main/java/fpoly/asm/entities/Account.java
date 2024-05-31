package fpoly.asm.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @NotBlank(message = "Không được để trống Username")
    private String username;
    @NotBlank(message = "Không được để trống Password")
    private String password;
    private boolean isAdmin;
}
