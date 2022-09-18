package com.pagamento.pix.core.domain.aggregates.pix;

import com.pagamento.pix.core.domain.shared.paginates.Paginate;

import java.util.List;

public interface PixRepository {
    void save(Pix pix);

    List<Pix> getAll(Paginate paginate, String idUser);

    List<Pix> getAll(String numberTransaction, Paginate paginate);

    List<Pix> getAll(Paginate paginate);

    Pix findById(String id);
    List<Pix> getPixByQrCode(String qrCode);

}
