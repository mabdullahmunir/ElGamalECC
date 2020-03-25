package com.cryptography.ecceg.cipher;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EncryptedMessage {
    private Point pointKey;
    private Point pointMessage;
}
