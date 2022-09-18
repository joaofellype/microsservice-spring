package com.pagamento.pix.controller.pix;

import com.google.zxing.WriterException;
import com.pagamento.pix.application.pix.dto.GetPixQuery;
import com.pagamento.pix.application.pix.dto.PixDto;
import com.pagamento.pix.application.pix.save.PixRequest;
import com.pagamento.pix.controller.shared.BaseControllerImpl;
import com.pagamento.pix.controller.users.UserController;
import com.pagamento.pix.core.command.pix.save.PixCommandHandler;
import com.pagamento.pix.core.domain.service.pix.PixService;
import com.pagamento.pix.core.domain.service.qrcodes.QrCodeGenerator;
import com.pagamento.pix.core.domain.shared.validators.ListValidator;
import com.pagamento.pix.core.queries.PixQueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PixControllerImpl extends BaseControllerImpl<PixDto> implements PixController {
    private final PixQueryImpl pixQuery;
    @Autowired
    private PixService pixService;
    @Autowired
    private UserController userController;
    @Autowired
    private PixCommandHandler pixCommandHandler;
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/qrcode.png";

    @Autowired
    public PixControllerImpl(PixQueryImpl pixQuery) {
        this.pixQuery = pixQuery;
    }

    @Override
    public ResponseEntity<Void> create(PixRequest pixRequest) {
        pixCommandHandler.handle(pixRequest);
        return created();
    }

    @Override
    public ResponseEntity<PixDto> findPixbyIdTransaction(String idTransaction) {

        var pixQ = pixQuery.getPixQuery();
        var pixs = pixQ.handle(new GetPixQuery(idTransaction));
        if (ListValidator.isEmpty(pixs)) {
            return notFoundAggregate();
        }
        var pix = pixs.stream().findFirst();
        if (!pix.isPresent()) {
            return notFoundAggregate();
        }
        return ok(pix.get());
    }

    @Override
    public ResponseEntity<List<PixDto>> findAll(String offset, String limit) {

        var pixQ = pixQuery.getPixQuery();
        var query = new GetPixQuery(offset,limit);
        var pixs = pixQ.handle(query);

        if(ListValidator.isEmpty(pixs))
            return notFound();


        return ok(pixs,query.getPaginate());
    }

    @Override
    public ResponseEntity<List<PixDto>> findAllByUser(String idUser, String offset, String limit) {
        var pixQ = pixQuery.getPixQuery();
        var query = new GetPixQuery(idUser,offset,limit);

        var pixs = pixQ.handle(query);
        if(ListValidator.isEmpty(pixs))
            return notFound();

        return ok(pixs,query.getPaginate());
    }

    @Override
    public ResponseEntity<Void> generateQrCode(String idTransaction) {
        String medium="https://rahul26021999.medium.com/";
        String github="https://github.com/rahul26021999";
        var pixQ = pixQuery.getPixQuery();
        var pixs = pixQ.handle(new GetPixQuery(idTransaction));
        var pix = pixs.stream().findFirst();
        byte[] image = new byte[0];
        try {
            image = QrCodeGenerator.getQRCodeImage(medium,250,250);
            QrCodeGenerator.generateQRCodeImage(pix.get().getQrCode(),250,250,QR_CODE_IMAGE_PATH);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        return  created();
    }

    @Override
    public ResponseEntity<PixDto> authorizeQrCode(String qrCode) {

        return ok(pixCommandHandler.authorizeQrCode(qrCode));
    }


}
