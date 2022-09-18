package com.pagamento.pix.core.domain.service.pix;

import com.pagamento.pix.core.domain.aggregates.pix.Pix;

import java.time.LocalDateTime;

public interface PixService {

    Pix execute(String id, String idTransaction, LocalDateTime dateTransaction, String value, String qrCode);
    Pix execute(String qrCode);

}
