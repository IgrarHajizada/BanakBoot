package az.orient.bankboot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumAvailableStatus {
    ACTIVE(1), DEACTIVATE(0);

    private final int value;

}
