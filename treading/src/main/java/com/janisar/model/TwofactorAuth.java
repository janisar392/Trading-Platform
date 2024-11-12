package com.janisar.model;

import com.janisar.domain.VerificationType;
import lombok.Data;

@Data
public class TwofactorAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo;
}
