package com.pagamento.pix.core.domain.service.pix;

import com.pagamento.pix.core.domain.aggregates.pix.Pix;
import com.pagamento.pix.core.domain.aggregates.pix.PixRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class PixServiceImpl implements PixService {

    private PixRepository pixRepository;

    public PixServiceImpl(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Override
    public Pix execute(String id, String idTransaction, LocalDateTime dateTransaction, String value,String qrCode) {

        var pix = pixRepository.findById(id);
        if (pix == null) {
            var zdt = ZonedDateTime.of(dateTransaction, ZoneId.systemDefault());
            long date = zdt.toInstant().toEpochMilli();
            return Pix.create(id, idTransaction, date, value,qrCode);
        }
        return pix;
    }

    @Override
    public Pix execute(String qrCode) {
        var pix = pixRepository.getPixByQrCode(qrCode);
        if (pix.isEmpty())
            return null;
        return pix.stream().findFirst().get();
    }

}
