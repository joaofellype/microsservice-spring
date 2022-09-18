package com.pagamento.pix.controller.pix;

import com.pagamento.pix.application.pix.dto.PixDto;
import com.pagamento.pix.application.pix.save.PixRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PixController {

    @PostMapping("/pix")
    ResponseEntity<Void> create(@RequestBody PixRequest pixRequest);

    @GetMapping("/pix/{idTransaction}")
    ResponseEntity<PixDto> findPixbyIdTransaction(@PathVariable String idTransaction);

    @GetMapping("/pix")
    ResponseEntity<List<PixDto>> findAll(@RequestParam(value = "_offset",required = false) String offset,@RequestParam(value = "_limit",required = false) String limit);

    @GetMapping("/pix/user")
    ResponseEntity<List<PixDto>> findAllByUser( @RequestParam(value = "idUser",required = false) String idUser,@RequestParam(value = "_offset",required = false) String offset,@RequestParam(value = "_limit",required = false) String limit);

    @GetMapping("/pix/qrcode")
    ResponseEntity<Void> generateQrCode(@RequestParam(value = "idTransaction",required = true) String idTransaction);
    @GetMapping("/pix/authorizeqrCode")
    ResponseEntity<PixDto> authorizeQrCode(@RequestParam(value = "qrCode",required = true) String qrCode);
}
